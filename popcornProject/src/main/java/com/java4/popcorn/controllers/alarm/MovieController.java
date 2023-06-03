package com.java4.popcorn.controllers.alarm;

import com.java4.popcorn.database.mongo.member.MongoMemberDAO;
import com.java4.popcorn.database.mongo.member.MongoMemberVO;
import com.java4.popcorn.database.screen.ScreenDAO;
import com.java4.popcorn.database.screen.ScreenVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MovieController {
    final
    ScreenDAO screenDAO;
    final
    MongoMemberDAO mongoMemberDAO;
    final
    SharedPropertiesStore store;
    final
    MovieService movieService;

    public MovieController(ScreenDAO screenDAO, MongoMemberDAO mongoMemberDAO, SharedPropertiesStore store, MovieService movieService){
        this.screenDAO = screenDAO;
        this.mongoMemberDAO = mongoMemberDAO;
        this.store = store;
        this.movieService = movieService;
    }

    //movie
    @RequestMapping(method = RequestMethod.GET, value = "alarm/movie/")
    public String movie_main(HttpServletRequest request,
                             Model model) {
        System.out.println("movie_main");


        //개인 식별 과정입니다
        String kakaoId;
        MongoMemberVO mmvo;
        try {
            kakaoId = request.getSession().getAttribute("kakaoId").toString();
            mmvo = mongoMemberDAO.selectOneByKakaoId(kakaoId);
        }catch (NullPointerException e){
            return "redirect:/login";
        }

        //상영 중인 영화 목록을 가져오는 과정입니다
        Map<String, String> movieOnScreen;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date1 = sdf.format(new Date());
        String date2 = sdf.format(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 4));
        List<String> dateList = store.betweenTwoDate(date1, date2);

        movieOnScreen = new HashMap<>();
        List<ScreenVO> screenVOList = new ArrayList<>();
        for(String d : dateList) {
            screenVOList.addAll(screenDAO.selectByDate(d));
        }
        for(ScreenVO vo : screenVOList) {
            movieOnScreen.put(vo.getMovie_docid(), vo.getTitle());
        }
        movieOnScreen.remove("!NOT FOUND");

        //즐겨찾기 영화 목록을 가져오는 과정입니다
        List<String> movieFavorites;
        movieFavorites = mmvo.getMovie_favorites();

        model.addAttribute("movieOnScreen", movieOnScreen);
        model.addAttribute("movieFavorites", movieFavorites);
        //model.addAttribute("movieSoon", movieSoon);
        //model.addAttribute("movieOld", movieOld);

        System.out.println("movie_main end");
        return "alarm/movie/movie_main";
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


}
