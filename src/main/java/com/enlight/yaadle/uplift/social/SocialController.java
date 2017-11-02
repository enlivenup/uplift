package com.enlight.yaadle.uplift.social;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SocialController {

	@RequestMapping("/unauthenticated") 
	public String unauthenticated() { return "redirect:/?error=true"; }
}
