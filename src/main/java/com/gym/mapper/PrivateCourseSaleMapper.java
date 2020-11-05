package com.gym.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import com.gym.model.PrivateCourseSaleModel;

public interface PrivateCourseSaleMapper {
//	sql字符串
	String sqlString="select tb_buy_private_course.buy_private_course_time as saleTime,tb_trainer.trainer_name as trainerName,tb_private_course.private_course_name as courseName,tb_member.member_name as member_name,tb_buy_private_course.buy_private_course_price as coursePrice " + 
			"from tb_buy_private_course,tb_trainer,tb_private_course,tb_member " + 
			"where tb_buy_private_course.trainer_id=tb_trainer.trainer_id and tb_buy_private_course.private_course_id=tb_private_course.private_course_id and tb_buy_private_course.member_id=tb_member.member_id and buy_private_course_time>=#{startTime} and buy_private_course_time<=#{endTime}";
	String sqlString_2="select tb_trainer.trainer_name as trainerName from tb_trainer";
	//绘制饼图1,获取教练姓名和课程总价
	String sqlString_3="select trainer_name as trainerName,SUM(buy_private_course_price) as coursePrice " + 
			"from tb_buy_private_course,tb_trainer " + 
			"where tb_buy_private_course.trainer_id=tb_trainer.trainer_id and buy_private_course_time>=#{startTime} and buy_private_course_time<=#{endTime}" + 
			"GROUP BY trainer_name";
	//绘制饼图2,获取课程名称和课程总价
	String sqlString_4="select private_course_name as courseName,SUM(buy_private_course_price) as coursePrice " + 
			"from tb_buy_private_course,tb_private_course " + 
			"where tb_buy_private_course.private_course_id=tb_private_course.private_course_id and buy_private_course_time>=#{startTime} and buy_private_course_time<=#{endTime}" + 
			"GROUP BY private_course_name";
	//绘制折线图和条形图,获取时间和课程总价
	String sqlString_5="select SUBSTRING_INDEX(buy_private_course_time,' ',1) as saleTime,SUM(buy_private_course_price) as coursePrice " + 
			"from tb_buy_private_course " +
			"where buy_private_course_time>=#{startTime} and buy_private_course_time<=#{endTime} " +
			"GROUP BY SUBSTRING_INDEX(buy_private_course_time,' ',1)";
	//统计总数量
	String sqlString_6="select count(*) " + 
			"from tb_buy_private_course,tb_trainer,tb_private_course,tb_member " + 
			"where tb_buy_private_course.trainer_id=tb_trainer.trainer_id and tb_buy_private_course.private_course_id=tb_private_course.private_course_id and tb_buy_private_course.member_id=tb_member.member_id and buy_private_course_time>=#{startTime} and buy_private_course_time<=#{endTime}";
	
	@Select(sqlString+" limit #{page},#{limit} ")
	public List<PrivateCourseSaleModel> selectPrivateCourseSaleByNull(String startTime,String endTime,int page,int limit);
	
	@Select(sqlString_6)
	public int count_1(String startTime,String endTime);
	
	@Select(sqlString+" and tb_trainer.trainer_name=#{trainerName} limit #{page},#{limit} ")
	public List<PrivateCourseSaleModel> selectPrivateCourseSaleByTrainer(String startTime,String endTime,String trainerName,int page,int limit);
	
	@Select(sqlString_6+" and tb_trainer.trainer_name=#{trainerName} ")
	public int count_2(String startTime,String endTime,String trainerName);
	
	@Select(sqlString+" and tb_private_course.private_course_name=#{courseName} limit #{page},#{limit} ")
	public List<PrivateCourseSaleModel> selectPrivateCourseSaleByCourse(String startTime,String endTime,String courseName,int page,int limit);
	
	@Select(sqlString_6+" and tb_private_course.private_course_name=#{courseName} ")
	public int count_3(String startTime,String endTime,String courseName);
	
	@Select(sqlString+" and tb_trainer.trainer_name=#{trainerName} and tb_private_course.private_course_name=#{courseName} limit #{page},#{limit} ")
	public List<PrivateCourseSaleModel> selectPrivateCourseSaleByTrainerCourse(String startTime,String endTime,String trainerName,String courseName,int page,int limit);
	
	@Select(sqlString_6+" and tb_trainer.trainer_name=#{trainerName} and tb_private_course.private_course_name=#{courseName} ")
	public int count_4(String startTime,String endTime,String trainerName,String courseName);
	
	//查询所有私教课程
	@Select("SELECT private_course_name as courseName FROM tb_private_course")
	public List<PrivateCourseSaleModel> selectPrivateCourseByNull();

	//查询教练姓名
	@Select(sqlString_2)
	public List<PrivateCourseSaleModel> selectTrainerNameByNull();
	
	//绘制饼图1,获取教练姓名和课程总价
	@Select(sqlString_3)
	public List<PrivateCourseSaleModel> selectPrivateCourseSaleonPie_1(String startTime,String endTime);
	
	//绘制饼图2,获取课程名称和课程总价
	@Select(sqlString_4)
	public List<PrivateCourseSaleModel> selectPrivateCourseSaleonPie_2(String startTime,String endTime);
	
	//绘制折线图和条形图,获取时间和课程总价
	@Select(sqlString_5)
	public List<PrivateCourseSaleModel> selectPrivateCourseSaleonBarLine(String startTime,String endTime);
}
