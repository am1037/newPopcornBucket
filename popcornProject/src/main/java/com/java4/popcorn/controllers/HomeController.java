package com.java4.popcorn.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	//alarm
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String home(HttpServletRequest request,
					   Model model) {
		//사용자 특정 관련.. 세션과 쿠키 등?
		HttpSession session = request.getSession();

		if(session.getAttribute("kakaoId") == null){
			System.out.println("kakaoId is null");
		}else {
			System.out.println("kakaoId is " + session.getAttribute("kakaoId"));
		}

		return "home";
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

}
