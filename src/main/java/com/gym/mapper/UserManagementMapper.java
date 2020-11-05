package com.gym.mapper;

import org.apache.ibatis.annotations.Select;

import com.gym.model.UserModel;

public interface UserManagementMapper {
//	sql字符串
	//查询管理员姓名和照片
	String sqlString_1="SELECT stuff_name as name,stuff_image as image " + 
			"FROM tb_stuff " + 
			"where stuff_id=#{id}";
	//查询会员姓名和照片
	String sqlString_2="SELECT member_name as name,member_image as image " + 
			"FROM tb_member " + 
			"where member_id=#{id}";
	//查询前台姓名和照片
	String sqlString_3="SELECT stuff_name as name,stuff_image as image " + 
			"FROM tb_stuff " + 
			"where stuff_id=#{id}";
	//查询教练姓名和照片
	String sqlString_4="SELECT trainer_name as name,trainer_image as image " + 
			"FROM tb_trainer " + 
			"where trainer_id=#{id}";
	
	
	//查询管理员基本信息
	String sqlString_5="select user_name as name,stuff_name as userName,user_password as password,stuff_image as image " + 
			"from tb_user,tb_stuff " + 
			"where tb_user.user_name=tb_stuff.stuff_phone and stuff_id=#{id}";
	//查询会员基本信息
	String sqlString_6="select user_name as name,member_name as userName,user_password as password,member_image as image " + 
			"from tb_user,tb_member " + 
			"where tb_user.user_name=tb_member.member_phone and member_id=#{id}";
	//查询前台基本信息
	String sqlString_7="select user_name as name,stuff_name as userName,user_password as password,stuff_image as image " + 
			"from tb_user,tb_stuff " + 
			"where tb_user.user_name=tb_stuff.stuff_phone and stuff_id=#{id}";
	//查询教练基本信息
	String sqlString_8="select user_name as name,trainer_name as userName,user_password as password,trainer_image as image " + 
			"from tb_user,tb_trainer " + 
			"where tb_user.user_name=tb_trainer.trainer_phone and trainer_id=#{id}";
	
	
	//更新管理员基本信息
	String sqlString_9="update tb_user set user_password=#{password} where user_name=#{name};" + 
			"update tb_stuff set stuff_image=#{image} where stuff_id=#{id}";
	//更新会员基本信息
	String sqlString_10="update tb_user set user_password=#{password} where user_name=#{name};"
			+ "update tb_member set member_image=#{image} where member_id=#{id}";
	//更新前台基本信息
	String sqlString_11="update tb_user set user_password=#{password} where user_name=#{name};"
			+ "update tb_stuff set stuff_image=#{image} where stuff_id=#{id}";
	//更新教练基本信息
	String sqlString_12="update tb_user set user_password=#{password} where user_name=#{name};"
			+ "update tb_trainer set trainer_image=#{image} where trainer_id=#{id}";
	
	@Select(sqlString_1)		//管理员
	public UserModel selectUserByName_1(int id);
	
	@Select(sqlString_2)		//会员
	public UserModel selectUserByName_2(int id);
	
	@Select(sqlString_3)		//前台
	public UserModel selectUserByName_3(int id);
	
	@Select(sqlString_4)		//教练
	public UserModel selectUserByName_4(int id);
	
	@Select(sqlString_5)		//管理员详情
	public UserModel selectDetailUserByName_1(int id);
	
	@Select(sqlString_6)		//会员详情
	public UserModel selectDetailUserByName_2(int id);
	
	@Select(sqlString_7)		//前台详情
	public UserModel selectDetailUserByName_3(int id);
	
	@Select(sqlString_8)		//教练详情
	public UserModel selectDetailUserByName_4(int id);
	
	@Select(sqlString_9)		//修改管理员信息
	public void updateUser_1(UserModel user);
	
	@Select(sqlString_10)		//修改会员信息
	public void updateUser_2(UserModel user);
	
	@Select(sqlString_11)		//修改前台信息
	public void updateUser_3(UserModel user);
	
	@Select(sqlString_12)		//修改教练信息
	public void updateUser_4(UserModel user);
}
