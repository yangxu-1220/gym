package com.gym.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import com.gym.model.AttendCourseChartModel;

public interface AttendCourseChartMapper {
//	sql字符串
	String sqlString_1="select tb_attend_course.attend_course_time as courseTime,tb_attend_course.attend_course_palce as coursePlace,tb_course.course_name as courseName,tb_trainer.trainer_name as trainerName " + 
			"from tb_attend_course,tb_course,tb_trainer " + 
			"where tb_attend_course.attend_course_id=tb_course.course_id and tb_attend_course.trainer_id=tb_trainer.trainer_id and attend_course_time>=#{startTime} and attend_course_time<=#{endTime} ";
	String sqlString_2="select trainer_name from tb_trainer";
	//绘制饼图1，统计课程和课程被上课次数，借用coursePlace字段当作课程被上课次数
	String sqlString_3="select course_name as courseName,COUNT(course_name) as coursePlace " + 
			"from tb_attend_course,tb_course " + 
			"where tb_attend_course.attend_course_id=tb_course.course_id and attend_course_time>=#{startTime} and attend_course_time<=#{endTime} " + 
			"GROUP BY course_name";
	//绘制饼图2，统计教练和教练上课次数，借用coursePlace字段当作教练上课上课次数
	String sqlString_4="select trainer_name as trainerName,COUNT(trainer_name) as coursePlace " + 
			"from tb_attend_course,tb_trainer " + 
			"where tb_attend_course.trainer_id=tb_trainer.trainer_id and attend_course_time>=#{startTime} and attend_course_time<=#{endTime} " + 
			"GROUP BY trainer_name";
	//统计总数量
	String sqlString_5="select count(*) " + 
			"from tb_attend_course,tb_course,tb_trainer " + 
			"where tb_attend_course.attend_course_id=tb_course.course_id and tb_attend_course.trainer_id=tb_trainer.trainer_id and attend_course_time>=#{startTime} and attend_course_time<=#{endTime} ";;
	
	@Select(sqlString_1+" limit #{page},#{limit}")
	public List<AttendCourseChartModel> selectAttendCourseByNull(String startTime,String endTime,int page,int limit);
	
	@Select(sqlString_5)			//统计总数量
	public int Count_1(String startTime,String endTime);
	
	@Select(sqlString_1+" and tb_trainer.trainer_name=#{trainerName} limit #{page},#{limit} ")		
	public List<AttendCourseChartModel> selectAttendCourseByTrainer(String startTime,String endTime,String trainerName,int page,int limit);
	
	@Select(sqlString_5+" and tb_trainer.trainer_name=#{trainerName} ")					//统计总数量
	public int Count_2(String startTime,String endTime,String trainerName);
	
	@Select(sqlString_2)
	public List<AttendCourseChartModel> selectTrainerNameByNull();
	
	//绘制饼图1，统计课程和课程被上课次数，借用coursePlace字段当作课程被上课次数
	@Select(sqlString_3)
	public List<AttendCourseChartModel> selectAttendCourseonPie_1(String startTime,String endTime);
	
	//绘制饼图2，统计教练和教练上课次数，借用coursePlace字段当作教练上课上课次数
	@Select(sqlString_4)
	public List<AttendCourseChartModel> selectAttendCourseonPie_2(String startTime,String endTime);
}
