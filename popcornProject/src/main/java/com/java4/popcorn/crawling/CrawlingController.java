package com.java4.popcorn.crawling;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CrawlingController {

    @Autowired
    MovieScreenDAO msDAO;

    CrawlingCGV cgv = new CrawlingCGV();
    List<MovieScreenVO> msList;
    @RequestMapping(method = RequestMethod.GET, value = "/crawling")
    public void crawling(
                        @RequestParam(value = "theater_code") String theater_code,
                        @RequestParam(value = "date") String date,
                        Model model) {
        System.out.println("theater_code: "+theater_code);
        System.out.println("date: "+date);
        msList = cgv.crawling(theater_code, date);
        System.out.println(msList);
        //return "home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/selectAll")
    public void selectAll(Model model) {
        msDAO.selectAll();
    }


}
