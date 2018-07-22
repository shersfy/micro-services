package org.shersfy.user.service;


import org.shersfy.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends BaseService<User, Long>, 
	UserDetailsService{
	
	User findByName(String username);

}
