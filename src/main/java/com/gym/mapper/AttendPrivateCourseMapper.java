package com.gym.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import com.gym.model.AttendPrivateCourseModel;

public interface AttendPrivateCourseMapper {
//	sql字符串
	String sqlString="select attend_private_time as courseTime,attend_private_place as coursePlace,tb_trainer.trainer_name as trainerName,tb_private_course.private_course_name as courseName,tb_member.member_name as memberName,tb_member.member_id as memberId " + 
			"from tb_attend_private,tb_trainer,tb_private_course,tb_member " + 
			"where tb_attend_private.trainer_id=tb_trainer.trainer_id and tb_attend_private.private_course_id=tb_private_course.private_course_id and tb_attend_private.member_id=tb_member.member_id and attend_private_time>=#{startTime} and attend_private_time<=#{endTime}";
	String sqlString_2="select private_course_name as courseName from tb_private_course";
	//绘制饼图1，统计教练和教练上课次数，借用coursePlace字段当作教练上课次数
	String sqlString_3="select trainer_name as trainerName,COUNT(trainer_name) as coursePlace " + 
			"from tb_attend_private,tb_trainer " + 
			"where tb_attend_private.trainer_id=tb_trainer.trainer_id and attend_private_time>=#{startTime} and attend_private_time<=#{endTime}" + 
			"GROUP BY trainer_name";
	//绘制饼图2，统计私教课程和私教课程上课次数，借用coursePlace字段当作私教课程上课次数
	String sqlString_4="select private_course_name as courseName,COUNT(private_course_name) as coursePlace " + 
			"from tb_attend_private,tb_private_course " + 
			"where tb_attend_private.private_course_id=tb_private_course.private_course_id and attend_private_time>=#{startTime} and attend_private_time<=#{endTime}" + 
			"GROUP BY private_course_name";
	//统计总数量
	String sqlString_5="select count(*) " + 
			"from tb_attend_private,tb_trainer,tb_private_course,tb_member " + 
			"where tb_attend_private.trainer_id=tb_trainer.trainer_id and tb_attend_private.private_course_id=tb_private_course.private_course_id and tb_attend_private.member_id=tb_member.member_id and attend_private_time>=#{startTime} and attend_private_time<=#{endTime}";;
	
//	按Null查询
	@Select(sqlString+" limit #{page},#{limit}")
	public List<AttendPrivateCourseModel> selectAttendPrivateCourseByNull(String startTime,String endTime,int page,int limit);
	
	@Select(sqlString_5)
	public int count_1(String startTime,String endTime);
	
//	按教练查询
	@Select(sqlString+" and tb_trainer.trainer_name=#{trainerName} limit #{page},#{limit}")
	public List<AttendPrivateCourseModel> selectAttendPrivateCourseByTrainer(String startTime,String endTime,String trainerName,int page,int limit);
	
	@Select(sqlString_5+" and tb_trainer.trainer_name=#{trainerName}")
	public int count_2(String startTime,String endTime,String trainerName);
	
//	按课程名查询
	@Select(sqlString+" and tb_private_course.private_course_name=#{courseName} limit #{page},#{limit}")
	public List<AttendPrivateCourseModel> selectAttendPrivateCourseByCourse(String startTime,String endTime,String courseName,int page,int limit);
	
	@Select(sqlString_5+" and tb_private_course.private_course_name=#{courseName} ")
	public int count_3(String startTime,String endTime,String courseName);
	
//	按会员查询
	@Select("select * from ("+sqlString+") as temp where memberName=#{memberName} or memberId=#{memberName} limit #{page},#{limit}")
	public List<AttendPrivateCourseModel> selectAttendPrivateCourseByMember(String startTime,String endTime,String memberName,int page,int limit);
	
	@Select("select count(DISTINCT tb_member.member_id) from ("+sqlString+") as temp where memberName=#{memberName} or memberId=#{memberName} ")
	public int count_4(String startTime,String endTime,String memberName);
	
//	按教练，课程查询
	@Select(sqlString+" and tb_trainer.trainer_name=#{trainerName} and tb_private_course.private_course_name=#{courseName} limit #{page},#{limit}")
	public List<AttendPrivateCourseModel> selectAttendPrivateCourseByTrainerCourse(String startTime,String endTime,String trainerName,String courseName,int page,int limit);
	
	@Select(sqlString_5+" and tb_trainer.trainer_name=#{trainerName} and tb_private_course.private_course_name=#{courseName} ")
	public int count_5(String startTime,String endTime,String trainerName,String courseName);

//	按教练，会员查询
	@Select("select * from ("+sqlString+" and tb_trainer.trainer_name=#{trainerName}) as temp where memberName=#{memberName} or memberId=#{memberName} limit #{page},#{limit}")
	public List<AttendPrivateCourseModel> selectAttendPrivateCourseByTrainerMember(String startTime,String endTime,String trainerName,String memberName,int page,int limit);
	
	@Select("select count(DISTINCT tb_member.member_id) from ("+sqlString+" and tb_trainer.trainer_name=#{trainerName}) as temp where memberName=#{memberName} or memberId=#{memberName} ")
	public int count_6(String startTime,String endTime,String trainerName,String memberName);

//	按课程，会员查询
	@Select("select * from ("+sqlString+" and tb_private_course.private_course_name=#{courseName}) as temp where memberName=#{memberName} or memberId=#{memberName} limit #{page},#{limit}")
	public List<AttendPrivateCourseModel> selectAttendPrivateCourseByCourseMember(String startTime,String endTime,String courseName,String memberName,int page,int limit);
	
	@Select("select count(DISTINCT tb_member.member_id) from ("+sqlString+" and tb_private_course.private_course_name=#{courseName}) as temp where memberName=#{memberName} or memberId=#{memberName} ")
	public int count_7(String startTime,String endTime,String courseName,String memberName);
	
//	按教练，课程和会员查询
	@Select("select * from ("+sqlString+" and tb_trainer.trainer_name=#{trainerName} and tb_private_course.private_course_name=#{courseName}) as temp where memberName=#{memberName} or memberId=#{memberName} limit #{page},#{limit}")
	public List<AttendPrivateCourseModel> selectAttendPrivateCourseByTrainerCourseMember(String startTime,String endTime,String trainerName,String courseName,String memberName,int page,int limit);
	
	@Select("select count(DISTINCT tb_member.member_id) from ("+sqlString+" and tb_trainer.trainer_name=#{trainerName} and tb_private_course.private_course_name=#{courseName}) as temp where memberName=#{memberName} or memberId=#{memberName} ")
	public int count_8(String startTime,String endTime,String trainerName,String courseName,String memberName);

//	查询所有私教课程
	@Select(sqlString_2)
	public List<AttendPrivateCourseModel> selectCourseName();
	
	//绘制饼图1，统计教练和教练上课次数，借用coursePlace字段当作教练上课次数
	@Select(sqlString_3)
	public List<AttendPrivateCourseModel> selectAttendPrivateCourseonPie_1(String startTime,String endTime);
	
	//绘制饼图2，统计私教课程和私教课程上课次数，借用coursePlace字段当作私教课程上课次数
	@Select(sqlString_4)
	public List<AttendPrivateCourseModel> selectAttendPrivateCourseonPie_2(String startTime,String endTime);
}
