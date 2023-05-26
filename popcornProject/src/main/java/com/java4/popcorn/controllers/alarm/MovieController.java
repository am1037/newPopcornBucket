package com.java4.popcorn.controllers.alarm;

import com.java4.popcorn.controllers.SharedPropertiesStore;
import com.java4.popcorn.database.MongoMember.MongoMemberDAO;
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
import java.util.*;

@Controller
public class MovieController {
    final
    ScreenDAO screenDAO;
    final
    MongoMemberDAO mongoMemberDAO;
    final
    SharedPropertiesStore store;

    Map<String, String> movieOnScreen;
    public MovieController(ScreenDAO screenDAO, MongoMemberDAO mongoMemberDAO, SharedPropertiesStore store) {
        this.screenDAO = screenDAO;
        this.mongoMemberDAO = mongoMemberDAO;
        this.store = store;
        setProperties();
    }

    /*
    sql로 바꿔야합니다!! 근데 지금은 그냥 java.util 친구들이 더 다루기 쉬우니까 ㅎ
     */

    public void setProperties(){
        System.out.println("MovieController initializing...");

        movieOnScreen = store.getMovieOnScreen();
        //movieSoon
        //TODO
        //movieOld
        //TODO

        System.out.println("MovieController initialized.");
    }

    //movie
    @RequestMapping(method = RequestMethod.GET, value = "alarm/movie/ajax/movieListUpdate/")
    public String movieListUpdate(HttpServletRequest request,
                                  Model model,
                                  @RequestParam("movieIds") String movieIds) {
        System.out.println("movieListUpdate : " + request.getSession().getAttribute("kakaoId"));

        String[] strs = movieIds.split(" ");
        List<String> list = Arrays.asList(strs);

        mongoMemberDAO.setMovieByKakaoId(request.getSession().getAttribute("kakaoId").toString(), list);

        System.out.println("movieListUpdate end");
        return "alarm/movie/ajax/movieListUpdate";
    }

    //movie
    @RequestMapping(method = RequestMethod.GET, value = "alarm/movie/")
    public String movie_main(HttpServletRequest request,
                             Model model) {
        System.out.println("movie_main : " + request.getSession().getAttribute("kakaoId"));

        List<String> list = mongoMemberDAO.selectOneByKakaoId(request.getSession().getAttribute("kakaoId").toString()).getMovie_favorites();

        model.addAttribute("movieOnScreen", movieOnScreen);
        model.addAttribute("movieFavorites", list);
        //model.addAttribute("movieSoon", movieSoon);
        //model.addAttribute("movieOld", movieOld);

        System.out.println("movie_main end");
        return "alarm/movie/movie_main";
    }
}
