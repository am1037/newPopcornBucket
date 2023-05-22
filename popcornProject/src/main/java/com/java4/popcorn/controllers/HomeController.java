package com.java4.popcorn.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//alarm
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String home(Model model) {
		//사용자 특정 관련.. 세션과 쿠키 등?
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/alarm_main")
	public String alarm_main(Model model) {
		return "alarm/alarm_main";
	}

	//theater
	@RequestMapping(method = RequestMethod.GET, value = "/theater_main")
	public String theater_main(Model model) {
		return "theater/theater_main";
	}

	//movie
	@RequestMapping(method = RequestMethod.GET, value = "/movie_main")
	public String movie_main(Model model) {
		return "movie/movie_main";
	}

}
