package org.shersfy.user.service.impl;

import javax.annotation.Resource;

import org.shersfy.user.mapper.BaseMapper;
import org.shersfy.user.mapper.UserMapper;
import org.shersfy.user.model.User;
import org.shersfy.user.model.UserDetail;
import org.shersfy.user.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long>
	implements UserService{

	@Resource
	private UserMapper mapper;
	
	@Override
	public BaseMapper<User, Long> getMapper() {
		return mapper;
	}


	@Override
	public User findByName(String username) {
		return mapper.findByName(username);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = this.findByName(username);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("user '%s' not exist", username));
		}
		return new UserDetail(user);
	}
	
	public String getAuthorityByLoginId(String username ){
		User user = this.findByName(username);
		return user.getRoles();
	}
}
