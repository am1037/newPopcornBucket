package com.java4.popcorn.crawling.controller;

import com.java4.popcorn.DB.MovieScreenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CrawlingController {

    @Autowired
    MovieScreenDAO msDAO;

    @RequestMapping(method = RequestMethod.GET, value = "/1")
    public void test(){
        System.out.println(msDAO.select0513("슈퍼 마리오", "0004"));

        //여러날짜 DB에 집어넣는 코드.. 지금은 비활성
//        try {
//
//            Schedule schedule = new Schedule();
//
//            String[] theaterCodes = {"0004", "0009", "0056", "0257"};
//            for (String t : theaterCodes) {
//                for (int i = 20230513; i <= 20230520; i++) {
//                    ClassPathResource cpr = new ClassPathResource("jsons\\schedule" + t + i + ".json");
//                    File file = cpr.getFile();
//                    schedule.fromJson(file).getMovieScreenList().forEach(msDAO::insert);
//                }
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }


}
