package com.java4.popcorn.controllers.alarm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java4.popcorn.controllers.alarm.subclasses.FavoritesRequest;
import com.java4.popcorn.database.mongo.member.MongoMemberDAO;
import com.java4.popcorn.database.mongo.member.MongoMemberVO;
import com.java4.popcorn.database.theater.TheaterDAO;
import com.java4.popcorn.database.theater.TheaterVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
    Map<String, TheaterVO> theaterMap;
    Set<String> regionSet;
    Map<String, List<TheaterVO>> theaterMapByRegion;

    public TheaterController(TheaterDAO theaterDAO, MongoMemberDAO mongoMemberDAO) {
        this.theaterDAO = theaterDAO;
        this.mongoMemberDAO = mongoMemberDAO;
        setProperties();
    }

    /*
    sql로 바꿔야합니다!! 근데 지금은 그냥 java.util 친구들이 더 다루기 쉬우니까 ㅎ
     */
    @Deprecated
    public void setProperties(){
        theaterList = theaterDAO.selectAllTheaterCode();
        theaterMap = new HashMap<>();
        regionSet = new HashSet<>();
        theaterMapByRegion = new HashMap<>();

        for (TheaterVO theater : theaterList) {
            regionSet.add(theater.getTheater_region());
            theaterMap.put(theater.getTheater_id(), theater);
        }

        for (String region : regionSet) {
            List<TheaterVO> theaters = new ArrayList<>();
            for (TheaterVO theater : theaterList) {
                if (theater.getTheater_region().equals(region)) {
                    theaters.add(theater);
                }
            }
            theaterMapByRegion.put(region, theaters);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "alarm/theater/")
    public String theater_main(HttpServletRequest request,
                               Model model) {
        String kakaoId;
        MongoMemberVO vo;
        try {
            kakaoId = request.getSession().getAttribute("kakaoId").toString();
            vo = mongoMemberDAO.selectOneByKakaoId(kakaoId);
        }catch (NullPointerException e){
            return "redirect:/login";
        }

        model.addAttribute("theater_rawMap", theaterMap);
        model.addAttribute("theater_favorites", vo.getTheater_favorites());
        model.addAttribute("regionSet", regionSet);
        model.addAttribute("theaterMapByRegion", theaterMapByRegion);

        return "alarm/theater/theater_main";
    }
    @RequestMapping(method = RequestMethod.POST, value = "alarm/theater/request")
    public void theater_request(HttpServletRequest request,
                                @RequestBody String body){
        System.out.println("theater_request called");
        ObjectMapper om = new ObjectMapper();
        try {
            System.out.println("theater_request process go on");
            FavoritesRequest tfr = om.readValue(body, FavoritesRequest.class);
            List<String> warnings = tfr.getWarnings();
            List<String> dangers = tfr.getDangers();
            System.out.println("primaries : " + warnings);
            System.out.println("dangers : " + dangers);
            String kakaoId = String.valueOf(request.getSession().getAttribute("kakaoId"));
            MongoMemberVO vo = mongoMemberDAO.selectOneByKakaoId(kakaoId);
            for (String w : warnings) {
                if (!vo.getTheater_favorites().contains(w)) {
                    mongoMemberDAO.pushTheaterByKakaoId(kakaoId, w);
                }
            }
            for (String d : dangers) {
                mongoMemberDAO.pullTheaterByKakaoId(kakaoId, d);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
