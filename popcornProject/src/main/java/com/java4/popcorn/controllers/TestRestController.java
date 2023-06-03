package com.java4.popcorn.controllers;

import com.java4.popcorn.controllers.alarm.SharedPropertiesStore;
import com.java4.popcorn.database.mongo.member.MongoMemberDAO;
import com.java4.popcorn.database.screen.ScreenDAO;
import com.java4.popcorn.database.screen.ScreenVO;
import com.java4.popcorn.database.theater.TheaterVO;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class TestRestController {

    @Autowired
    ScreenDAO screenDAO;
    @Autowired
    SharedPropertiesStore store;
    @Autowired
    MongoMemberDAO mongoMemberDAO;

    @RequestMapping("/btnInTest")
    public String testBtn(HttpServletRequest request,
                          Model model){

        System.out.println("btnInTest");

        model.addAttribute("hmm", "hmm");

        return "testPage";
    }

    @RequestMapping("/btnInTest2")
    public void testBtn2(@RequestParam("movieId") String hmm,
                           HttpServletRequest request,
                           Model model){
        //StringBuilder sb = new StringBuilder();
        List<String> theaterVOs = mongoMemberDAO.selectOneByMemberId(request.getSession().getAttribute("member_id").toString()).getTheater_favorites();
        for(String theaterStr : theaterVOs) {
            System.out.println(store.getTheaterIdToNameMap().get(theaterStr));
            for (String dateStr : store.getDateStrings()) {
                System.out.println(dateStr);
                for (ScreenVO vo : screenDAO.selectByMovieIdAndTheaterAndDate(hmm, theaterStr, dateStr)) {
                    System.out.println(vo.toString());
                }
            }
        }
        //return sb.toString();
    }
}
