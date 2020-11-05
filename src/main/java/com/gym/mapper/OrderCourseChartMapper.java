package com.gym.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import com.gym.model.OrderCourseChartModel;

public interface OrderCourseChartMapper {
//	sql字符串
	String sqlString_1="select tb_order_course.course_id as courseId,tb_attend_course.attend_course_time as courseTime,attend_course_palce as coursePlace,tb_course.course_name as courseName,trainer_name as trainerName,GROUP_CONCAT(DISTINCT(member_name)) as memberName,count(tb_order_course.member_id) as orderedNumber,tb_course.course_number as maxOrderedNumber " + 
			"from tb_order_course,tb_attend_course,tb_trainer,tb_course,tb_member " + 
			"where tb_order_course.course_id=tb_attend_course.attend_course_id and tb_attend_course.trainer_id=tb_trainer.trainer_id and tb_order_course.course_id=tb_course.course_id and tb_order_course.member_id=tb_member.member_id and order_time>=#{startTime} and order_time<=#{endTime} ";
	String sqlString_2="group by tb_order_course.course_id ";
	String sqlString_3="select tb_course.course_name as courseName from tb_course";
	//绘制饼图，统计操课名和操课上课次数
	String sqlString_4="select tb_order_course.course_id as courseId,tb_course.course_name as courseName,count(tb_order_course.member_id) as orderedNumber " + 
			"from tb_order_course,tb_attend_course,tb_course " + 
			"where tb_order_course.course_id=tb_attend_course.attend_course_id and tb_order_course.course_id=tb_course.course_id and order_time>=#{startTime} and order_time<=#{endTime} " + 
			"group by tb_order_course.course_id";
	//绘制折线图和条形图，统计在预约时间内的操课名，已预约人数和最大预约人数
	String sqlString_5="select SUBSTRING_INDEX(order_time,' ',1) as courseTime,tb_course.course_name as courseName,count(tb_order_course.member_id) as orderedNumber,course_number as maxOrderedNumber " + 
			"from tb_order_course,tb_attend_course,tb_course " + 
			"where tb_order_course.course_id=tb_attend_course.attend_course_id and tb_order_course.course_id=tb_course.course_id and order_time>=#{startTime} and order_time<=#{endTime} " + 
			"group by SUBSTRING_INDEX(order_time,' ',1)";
	//统计数量
	String sqlString_6="select count(DISTINCT tb_order_course.course_id) " + 
			"from tb_order_course,tb_attend_course,tb_trainer,tb_course,tb_member " + 
			"where tb_order_course.course_id=tb_attend_course.attend_course_id and tb_attend_course.trainer_id=tb_trainer.trainer_id and tb_order_course.course_id=tb_course.course_id and tb_order_course.member_id=tb_member.member_id and order_time>=#{startTime} and order_time<=#{endTime} ";
	
	@Select(sqlString_1+sqlString_2+" limit #{page},#{limit} ")
	public List<OrderCourseChartModel> selectOrderCourseByNull(String startTime,String endTime,int page,int limit);
	
	@Select(sqlString_6)
	public int count_1(String startTime,String endTime);
	
	@Select(sqlString_1+" and tb_course.course_name=#{courseName} "+sqlString_2+" limit #{page},#{limit} ")
	public List<OrderCourseChartModel> selectOrderCourseByCourse(String startTime,String endTime,String courseName,int page,int limit);
	
	@Select(sqlString_6+" and tb_course.course_name=#{courseName} "+sqlString_2)
	public int count_2(String startTime,String endTime,String courseName);
	
	@Select(sqlString_3)
	public List<OrderCourseChartModel> selectCourseByNull();
	
	//绘制饼图，统计操课名和操课上课次数
	@Select(sqlString_4)
	public List<OrderCourseChartModel> selectOrderCourseonPie(String startTime,String endTime);
	
	//绘制折线图和条形图，统计在预约时间内的操课名，已预约人数和最大预约人数
	@Select(sqlString_5)
	public List<OrderCourseChartModel> selectOrderCourseonBarLine(String startTime,String endTime);
}
