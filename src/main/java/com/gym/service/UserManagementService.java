package com.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.config.SHA;
import com.gym.mapper.UserManagementMapper;
import com.gym.model.UserModel;

@Service
public class UserManagementService {
	@Autowired
	UserManagementMapper userManagementMapper;
	
	//获取user信息
	public UserModel getUserManagement(int id) {
		UserModel userModel;
		if(id<100000) {		//管理员
			userModel=userManagementMapper.selectUserByName_1(id);
			userModel.setName(userModel.getName()+"管理员");
			return userModel;
		}
		if(id/100000==1) {	//会员
			userModel=userManagementMapper.selectUserByName_2(id);
			userModel.setName(userModel.getName()+"会员");
			return userModel;
		}
		else if(id/100000==6) {	//前台
			userModel=userManagementMapper.selectUserByName_3(id);
			userModel.setName(userModel.getName()+"前台");
			return userModel;
		}
		else if(id/100000==8) {	//教练
			userModel=userManagementMapper.selectUserByName_4(id);
			userModel.setName(userModel.getName()+"教练");
			return userModel;
		}
		else
			return null;
	}
	
	//获取user详细信息
	public UserModel getDetailUserManagement(int id) {
		UserModel userModel;
		if(id<100000) {		//管理员
			userModel=userManagementMapper.selectDetailUserByName_1(id);
			return userModel;
		}
		else if(id/100000==1) {	//会员
			userModel=userManagementMapper.selectDetailUserByName_2(id);
			return userModel;
		}
		else if(id/100000==6) {	//前台
			userModel=userManagementMapper.selectDetailUserByName_3(id);
			return userModel;
		}
		else if(id/100000==8) {	//教练
			userModel=userManagementMapper.selectDetailUserByName_4(id);
			return userModel;
		}
		else
			return null;
	}
	
	//按身份修改用户基本信息
	public void updateUser(UserModel user) {
		UserModel userModel;
		SHA sHA=new SHA();			//实例化SHA对象
		int id=user.getId();
		if(id<100000) {		//管理员
			userModel=userManagementMapper.selectDetailUserByName_1(id);
			if("".equals(user.getPassword()))
				user.setPassword(userModel.getPassword());
			else
				user.setPassword(sHA.getResult(user.getName()+user.getPassword()));		//将用户名+密码的形式加密
			userManagementMapper.updateUser_1(user);
			return ;
		}
		else if(id/100000==1) {	//会员
			userModel=userManagementMapper.selectDetailUserByName_2(id);
			if("".equals(user.getPassword()))
				user.setPassword(userModel.getPassword());
			else
				user.setPassword(sHA.getResult(user.getName()+user.getPassword()));		//将用户名+密码的形式加密
			userManagementMapper.updateUser_2(user);
		}
		else if(id/100000==6) {	//前台
			userModel=userManagementMapper.selectDetailUserByName_3(id);
			if("".equals(user.getPassword()))
				user.setPassword(userModel.getPassword());
			else
				user.setPassword(sHA.getResult(user.getName()+user.getPassword()));		//将用户名+密码的形式加密
			userManagementMapper.updateUser_3(user);
		}
		else if(id/100000==8) {	//教练
			userModel=userManagementMapper.selectDetailUserByName_4(id);
			if("".equals(user.getPassword()))
				user.setPassword(userModel.getPassword());
			else
				user.setPassword(sHA.getResult(user.getName()+user.getPassword()));		//将用户名+密码的形式加密
			userManagementMapper.updateUser_4(user);
		}else
			return ;
	}
}
