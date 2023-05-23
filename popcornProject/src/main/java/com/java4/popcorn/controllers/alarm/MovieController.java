package com.java4.popcorn.controllers.alarm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovieController {

    //movie
    @RequestMapping(method = RequestMethod.GET, value = "alarm/movie/")
    public String movie_main(Model model) {
        return "alarm/movie/movie_main";
    }
}
