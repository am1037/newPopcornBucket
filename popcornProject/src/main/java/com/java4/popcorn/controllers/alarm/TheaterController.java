package com.java4.popcorn.controllers.alarm;

import com.google.common.cache.RemovalListener;
import com.java4.popcorn.database.MongoMember.MongoMemberDAO;
import com.java4.popcorn.database.MongoMember.MongoMemberVO;
import com.java4.popcorn.database.theater.TheaterDAO;
import com.java4.popcorn.database.theater.TheaterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class TheaterController {

    final
    TheaterDAO theaterDAO;
    final
    MongoMemberDAO mongoMemberDAO;

    List<TheaterVO> theaterList;
    Set<String> regionSet;
    Map<String, List<String>> theaterMapByRegion = new HashMap<>();
    public TheaterController(TheaterDAO theaterDAO, MongoMemberDAO mongoMemberDAO) {
        System.out.println("TheaterController initializing...");
        theaterList = theaterDAO.selectAllTheaterCode();

        regionSet = new HashSet<>();
        for (TheaterVO theater : theaterList) {
            regionSet.add(theater.getTheater_region());
        }

        for (String region : regionSet) {
            List<String> theaterNames = new ArrayList<>();
            for (TheaterVO theater : theaterList) {
                if (theater.getTheater_region().equals(region)) {
                    theaterNames.add(theater.getTheater_name());
                }
            }
            theaterMapByRegion.put(region, theaterNames);
        }

        System.out.println("TheaterController initialized.");

        theaterMapByRegion.forEach((key, value) -> {
            System.out.println(key + " : " + value);
        });

        this.theaterDAO = theaterDAO;
        this.mongoMemberDAO = mongoMemberDAO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "alarm/theater/")
    public String theater_main(HttpServletRequest request,
                               Model model) {
        String kakaoId = String.valueOf(request.getSession().getAttribute("kakaoId"));
        if (kakaoId == null) {
            //로그인 안되어있으면 로그인 페이지로
            //나중에 콜백 추가??
            System.out.println("kakaoId is null");
            return "redirect:/";
        }
        System.out.println("kakaoId : " + kakaoId);
        //mongoMemberDAO.selectOneByKakaoId(kakaoId).getTheater_favorites().forEach(System.out::println);
        MongoMemberVO vo = mongoMemberDAO.selectOneByKakaoId(kakaoId);
        System.out.println(vo);


        model.addAttribute("regionSet", regionSet);
        model.addAttribute("theaterMapByRegion", theaterMapByRegion);

        return "alarm/theater/theater_main";
    }


}
