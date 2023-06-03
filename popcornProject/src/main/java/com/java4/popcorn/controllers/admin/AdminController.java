package com.java4.popcorn.controllers.admin;


/*
우선
1. 간단하게 원 터치로 특정 날짜, 극장 크롤링 -> DB에 저장까지 이어지는 버튼
2. DB에 저장된 데이터, 또는 존재하는 json을 토대로 -> 특정 날짜, 극장의 정보가 있는지, 유효한지 확인하는 버튼
나중
3. kobis 또는 kmdb로부터 lazy하게 콜 하는 버튼?
 */

import com.java4.popcorn.api.line.message.LineAPI;
import com.java4.popcorn.controllers.alarm.SharedPropertiesStore;
import com.java4.popcorn.controllers.alarm.MovieController;
import com.java4.popcorn.database.mongo.member.MongoMemberDAO;
import com.java4.popcorn.database.screen.ScreenDAO;
import com.java4.popcorn.database.theater.TheaterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    final ScreenDAO screenDAO;
    final TheaterDAO theaterDAO;
    final AdminFileHandler adminFileHandler;
    final LineAPI lineAPI;
    final MongoMemberDAO mongoMemberDAO;

    public AdminController(SharedPropertiesStore store, ScreenDAO screenDAO, TheaterDAO theaterDAO, AdminFileHandler adminFileHandler, LineAPI lineAPI, MongoMemberDAO mongoMemberDAO) {
        this.store = store;
        this.screenDAO = screenDAO;
        this.theaterDAO = theaterDAO;
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


    Map<String, List<String>> map;
//    @RequestMapping(method = RequestMethod.GET, value = "/admin/CheckFiles")
//    public String cgvCheckFromFiles(Model model){
//        System.out.println("cgvCheckFromFiles");
//
//        map = CGV.checkExistingJsonFiles();
//        model.addAttribute("filesMap", map);
//
//        System.out.println("cgvCheckFromFiles Done");
//        return "admin/CheckFiles";
//    }

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

//    @RequestMapping(method = RequestMethod.GET, value = "/admin/insertExistingFiles")
//    public void cgvInsertExistingFiles(){
//        System.out.println("cgvInsertAllToDB");
//
//        Map<String, String> map = screenDAO.insertExistingFiles();
//
//        System.out.println("cgvInsertAllToDB Done : ");
//        map.forEach((k,v)-> System.out.println(k+" : "+v));
//    }
//    @RequestMapping(method = RequestMethod.GET, value = "/admin/insertFromUntil")
//    public void cgvInsertFromUntil(
//                                @RequestParam("dateFrom") String dateFrom,
//                                @RequestParam("dateUntil") String dateUntil){
//        System.out.println("cgvInsertAllToDB");
//        System.out.println("date: "+dateFrom+"~"+dateUntil);
//
//        for(TheaterVO vo : theaterDAO.getTheaterCodes()){
//            System.out.println("theater: "+vo);
//            screenDAO.insertFromUntilByTheater(vo.getTheater_id(), dateFrom, dateUntil);
//        }
//
//        System.out.println("cgvInsertAllToDB Done");
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/insertTheaterCodes")
    public void insertTheaterCodes(){
        System.out.println("insertTheaterCodes");

        theaterDAO.crawlingAllTheaterCodes();

        System.out.println("insertTheaterCodes Done");
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

//    @RequestMapping(method = RequestMethod.GET, value = "/admin/sendMessage")
//    public void sendMessageToAll(Model model){
//        System.out.println("sendMessageToAll");
//        List<String> kakaoIdList = mongoMemberDAO.selectAllKakaoId();
//        for(String kakaoId : mongoMemberDAO.selectAllKakaoId()){
//            try {
//                MongoMemberVO member = mongoMemberDAO.selectOneByKakaoId(kakaoId);
//                String lineId = member.getLine_id();
//
//                StringBuilder sb = new StringBuilder();
//                sb.append("즐겨찾기 한 극장 목록입니다");
//                Map<String, String> map = new HashMap<>();
//                List<TheaterVO> list = theaterDAO.getTheaterCodes();
//                for (TheaterVO vo : list) {
//                    map.put(vo.getTheater_id(), vo.getTheater_name());
//                }
//                for (String str : member.getTheater_favorites()){
//                    sb.append("\n").append(map.get(str));
//                }
//                sb.append("\n\n즐겨찾기 한 영화 목록입니다");
//                Map<String, String> map2 = new HashMap<>();
//                List<ScreenVO> list2 = screenDAO.selectAll();
//                for (ScreenVO vo : list2) {
//                    map2.put(vo.getMovie_id(), vo.getTitle());
//                }
//                for (String str : member.getMovie_favorites()){
//                    sb.append("\n").append(map2.get(str));
//                }
//
//                System.out.println(lineAPI.sendMessageTest(sb.toString(), lineId));
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println("sendMessage Done");
//
//        //return "admin/sendMessage";
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/admin/fileExplorerTest")
    public String fet(Model model){
        return "admin/fileExplorerTest";
    }

}
