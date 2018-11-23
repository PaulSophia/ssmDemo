package com.paul.ssm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paul.ssm.domain.User;
import com.paul.ssm.mapper.UserMapper;
import com.paul.ssm.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;

	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public User getUserById(Long id) {
		User user = this.userMapper.selectByPrimaryKey(id);
		return user;
	}
	
}
