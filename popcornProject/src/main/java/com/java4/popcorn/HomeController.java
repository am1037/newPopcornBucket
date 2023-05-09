package com.java4.popcorn;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import com.java4.popcorn.actor.ActorDAO;
import com.java4.popcorn.actor.ActorVO;
import com.java4.popcorn.movieJjim.MovieJjimVO;
import com.java4.popcorn.rankActor.RankActorVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//
//		String formattedDate = dateFormat.format(date);
//
//		model.addAttribute("serverTime", formattedDate );
//
//		return "home";
//	}

	@RequestMapping(method = RequestMethod.GET, value = "/go")
	public String test(@RequestParam(value = "name", required = false) String name, Model model) {
		System.out.println("name: "+name);
		return name+"/first";
	}
}
