package com.enlight.yaadle.uplift;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.util.StringUtils;

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
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			                 @RequestParam(value = "logout", required = false) String logout,
			                 HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		
		logger.info("Login Home Page");
		
		if (error != null) 
		{
			String targetUrl = getRememberMeTargetUrlFromSession(request);
			logger.info("targetURL: "+targetUrl);
			if(StringUtils.hasText(targetUrl))
			 {
		       model.addAttribute("targetUrl", targetUrl);
			   model.addAttribute("loginUpdate", true);
		     }
		  
	   }
		if (logout != null) {
			model.addAttribute("msg", "You've been logged out successfully.");
		}
	  return "home";
    }
	
	@RequestMapping(value= {"/landing","/_=_","/#!"}, method = RequestMethod.GET)
	public String landing(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		logger.info("Landing Page --");
		return "landing";
	}
	

	@RequestMapping(value = "error", method = RequestMethod.GET)
	public String errorPage(HttpServletRequest request, HttpServletResponse response) {
		return "error";
	}
	
	//---ADDED FOR REMEMBER ME SERVICES
	
	@SuppressWarnings("unused")
	private boolean isRememberMeAuthenticated() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}

		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}
	
	@SuppressWarnings("unused")
	private void setRememberMeTargetUrlToSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.setAttribute("targetUrl", "/admin/update");
		}
	}
	
	@SuppressWarnings("unused")
	private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		if(session!=null){
			targetUrl = session.getAttribute("targetUrl")==null?""
                             :session.getAttribute("targetUrl").toString();
		}
		return targetUrl;
	}
}
