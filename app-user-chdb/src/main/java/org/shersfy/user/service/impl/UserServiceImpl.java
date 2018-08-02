package org.shersfy.user.service.impl;

import javax.annotation.Resource;

import org.shersfy.user.mapper.BaseMapper;
import org.shersfy.user.mapper.UserMapper;
import org.shersfy.user.mapper.annotation.UserDao;
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
	
	@Resource
	private UserDao userDao;
	
	@Override
	public BaseMapper<User, Long> getMapper() {
		return mapper;
	}


	@Override
	public User findByName(String username) {
		return mapper.findByName(username);
	}


    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }


    @Override
    public int updateById(User entity) {
        return userDao.updateById(entity);
    }
	
}
