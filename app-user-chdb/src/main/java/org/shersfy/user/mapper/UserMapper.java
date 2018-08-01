package org.shersfy.user.mapper;

import org.shersfy.user.model.User;

public interface UserMapper extends BaseMapper<User, Long>{

	User findByName(String username);
	
}