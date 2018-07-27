package org.shersfy.user.controller;

import javax.annotation.Resource;

import org.shersfy.user.model.User;
import org.shersfy.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	
	@GetMapping("/list")
	public Object list(User where){
		return userService.findPage(where, 1, 10);
	}
	
}
