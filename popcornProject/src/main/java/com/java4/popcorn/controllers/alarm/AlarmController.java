package com.java4.popcorn.controllers.alarm;

import com.java4.popcorn.database.MongoMember.MongoMemberDAO;
import com.java4.popcorn.database.MongoMember.MongoMemberVO;
import com.java4.popcorn.database.screen.ScreenDAO;
import com.java4.popcorn.database.theater.TheaterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AlarmController {

    @Autowired
    MongoMemberDAO mongoMemberDAO;
    @Autowired
    ScreenDAO screenDAO;
    @Autowired
    SharedPropertiesStore store;
    @Autowired
    TheaterDAO theaterDAO;

    @RequestMapping(method = RequestMethod.GET, value = "alarm/alarm")
    public String alarm_main(HttpServletRequest request,
                             Model model) {
        System.out.println("alarm_main");

        String kakaoId;
        MongoMemberVO vo;
        try {
            kakaoId = request.getSession().getAttribute("kakaoId").toString();
            vo = mongoMemberDAO.selectOneByKakaoId(kakaoId);
        }catch (NullPointerException e){
            return "redirect:/login";
        }
        return "alarm/alarm/alarm_main";
    }

    @RequestMapping(method = RequestMethod.GET, value = "alarm/navbar")
    public String alarm_navbar(Model model) {
        return "alarm/navbar";
    }

}
