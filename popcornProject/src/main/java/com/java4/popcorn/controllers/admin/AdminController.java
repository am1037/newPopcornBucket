package com.java4.popcorn.controllers.admin;


/*
우선
1. 간단하게 원 터치로 특정 날짜, 극장 크롤링 -> DB에 저장까지 이어지는 버튼
2. DB에 저장된 데이터, 또는 존재하는 json을 토대로 -> 특정 날짜, 극장의 정보가 있는지, 유효한지 확인하는 버튼
나중
3. kobis 또는 kmdb로부터 lazy하게 콜 하는 버튼?
 */

import com.java4.popcorn.api.kmdb.KmdbAPI;
import com.java4.popcorn.api.line.message.LineAPI;
import com.java4.popcorn.controllers.SharedPropertiesStore;
import com.java4.popcorn.controllers.alarm.MovieController;
import com.java4.popcorn.database.MongoMember.MongoMemberDAO;
import com.java4.popcorn.database.MongoMember.MongoMemberVO;
import com.java4.popcorn.database.screen.ScreenVO;
import com.java4.popcorn.database.theater.TheaterVO;
import com.java4.popcorn.database.cgv.CGV;
import com.java4.popcorn.database.cgv.Schedule;
import com.java4.popcorn.database.screen.ScreenDAO;
import com.java4.popcorn.database.theater.TheaterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;


@Controller
public class AdminController {
    final SharedPropertiesStore store;

    final CGV cgv;
    final ScreenDAO screenDAO;
    final TheaterDAO theaterDAO;
    final KmdbAPI kmdbAPI;
    final AdminFileHandler adminFileHandler;
    final LineAPI lineAPI;
    final MongoMemberDAO mongoMemberDAO;

    public AdminController(SharedPropertiesStore store, CGV cgv, ScreenDAO screenDAO, TheaterDAO theaterDAO, KmdbAPI kmdbAPI, AdminFileHandler adminFileHandler, LineAPI lineAPI, MongoMemberDAO mongoMemberDAO) {
        this.store = store;
        this.cgv = cgv;
        this.screenDAO = screenDAO;
        this.theaterDAO = theaterDAO;
        this.kmdbAPI = kmdbAPI;
        this.adminFileHandler = adminFileHandler;
        this.lineAPI = lineAPI;
        this.mongoMemberDAO = mongoMemberDAO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    public String admin(Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String today = sdf.format(date);
        String lastday = sdf.format(new Date(date.getTime() + (1000 * 60 * 60 * 24 * 4)));
        model.addAttribute("today", today);
        model.addAttribute("lastday", lastday);
        model.addAttribute("regionSet", store.getRegionSet());
        return "admin/admin";
    }

    @Autowired
    MovieController mc;

    @RequestMapping(method = RequestMethod.GET, value = "/admin/refreshProperties")
    public void refresh(Model model){
        mc.setProperties();
        //return "admin/admin";
    }


    Map<String, List<String>> map;
    @RequestMapping(method = RequestMethod.GET, value = "/admin/CheckFiles")
    public String cgvCheckFromFiles(Model model){
        System.out.println("cgvCheckFromFiles");

        map = CGV.checkExistingJsonFiles();
        model.addAttribute("filesMap", map);

        System.out.println("cgvCheckFromFiles Done");
        return "admin/CheckFiles";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/ListAllTheaters")
    public String cgvListAllTheaters(Model model){
        System.out.println("cgvListAllTheaters");

        model.addAttribute(theaterDAO.selectAllTheaterCode());

        System.out.println("cgvListAllTheaters Done");
        return "admin/ListAllTheaters";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/truncateScreenTable")
    public void truncateScreen(){
        System.out.println("truncateScreen");

        screenDAO.truncateTable();

        System.out.println("truncateScreen done");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/emptyFolder")
    public void emptyFolder(){
        System.out.println("emptyFolder");

        Path dir = Paths.get("schedules");
        try (Stream<Path> walk = Files.walk(dir)){
                    walk
                    .map(Path::toFile)
                    .forEach(file -> {
                        if (!file.isDirectory()) {
                            file.delete();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("emptyFolder done");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/insertExistingFiles")
    public void cgvInsertExistingFiles(){
        System.out.println("cgvInsertAllToDB");

        Map<String, String> map = screenDAO.insertExistingFiles();

        System.out.println("cgvInsertAllToDB Done : ");
        map.forEach((k,v)-> System.out.println(k+" : "+v));
    }
    @RequestMapping(method = RequestMethod.GET, value = "/admin/insertFromUntil")
    public void cgvInsertFromUntil(
                                @RequestParam("dateFrom") String dateFrom,
                                @RequestParam("dateUntil") String dateUntil){
        System.out.println("cgvInsertAllToDB");
        System.out.println("date: "+dateFrom+"~"+dateUntil);

        for(TheaterVO vo : theaterDAO.getTheaterCodes()){
            System.out.println("theater: "+vo);
            screenDAO.insertFromUntilByTheater(vo.getTheater_id(), dateFrom, dateUntil);
        }

        System.out.println("cgvInsertAllToDB Done");
    }


    //나중에 코드 다른 곳으로 옮겨야 합니다!! 그 때까지 최대한 손대지 않기로
    Map<String, List<String>> crawlingErrors;
    @RequestMapping(method = RequestMethod.GET, value = "/admin/crawlFromUntilRegion")
    public void cgvCrawlFromUntilRegion(
                                @RequestParam("dateFrom") String dateFrom,
                                @RequestParam("dateUntil") String dateUntil,
                                @RequestParam("region") String region){
        System.out.println("cgvCrawlFromUntilRegion");

        for(String r : region.split(",")) {
            List<String> list = new ArrayList<>();
            theaterDAO.selectTheaterByRegion(r).forEach(vo -> list.add(vo.getTheater_id()));
            for (String theater_id : list) {
                for (String i : store.betweenTwoDate(dateFrom, dateUntil)) {
                    cgvCrawlOneDay(theater_id, i);
                }
            }
        }

        System.out.println("cgvCrawlFromUntilRegion Done");
    }
    @RequestMapping(method = RequestMethod.GET, value = "/admin/crawlOne")
    public void cgvCrawlOneTheater(
            @RequestParam("dateFrom") String dateFrom,
            @RequestParam("dateUntil") String dateUntil,
            @RequestParam("theater") String theater_id){
        System.out.println("cgvCrawlOne");

        for(String i : store.betweenTwoDate(dateFrom, dateUntil)){
            cgvCrawlOneDay(theater_id, i);
        }

        System.out.println("cgvCrawlOne Done");
    }

    private void cgvCrawlOneDay(@RequestParam("theater") String theater_id, String date) {
        System.out.println("crawling theater: "+theater_id+", date: "+date);
        try {
            Schedule schedule = cgv.crawl(theater_id, date);
            schedule.printAsJsonFile("schedules/"+theater_id+"_"+date+".json");
        }catch (Exception e){
            if(crawlingErrors == null) crawlingErrors = new HashMap<>();
            crawlingErrors.putIfAbsent(theater_id, new ArrayList<>());
            crawlingErrors.get(theater_id).add(date);
            System.out.println("error: "+e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/insertTheaterCodes")
    public void insertTheaterCodes(){
        System.out.println("insertTheaterCodes");

        theaterDAO.crawlingAllTheaterCodes();

        System.out.println("insertTheaterCodes Done");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/insertMovieId")
    public void insertMovieId(Model model){
        System.out.println("insertMovieId");

        screenDAO.updateMovieId();

        System.out.println("insertMovieId Done");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/insertMovieIdOne")
    public void insertMovieIdOne(@RequestParam("title") String movie_title,
                                 @RequestParam("DOCID") String movie_DOCID){
        System.out.println("insertMovieIdOne");

        screenDAO.updateMovieIdByTitle(movie_title, movie_DOCID);

        System.out.println("insertMovieIdOne Done");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/readMovieIdErrorJsons")
    public String readMovieIdErrorJsons(Model model){
        List<String> list = adminFileHandler.readErrorJsonsAsStringList();
        model.addAttribute("list", list);
        return "admin/error/readMovieIdErrorJsons";
    }

    //테스트 용도입니다!!!! 근데 지금 급한데 바꾸면 또 안 될까봐 sendMessageTest라고 이름 못 바꾸겟음 ㅎ
//    @RequestMapping(method = RequestMethod.GET, value = "/admin/sendMessage")
//    public void sendMessage(HttpServletRequest request,
//                            Model model){
//        System.out.println("sendMessage");
//        String kakaoId = request.getSession().getAttribute("kakaoId").toString();
//        try {
//            MongoMemberVO member = mongoMemberDAO.selectOneByKakaoId(kakaoId);
//            String lineId = member.getLine_id();
//
//            StringBuilder sb = new StringBuilder();
//            sb.append("즐겨찾기 한 극장 목록입니다");
//            Map<String, String> map = new HashMap<>();
//            List<TheaterVO> list = theaterDAO.getTheaterCodes();
//            for (TheaterVO vo : list) {
//                map.put(vo.getTheater_id(), vo.getTheater_name());
//            }
//            for (String str : member.getTheater_favorites()){
//                sb.append("\n").append(map.get(str));
//            }
//            sb.append("\n\n즐겨찾기 한 영화 목록입니다");
//            Map<String, String> map2 = new HashMap<>();
//            List<ScreenVO> list2 = screenDAO.selectAll();
//            for (ScreenVO vo : list2) {
//                map2.put(vo.getMovie_id(), vo.getTitle());
//            }
//            for (String str : member.getMovie_favorites()){
//                sb.append("\n").append(map2.get(str));
//            }
//
//            System.out.println(lineAPI.sendMessageTest(sb.toString(), lineId));
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("sendMessage Done");
//
//        //return "admin/sendMessage";
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/sendMessage")
    public void sendMessageToAll(Model model){
        System.out.println("sendMessageToAll");
        List<String> kakaoIdList = mongoMemberDAO.selectAllKakaoId();
        for(String kakaoId : mongoMemberDAO.selectAllKakaoId()){
            try {
                MongoMemberVO member = mongoMemberDAO.selectOneByKakaoId(kakaoId);
                String lineId = member.getLine_id();

                StringBuilder sb = new StringBuilder();
                sb.append("즐겨찾기 한 극장 목록입니다");
                Map<String, String> map = new HashMap<>();
                List<TheaterVO> list = theaterDAO.getTheaterCodes();
                for (TheaterVO vo : list) {
                    map.put(vo.getTheater_id(), vo.getTheater_name());
                }
                for (String str : member.getTheater_favorites()){
                    sb.append("\n").append(map.get(str));
                }
                sb.append("\n\n즐겨찾기 한 영화 목록입니다");
                Map<String, String> map2 = new HashMap<>();
                List<ScreenVO> list2 = screenDAO.selectAll();
                for (ScreenVO vo : list2) {
                    map2.put(vo.getMovie_id(), vo.getTitle());
                }
                for (String str : member.getMovie_favorites()){
                    sb.append("\n").append(map2.get(str));
                }

                System.out.println(lineAPI.sendMessageTest(sb.toString(), lineId));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("sendMessage Done");

        //return "admin/sendMessage";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/fileExplorerTest")
    public String fet(Model model){
        return "admin/fileExplorerTest";
    }

}
