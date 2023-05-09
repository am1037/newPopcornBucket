package com.java4.popcorn;

import com.java4.popcorn.actor.ActorDAO;
import com.java4.popcorn.actor.ActorVO;
import com.java4.popcorn.bbs.BbsDAO;
import com.java4.popcorn.bbs.BbsVO;
import com.java4.popcorn.movieInfo.MovieDAO;
import com.java4.popcorn.movieInfo.MovieVO;
import com.java4.popcorn.movieJjim.MovieJjimVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    @Autowired
    ActorDAO dao1;
    @Autowired
    BbsDAO dao2;
    @Autowired
    MovieDAO dao3;
    @RequestMapping(method = RequestMethod.GET, value = "/s")
    public void selectTest(@RequestParam(value = "id", required = false) int id, Model model) {
        ActorVO vo = new ActorVO();
        vo.setActorId(id);
        System.out.println(dao1.selectOne(vo));
        dao1.printAddress();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/i")
    public void insertTest(@RequestParam(value = "id", required = false) int id,
                           @RequestParam(value = "name", required = false) String name, Model model) {
        ActorVO vo = new ActorVO();
        vo.setActorId(id);
        vo.setActorName(name);
        System.out.println(dao1.insertOne(vo));
        dao1.printAddress();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/sbbs")
    public void selectTest2(@RequestParam(value = "id", required = false) int id, Model model) {
        BbsVO vo = new BbsVO();
        vo.setBbs_Id(id);
        System.out.println(dao2.selectOne(vo));
        dao2.printAddress();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ibbs")
    public void insertTest2(@RequestParam(value = "id", required = false) int id,
                           @RequestParam(value = "title", required = false) String title, Model model) {
        BbsVO vo = new BbsVO();
        vo.setBbs_Id(id);
        vo.setBbs_Title(title);
        System.out.println(dao2.insertOne(vo));
        dao2.printAddress();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/smovie")
    public void insertTest3(@RequestParam(value = "id", required = false) int id,
                            @RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "genre", required = false) String genre, Model model) {
        MovieVO vo = new MovieVO();
        vo.setMovieid(id);
        vo.setMovieTitle(title);
        vo.setMovieGenre(genre);
        System.out.println(dao3.insertOne(vo));
    }

}
