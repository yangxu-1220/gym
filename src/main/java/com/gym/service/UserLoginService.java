package com.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.config.SHA;
import com.gym.mapper.UserLoginMapper;
import com.gym.model.UserModel;

@Service
public class UserLoginService {
	@Autowired
	UserLoginMapper userLoginMapper;
	
    //获取登录账号和密码
	public UserModel getUserInformation(String name,String password) {
		SHA sHA=new SHA();
		String pass=sHA.getResult(name+password);				//加密
		UserModel user=userLoginMapper.getUserInformation(name);
		if(user.getName()==null) {
			user.setStatus(1);
			System.out.println("status="+user.getStatus());
		}else if(!(user.getPassword().equals(pass))) {
			user.setStatus(2);
			System.out.println("status="+user.getStatus());
//			user.getPassword().equals(userModel.getPassword())
		}else {
			user.setStatus(3);
			System.out.println("status="+user.getStatus());
		}
		return user;
	}
	
	//通过电话号码获取用户的Id
	public int getUserIdByPhone(String name) {
		return userLoginMapper.getUserIdByPhone(name);
	}
}
