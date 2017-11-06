package com.enlight.yaadle.uplift;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@SuppressWarnings("unused")
@Controller
public class LoginController implements ErrorController{
	
	
	@Autowired 
	 private HttpSession httpSession;
	
    private ErrorAttributes errorAttributes;

    private final static String ERROR_PATH = "/error";
    
    public LoginController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }
	 
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@RequestMapping(value= {"/","/home","/login"})
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
	
	/*@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			                 @RequestParam(value = "logout", required = false) String logout,
			                 HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		
		logger.info("Login Home Page"+ error);
		String referrer = request.getHeader("Referer");
		request.getSession().setAttribute("url_prior_login", referrer);
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
    }*/
	
	@RequestMapping(value= {"/landing","/_=_","/#!"}, method = RequestMethod.GET)
	public String landing(HttpServletRequest request, HttpServletResponse response,
			               ModelMap model) {
		String header=request.toString();
        Principal userPrincipal=request.getUserPrincipal();
		String username=userPrincipal.getName();
		logger.info("Landing Page --" + header + "\nPrincipal" + userPrincipal.getName());
		return "redirect:/landing_=_";
	}
	

	@RequestMapping(value = ERROR_PATH, method = RequestMethod.GET, produces = "text/html")
	public String errorPage(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Error Page --");
		return "error";
	}
	
	//---ADDED FOR REMEMBER ME SERVICES
	
	private boolean isRememberMeAuthenticated() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}

		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}

	private void setRememberMeTargetUrlToSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session!=null){
			session.setAttribute("targetUrl", "/admin/update");
		}
	}
	
	private String getRememberMeTargetUrlFromSession(HttpServletRequest request){
		String targetUrl = "";
		HttpSession session = request.getSession(false);
		if(session!=null){
			targetUrl = session.getAttribute("targetUrl")==null?""
                             :session.getAttribute("targetUrl").toString();
		}
		return targetUrl;
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
