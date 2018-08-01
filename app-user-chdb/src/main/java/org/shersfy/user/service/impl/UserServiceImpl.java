package org.shersfy.user.service.impl;

import javax.annotation.Resource;

import org.shersfy.user.mapper.BaseMapper;
import org.shersfy.user.mapper.UserMapper;
import org.shersfy.user.model.User;
import org.shersfy.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
	
}
