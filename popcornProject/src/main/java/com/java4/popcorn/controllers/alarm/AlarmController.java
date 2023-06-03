package com.java4.popcorn.controllers.alarm;

import com.java4.popcorn.database.mongo.member.MongoMemberDAO;
import com.java4.popcorn.database.mongo.member.MongoMemberVO;
import com.java4.popcorn.database.movie.mapping.MovieMappingVO;
import com.java4.popcorn.database.screen.ScreenDAO;
import com.java4.popcorn.database.screen.ScreenVO;
import com.java4.popcorn.database.theater.TheaterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @RequestMapping(method = RequestMethod.GET, value = "/1")
    public String home(HttpServletRequest request,
                       Model model) {
        //사용자 특정 관련.. 세션과 쿠키 등?
        HttpSession session = request.getSession();

        if(session.getAttribute("kakaoId") == null){
            System.out.println("kakaoId is null");
        }else {
            System.out.println("kakaoId is " + session.getAttribute("kakaoId"));
        }

        Map<String, List<ScreenVO>> movieOnScreenMap = store.getMovieOnScreenMap();
        List<MovieMappingVO> movieOnScreenMapList = store.getMovieOnScreenMapList();
        Map<String, String> movieToIdMap = store.getMovieIdToTitleMap();

        model.addAttribute("movieOnScreenMap", movieOnScreenMap);

        model.addAttribute("movie1_poster", movieOnScreenMapList.get(0).getPosterList().get(0));
        model.addAttribute("movie1_name", movieToIdMap.get(movieOnScreenMapList.get(0).getDocid()));
        model.addAttribute("movie1_number", movieOnScreenMap.get(movieOnScreenMapList.get(0).getDocid()).size());

        model.addAttribute("movie2_poster", movieOnScreenMapList.get(1).getPosterList().get(0));
        model.addAttribute("movie2_name", movieToIdMap.get(movieOnScreenMapList.get(1).getDocid()));
        model.addAttribute("movie2_number", movieOnScreenMap.get(movieOnScreenMapList.get(1).getDocid()).size());

        model.addAttribute("movie3_poster", movieOnScreenMapList.get(2).getPosterList().get(0));
        model.addAttribute("movie3_name", movieToIdMap.get(movieOnScreenMapList.get(2).getDocid()));
        model.addAttribute("movie3_number", movieOnScreenMap.get(movieOnScreenMapList.get(2).getDocid()).size());

        return "alarm/home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login(HttpServletRequest request,
                        Model model) {
        //사용자 특정 관련.. 세션과 쿠키 등?
        HttpSession session = request.getSession();

        if(session.getAttribute("kakaoId") == null){
            System.out.println("kakaoId is null");
        }else {
            System.out.println("kakaoId is " + session.getAttribute("kakaoId"));
        }

        return "login";
    }
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
