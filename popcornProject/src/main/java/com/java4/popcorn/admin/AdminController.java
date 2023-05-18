package com.java4.popcorn.admin;


/*
우선
1. 간단하게 원 터치로 특정 날짜, 극장 크롤링 -> DB에 저장까지 이어지는 버튼
2. DB에 저장된 데이터, 또는 존재하는 json을 토대로 -> 특정 날짜, 극장의 정보가 있는지, 유효한지 확인하는 버튼
나중
3. kobis 또는 kmdb로부터 lazy하게 콜 하는 버튼?
 */

import com.java4.popcorn.DB.TheaterCodeVO;
import com.java4.popcorn.cgv.CGV;
import com.java4.popcorn.cgv.Schedule;
import com.java4.popcorn.DB.MovieScreenDAO;
import com.java4.popcorn.notnow.Kobis.movieInfo.kmdb.KmdbAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AdminController {
    @Autowired
    CGV cgv;
    @Autowired
    KmdbAPI kmdbAPI;
    @Autowired
    MovieScreenDAO movieScreenDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    public String admin(Model model)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String today = sdf.format(date);
        String lastday = sdf.format(new Date(date.getTime() + (1000 * 60 * 60 * 24 * 7)));
        model.addAttribute("today", today);
        model.addAttribute("lastday", lastday);
        return "admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/truncateScreen")
    public String truncateScreen(){
        System.out.println("truncateScreen");
        movieScreenDAO.truncateTable();
        System.out.println("truncateScreen done");
        return "admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ci2db")
    public String cgvInsertToDB(
                        @RequestParam("theater") String theater,
                        @RequestParam("dateFrom") String dateFrom,
                        @RequestParam("dateUntil") String dateUntil,
                        Model model){
        System.out.println("cgvInsertToDB");
        System.out.println("theater: "+theater + ", date: "+dateFrom+"~"+dateUntil);
        movieScreenDAO.insertFromUntilByTheater(theater, dateFrom, dateUntil);
        return "admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ciat")
    public String cgvInsertAllToDB(
            @RequestParam("dateFrom") String dateFrom,
            @RequestParam("dateUntil") String dateUntil,
            Model model){
        System.out.println("cgvInsertAllToDB");
        System.out.println("date: "+dateFrom+"~"+dateUntil);
        for(TheaterCodeVO vo : movieScreenDAO.getTheaterCodes()){
            System.out.println("theater: "+vo);
            movieScreenDAO.insertFromUntilByTheater(vo.getTheater_id(), dateFrom, dateUntil);
        }
        return "admin";
    }

    /*
    이 밑에 있는 크롤 친구들은 모두 이 친구를 이용하며
    이 친구는 json 파일로 쓰는 데까지 진행합니다
     */
    @RequestMapping(method = RequestMethod.GET, value = "/ccbta")
    public String cgvCrawlByTheaterAndDate(@RequestParam("theater") String theater,
                                           @RequestParam("date") String date){
        System.out.println("cgvCrawlByTheaterAndDate");
        System.out.println("theater: "+theater + ", date: "+date);
        Schedule schedule = cgv.crawl(theater, date);
        schedule.printAsJsonFile("schedules/"+theater+"_"+date+".json");
        return "admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ccbtafu")
    public String cgvCrawlByTheaterAndDateFromUntil(
                                        @RequestParam("theater") String theater,
                                        @RequestParam("dateFrom") String dateFrom,
                                        @RequestParam("dateUntil") String dateUntil,
                                        Model model){
        System.out.println("cgvByTheaterAndDateFromUntil");
        int i1 = Integer.parseInt(dateFrom);
        int i2 = Integer.parseInt(dateUntil);
        for(int i=i1; i<=i2; i++){
            System.out.println("theater: "+theater + ", date: "+i);
            String date = String.valueOf(i);
            cgvCrawlByTheaterAndDate(theater, date);
        }
        return "admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ccat")
    public String cgvCrawlAllTheaters(
                                        @RequestParam("dateFrom") String dateFrom,
                                        @RequestParam("dateUntil") String dateUntil,
                                        Model model){
        System.out.println("cgvCrawlAllTheaters");
        for(TheaterCodeVO vo : movieScreenDAO.getTheaterCodes()){
            System.out.println("theater: "+vo);
            cgvCrawlByTheaterAndDateFromUntil(vo.getTheater_id(), dateFrom, dateUntil, model);
        }

        return "admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cctbr")
    public String cgvCrawlTheatersByRegion(
            @RequestParam("dateFrom") String dateFrom,
            @RequestParam("dateUntil") String dateUntil,
            @RequestParam("region") String region,
            Model model){
        System.out.println("cgvCrawlTheatersByRegion");
        for(TheaterCodeVO vo : movieScreenDAO.getTheaterCodes(region)){
            System.out.println("theater: "+vo);
            cgvCrawlByTheaterAndDateFromUntil(vo.getTheater_id(), dateFrom, dateUntil, model);
        }
        System.out.println(cgv.getMap());

        return "admin";
    }

    /*
    bin/jsons/에 있는 TTTT_YYYYMMDD.json 파일들의
    '극장과 날짜 정보만', '모두' 읽어서 맵에 저장한다
     */
    Map<String, List<String>> map;
    @RequestMapping(method = RequestMethod.GET, value = "/ccff")
    public String cgvCheckFromFiles(Model model){
        System.out.println("cgvCheckFromFiles");
        map = new HashMap<>();
        File[] files = new File("schedules").listFiles();
        if (files != null) {
            for (File file : files) {
                map.putIfAbsent(file.getName().substring(0, 4), new ArrayList<String>());
                map.get(file.getName().substring(0, 4)).add(file.getName().substring(5, 13));
            }
            System.out.println("map: "+map);
        } else {
            System.out.println("files is null");
        }
        model.addAttribute("filesMap", map);
        return "admin/ccff";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/crfof")
    public String cgvReadFromOneFile(@RequestParam("theater") String theater,
                                     @RequestParam("date") String date,
                                     Model model){
        System.out.println("cgvReadFromOneFile");
        File file = new File("schedules/"+theater+"_"+date+".json");
        Schedule schedule = new Schedule().fromJson(file);
        System.out.println("schedule: "+schedule);
        model.addAttribute("schedule"+theater+date, schedule);
        return "admin";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/clat")
    public String cgvListAllTheater(Model model){
        model.addAttribute(movieScreenDAO.selectAllTheaterCode());
        return "admin/clat";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/insertTheaterCodes")
    public void insertTheaterCodes(){
        System.out.println("insertTheaterCodes");
        movieScreenDAO.insertTheaterCodes();
    }

//    public String cgvCheckByTheaterAndDate(String theater, String date){
//        System.out.println("cgvCheckFromDB");
//        System.out.println("theater: "+theater + ", date: "+date);
//        List<MovieScreenVO> movieScreenVOList = movieScreenDAO.selectByTheaterAndDate(theater, date);
//        return "admin";
//    }


}
