package com.gym.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gym.model.StuffManagementModel;

public interface StuffManagementMapper {
//	sql字符串
	String sqlString_1="select * from tb_stuff ";		//查询所有员工（包括前台和管理员）
	String sqlString_2="insert into tb_stuff set stuff_id=#{stuffId},stuff_type=#{stuffType},stuff_name=#{stuffName},stuff_sex=#{stuffSex},stuff_birthday=#{stuffBirthday},"
			+ "idcard_type=#{idcardType},idcard_number=#{idcardNumber},stuff_address=#{stuffAddress},stuff_phone=#{stuffPhone},join_time=#{joinTime},stuff_image=#{stuffImage},stuff_note=#{stuffNote}";
	String sqlString_3="update tb_user set user_password=#{userPassword} where user_name=#{stuffPhone}";
	String sqlString_4="select DISTINCT stuff_type from tb_stuff";		//查询所有员工表中的职位类型
	String sqlString_5="select tb_user.user_password from tb_stuff,tb_user where tb_stuff.stuff_phone=tb_user.user_name and tb_stuff.stuff_phone=#{stuffPhone}";
	String sqlString_6="update tb_stuff set stuff_type=#{stuffType},stuff_name=#{stuffName},stuff_sex=#{stuffSex},stuff_birthday=#{stuffBirthday},"
			+ "idcard_type=#{idcardType},idcard_number=#{idcardNumber},stuff_address=#{stuffAddress},stuff_phone=#{stuffPhone},join_time=#{joinTime},stuff_image=#{stuffImage},stuff_note=#{stuffNote} where stuff_id=#{stuffId}";
	String sqlString_7="delete from tb_stuff where stuff_id=#{stuffId}";
	String sqlString_8="select count(distinct stuff_id) from tb_stuff ";	//统计总数量
	String sqlString_9="select * from tb_user,tb_stuff where stuff_phone=user_name and stuff_id=#{stuffId}";
	String sqlString_10="select count(*) from tb_stuff where stuff_id=#{search} or stuff_name=#{search} ";		//统计指定员工的数量
	
	@Select(sqlString_1+" limit #{page},#{limit} ")		//查询所有员工
	public List<StuffManagementModel> selectStuffByNull(int page,int limit);
	
	@Select(sqlString_8)		//统计总数量
	public int count();
	
	@Insert(sqlString_2)		//插入添加信息语句
	public int insertStuffByStuff(StuffManagementModel stuff);
	
	@Insert(sqlString_3)		//更新tb_user密码
	public int updateStuffByStuff(StuffManagementModel stuff);
	
	@Select(sqlString_4)		//查询员工职位（消除重复值）
	public List<StuffManagementModel> selectStuffTypeByNull();
	
	@Select(sqlString_5)		//根据用户名（电话号码）查询用户密码
	public String selectStuffByPhone(String stuffPhone);
	
	@Select(sqlString_9)	//按stuffId查询员工信息
	public StuffManagementModel selectStuffByStuffId(int stuffId);
	
	@Update(sqlString_6)		//根据员工ID修改员工信息
	public int updateStuffById(int stuffId,String stuffType,String stuffName,String stuffSex,String stuffBirthday,String idcardType,String idcardNumber,String stuffAddress,String stuffPhone,String joinTime,String stuffNote,String stuffImage);
	
	@Delete(sqlString_7)		//根据员工ID删除员工信息
	public void deleteStuffById(String stuffId);
	
	@Select(sqlString_1+" where stuff_id=#{search} or stuff_name=#{search} limit #{page},#{limit} ")		//查询指定员工
	public List<StuffManagementModel> selectStuffByStuffIdorStuffName(String search,int page,int limit);
	
	@Select(sqlString_10)		//统计指定查询员工数量
	public int countByStuffIdorStuffName(String search);
}
