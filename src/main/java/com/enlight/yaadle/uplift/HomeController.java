package com.enlight.yaadle.uplift;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	private final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping(value= {"/","/home"})
	public String home() {
		logger.info("Landed Home Page with /");
		return "home";
	}
	
	/*@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model, @RequestParam(value = "error",required = false) String error,
	@RequestParam(value = "logout",	required = false) String logout,HttpServletRequest request,HttpServletResponse response) {
		logger.info("Landed Home Page");
		return "home";
	}*/
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(HttpServletRequest request,HttpServletResponse response) {
		logger.info("Login Home Page");
		return "home";
	}
	
	@RequestMapping(value= {"/landing"}, method = RequestMethod.GET)
	public String landing(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		return "landing";
	}
	

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String errorPage(HttpServletRequest request, HttpServletResponse response) {
		return "error";
	}
	
}
