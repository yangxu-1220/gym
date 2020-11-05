package com.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.UserShiroMapper;
import com.gym.model.UserModel;

@Service
public class UserShiroService {
	@Autowired
	UserShiroMapper userShiroMapper;
	
	public UserModel getUserModelByName(String name) {
		return userShiroMapper.selectUserModelByName(name);
	}
}
