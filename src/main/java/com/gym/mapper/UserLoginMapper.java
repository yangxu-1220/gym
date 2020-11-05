package com.gym.mapper;

import org.apache.ibatis.annotations.Select;

import com.gym.model.UserModel;

public interface UserLoginMapper {
	//通过姓名获取用户姓名和密码
	@Select("select user_name as name,user_password as password from tb_user where user_name=#{name}")
	public UserModel getUserInformation(String name);
	
	//首先执行存储过程，执行查询语句，获取用户的Id
	@Select("call select_id(#{name},@id);SELECT @id as id")
	public int getUserIdByPhone(String name);
}
