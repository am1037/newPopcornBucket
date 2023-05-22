package com.java4.popcorn.controllers;


/*
우선
1. 간단하게 원 터치로 특정 날짜, 극장 크롤링 -> DB에 저장까지 이어지는 버튼
2. DB에 저장된 데이터, 또는 존재하는 json을 토대로 -> 특정 날짜, 극장의 정보가 있는지, 유효한지 확인하는 버튼
나중
3. kobis 또는 kmdb로부터 lazy하게 콜 하는 버튼?
 */

import com.java4.popcorn.data.theater.TheaterVO;
import com.java4.popcorn.crawling.cgv.CGV;
import com.java4.popcorn.crawling.cgv.Schedule;
import com.java4.popcorn.data.screen.ScreenDAO;
import com.java4.popcorn.data.theater.TheaterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@Controller
public class AdminController {
    @Autowired
    CGV cgv;
    @Autowired
    ScreenDAO screenDAO;
    @Autowired
    TheaterDAO theaterDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    public String admin(Model model){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String today = sdf.format(date);
        String lastday = sdf.format(new Date(date.getTime() + (1000 * 60 * 60 * 24 * 7)));
        model.addAttribute("today", today);
        model.addAttribute("lastday", lastday);
        return "admin/admin";
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

        int i = screenDAO.insertExistingFiles();

        System.out.println("cgvInsertAllToDB Done : " + i);
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

        List<String> list = new ArrayList<>();
        theaterDAO.selectTheaterByRegion(region).forEach(vo -> list.add(vo.getTheater_id()));

        int i1 = Integer.parseInt(dateFrom);
        int i2 = Integer.parseInt(dateUntil);

        for(int i = i1; i <= i2; i++){
            for(String theater_id : list){
                cgvCrawlOneDay(theater_id, i);
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

        int i1 = Integer.parseInt(dateFrom);
        int i2 = Integer.parseInt(dateUntil);

        for(int i = i1; i <= i2; i++){
            cgvCrawlOneDay(theater_id, i);
        }

        System.out.println("cgvCrawlOne Done");
    }

    private void cgvCrawlOneDay(@RequestParam("theater") String theater_id, int i) {
        String date = String.valueOf(i);
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

}
