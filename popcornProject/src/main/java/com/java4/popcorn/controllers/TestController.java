package com.java4.popcorn.controllers;

import com.java4.popcorn.controllers.alarm.SharedPropertiesStore;
import com.java4.popcorn.database.mongo.member.MongoMemberDAO;
import com.java4.popcorn.database.mongo.member.MongoMemberVO;
import com.java4.popcorn.database.screen.ScreenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    MongoMemberDAO mongoMemberDAO;

    @Autowired
    SharedPropertiesStore store;

    @Autowired
    ScreenDAO screenDAO;

    @RequestMapping("/")
    public String test(HttpServletRequest request,
                       Model model){
        request.getSession().setAttribute("member_id", "am1037");
        MongoMemberVO memberVO = mongoMemberDAO.selectOneByMemberId(request.getSession().getAttribute("member_id").toString());

        model.addAttribute("memberVO_member_id", memberVO.getMember_id());
        model.addAttribute("memberVO_kakao_id", memberVO.getKakao_id());
        model.addAttribute("memberVO_line_id", memberVO.getLine_id());

        Map<String, String> favoriteMovieMap = new HashMap<>();
        for(String movieId : memberVO.getMovie_favorites()){
            favoriteMovieMap.put(movieId, store.getMovieIdToTitleMap().get(movieId));
        }
        model.addAttribute("favoriteMovieMap", favoriteMovieMap);

        Map<String, String> favoriteTheaterMap = new HashMap<>();
        for(String theaterId : memberVO.getTheater_favorites()){
            favoriteTheaterMap.put(theaterId, store.getTheaterIdToNameMap().get(theaterId));
        }
        model.addAttribute("favoriteTheaterMap", favoriteTheaterMap);

        model.addAttribute("dateFrom", store.getDateString());
        model.addAttribute("dateUntil", store.getDateStringPlus4day());

        return "testPage";
    }
}
