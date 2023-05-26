package com.java4.popcorn.controllers.alarm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java4.popcorn.controllers.SharedPropertiesStore;
import com.java4.popcorn.database.MongoMember.MongoMemberDAO;
import com.java4.popcorn.database.MongoMember.MongoMemberVO;
import com.java4.popcorn.database.screen.ScreenDAO;
import com.java4.popcorn.database.screen.ScreenVO;
import com.java4.popcorn.database.theater.TheaterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AlarmController {

    @Autowired
    MongoMemberDAO mongoMemberDAO;
    @Autowired
    ScreenDAO screenDAO;
    @Autowired
    SharedPropertiesStore store;

    @RequestMapping(method = RequestMethod.GET, value = "alarm/")
    public String alarm_main(HttpServletRequest request,
                             Model model) {
        String kakaoId = request.getSession().getAttribute("kakaoId").toString();
        MongoMemberVO mongoMemberVO = mongoMemberDAO.selectOneByKakaoId(kakaoId);

        List<String> listTheaterFavorites = mongoMemberVO.getTheater_favorites();
        List<String> listMovieFavorites = mongoMemberVO.getMovie_favorites();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String tomorrow = sdf.format(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));
        String fourDays = sdf.format(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 4));

        List<String> daysList = store.betweenTwoDate(tomorrow, fourDays);

        Map<String, List<ScreenVO>> screenMap = new HashMap<>();
        for(String theater : listTheaterFavorites)      {
            for(String movie : listMovieFavorites) {
                screenMap.put(movie, new ArrayList<>());
                for (String day : daysList) {
                    screenMap.get(movie).addAll(screenDAO.selectByTheaterAndDateAndMovieId(theater, day, movie));
                }
            }
        }

        try {
        ObjectMapper om = new ObjectMapper();
        model.addAttribute("listTheaterFavorites", om.writeValueAsString(listTheaterFavorites));
        model.addAttribute("listMovieFavorites", om.writeValueAsString(listMovieFavorites));
        model.addAttribute("screenMap", om.writeValueAsString(screenMap));
        System.out.println(om.writeValueAsString("hmm1" + store.getMovieIdToTitleMap()));
        model.addAttribute("movieIdToTitleMap", om.writeValueAsString(store.getMovieIdToTitleMap()));
        model.addAttribute("listDays", om.writeValueAsString(daysList));
        //print attributed values for check and test
        System.out.println("alarm_main : " + request.getSession().getAttribute("kakaoId"));
        System.out.println("listTheaterFavorites : " + listTheaterFavorites);
        System.out.println("listMovieFavorites : " + listMovieFavorites);
        System.out.println("screenMap : " + screenMap);
        System.out.println("listDays : " + daysList);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return "alarm/alarm/alarm_main";
    }

    @RequestMapping(method = RequestMethod.GET, value = "alarm/navbar")
    public String alarm_navbar(Model model) {
        return "alarm/navbar";
    }

}
