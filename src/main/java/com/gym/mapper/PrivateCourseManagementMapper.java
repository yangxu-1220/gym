package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.gym.model.PrivateCourseForBoughtModel;
import com.gym.model.PrivateCourseForBuyModel;
import com.gym.model.PrivateCourseForSelectModel;

public interface PrivateCourseManagementMapper {
//	sql字符串
	//根据会员ID/教练ID查询上课详情
	String sqlString_1="select member_name as memberName,trainer_name as trainerName,private_course_name as privateCourseName,attend_private_time as attendPrivateTime,attend_private_place as attendPrivatePlace,private_course_length as attendPrivateLenght,tb_member.member_id as memberId,tb_trainer.trainer_id as trainerId " + 
			"from tb_attend_private,tb_private_course,tb_member,tb_trainer " + 
			"where tb_attend_private.private_course_id=tb_private_course.private_course_id and tb_attend_private.member_id=tb_member.member_id and tb_attend_private.trainer_id=tb_trainer.trainer_id";
	//查询私教课程
	String sqlString_2="select private_course_name as privateCourseName,private_course_content as privateCourseContext,private_course_length as privateCourseLength,private_course_price as privateCoursePrice " + 
			"from tb_private_course";
	//通过私教课程名查询到购买该私教课程的信息
	String sqlString_3="select private_course_name as privateCourseName,private_course_content as privateCourseContext,private_course_length as privateCourseLength,private_course_price as privateCoursePrice " + 
			"from tb_private_course " + 
			"where private_course_name=#{privateCourseName}";
	//查询私教课程的ID和名称，以便转换
	String sqlString_4="select private_course_id as privateCourseId,private_course_name as privateCourseName " + 
			"from tb_private_course ";
	//查询私教教练的ID和姓名，以便转换
	String sqlString_5="select trainer_id as trainerId,trainer_name as trainerName " + 
			"from tb_trainer ";
	//将付款成功之后的买私教课信息写入数据库
	String sqlString_6="insert into tb_buy_private_course " + 
			"set private_course_id=#{privateCourseId},trainer_id=#{trainerId},member_id=#{memberId},buy_private_course_time=#{date},buy_private_course_number=#{privateCourseNumber} ";
	//查询会员私教课程购买情况
	String sqlString_7="select tb_member.member_id as memberId,tb_trainer.trainer_id as trainerId,private_course_name as privateCourseName,trainer_name as trainerName,member_name as memberName,buy_private_course_time as buyPrivateCourseTime,buy_private_course_number as buyPrivateCourseNumber,buy_private_course_price as buyPrivateCoursePrice " + 
			"from tb_buy_private_course,tb_private_course,tb_trainer,tb_member " + 
			"where tb_buy_private_course.private_course_id=tb_private_course.private_course_id and tb_buy_private_course.trainer_id=tb_trainer.trainer_id and tb_buy_private_course.member_id=tb_member.member_id ";
	//统计总数量
	String sqlString_8="select count(DISTINCT private_course_name) " + 
			"from tb_private_course";
	//统计总数量2
	String sqlString_9="select count(DISTINCT tb_trainer.trainer_id) " + 
			"from tb_buy_private_course,tb_private_course,tb_trainer,tb_member " + 
			"where tb_buy_private_course.private_course_id=tb_private_course.private_course_id and tb_buy_private_course.trainer_id=tb_trainer.trainer_id and tb_buy_private_course.member_id=tb_member.member_id ";
	//统计数量3
	String sqlString_10="select count(distinct tb_member.member_id) " + 
			"from tb_attend_private,tb_private_course,tb_member,tb_trainer " + 
			"where tb_attend_private.private_course_id=tb_private_course.private_course_id and tb_attend_private.member_id=tb_member.member_id and tb_attend_private.trainer_id=tb_trainer.trainer_id and attend_private_time>=#{nowTime} ";

	
	//根据会员ID查询会员上课详情
	@Select("select * from ("+sqlString_1+") as temp where memberId=#{id} or trainerId=#{id} limit #{page},#{limit} ")
	public List<PrivateCourseForSelectModel> selectPrivateCourseForSelectByMemberId(String nowTime,int id,int page,int limit);
	
	//统计数量3
	@Select("select count(*) from ("+sqlString_1+") as temp where memberId=#{id} or trainerId=#{id} ")
	public int count_3(String nowTime,int id);
	
	//根据教练ID查询教练上课详情
	@Select("select * from ("+sqlString_1+") as temp where memberId=#{id} or trainerId=#{id} limit #{page},#{limit} ")
	public List<PrivateCourseForSelectModel> selectPrivateCourseForSelectByTrainerId(String nowTime,int id,int page,int limit);
	
	//根据教练ID查询教练上课详情
	@Select(sqlString_10+"and tb_trainer.trainer_id=#{trainerId}")
	public int count_4(String nowTime,int trainerId);
	
	//查询私教课程
	@Select(sqlString_2+" limit #{page},#{limit} ")
	public List<PrivateCourseForBuyModel> selectPrivateCourseForBuy(int page,int limit);
	
	//查询私教课程
	@Select(sqlString_8)
	public int count_1();
	
	//通过私教课程名查询到购买该私教课程的信息
	@Select(sqlString_3)
	public PrivateCourseForBuyModel selectPrivateCourseForBuyByPrivateCourseName(String privateCourseName);
	
	//查询私教课程的ID和名称，以便转换
	@Select(sqlString_4)
	public List<PrivateCourseForBuyModel> selectPrivateCourse();
	
	//查询私教教练的ID和姓名，以便转换
	@Select(sqlString_5)
	public List<PrivateCourseForBuyModel> selectTrainer();
	
	//将付款成功之后的买私教课信息写入数据库
	@Insert(sqlString_6)
	public void insertPrivateCourseForBuy(int privateCourseId,int trainerId,int memberId,String date,String privateCourseNumber);
	
	//通过会员ID查询会员私教课程购买情况
	@Select("select * from ("+sqlString_7+") as temp where memberId=#{id} or trainerId=#{id} limit #{page},#{limit} ")
	public List<PrivateCourseForBoughtModel> selectPrivateCourseForBought(int id,int page,int limit);
	
	//统计数量2
	@Select("select count(*) from ("+sqlString_7+") as temp where memberId=#{id} or trainerId=#{id} ")
	public int count_2(int id);
	
	//通过教练ID查询该教练私教课程售卖情况
	@Select(sqlString_7+" and tb_trainer.trainer_id=#{trainerId}")
	public List<PrivateCourseForBoughtModel> selectPrivateCourseForAttend(int trainerId);
}
