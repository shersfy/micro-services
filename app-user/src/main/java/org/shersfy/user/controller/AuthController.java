package org.shersfy.user.controller;


import java.security.Principal;

import javax.annotation.Resource;
import org.shersfy.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RefreshScope
public class AuthController {
	
	@Resource
    private UserService userService;
	@Value("${version}")
	private String version;
	
	@RequestMapping("/index.html")
	public ModelAndView index(Principal principal) {
		ModelAndView mav = new ModelAndView("login");
		if(principal == null) {
			return mav;
		}
		mav.setViewName("index");
		mav.addObject("username", principal.getName());
		return mav;
	}
	
	@RequestMapping("/")
	public ModelAndView index() {
		return  new ModelAndView("redirect:/index.html");
	}
	
	@RequestMapping("/index")
	public ModelAndView indexHtml() {
		return  new ModelAndView("redirect:/index.html");
	}

	@RequestMapping("/loginError")
	public ModelAndView loginError() {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("loginError", true);
		return mav;
	}

	@RequestMapping("/expired")
	public ModelAndView sessionExpired() {
		ModelAndView mav = new ModelAndView("expired");
		mav.addObject("loginError", true);
		return mav;
	}
	
	@RequestMapping("/version")
	@ResponseBody
	public String version() {
	    return version;
	}
	
}
