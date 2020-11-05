package com.gym.mapper;

import org.apache.ibatis.annotations.Select;

import com.gym.model.UserModel;

public interface UserShiroMapper {
	@Select("select user_name as name,user_password as password,user_perms as perms from tb_user where user_name=#{name}")
	public UserModel selectUserModelByName(String name);
}
