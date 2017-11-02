package com.enlight.yaadle.uplift;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.enlight.yaadle.uplift.user.UserDao;
import com.enlight.yaadle.uplift.user.User;

@Controller
@ComponentScan
@RequestMapping(value = "/register")
public class RegistrationController {
	
	private final Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	  @Autowired
	  UserDao userService;
	  
	  @Autowired
	  ApplicationEventPublisher eventPublisher;
	 
	  
	@RequestMapping(method = RequestMethod.POST)
	public String processRegistration(@RequestParam ("email") String email,@RequestParam ("displayname") String displayName,
			                          @RequestParam ("password") String password,HttpServletRequest request, HttpServletResponse response,Model model) {
	    logger.info("Display Name: "+ displayName + " Display Email: "+ email + " Display Password: "+ password  );
	    User user=new User(email,displayName,password);
	    userService.register(user);
		return "postregister";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String postRegistration(HttpServletRequest request, HttpServletResponse response ) {
	    return "postregister";
	}
	
		
}

