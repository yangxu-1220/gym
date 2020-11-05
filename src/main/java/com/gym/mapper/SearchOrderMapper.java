package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.gym.model.OrderCourseModel;

@Repository
public interface SearchOrderMapper {
	
	/**
	 * 分页数据
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("SELECT tb_member.member_name,tb_member.member_id," + 
			"tb_trainer.trainer_name,tb_trainer.trainer_id," + 
			"tb_course.course_name,tb_course.course_id," + 
			"tb_member.member_sex,tb_member.member_phone," + 
			"tb_member.member_address,tb_attend_course.attend_course_time,tb_order_course.order_time " + 
			"FROM tb_member,tb_course,tb_trainer,tb_attend_course,tb_order_course " + 
			"WHERE tb_order_course.member_id=tb_member.member_id " + 
			"and tb_order_course.course_id=tb_course.course_id " + 
			"and tb_order_course.trainer_id=tb_trainer.trainer_id " + 
			"and tb_order_course.attend_course_time=tb_attend_course.attend_course_time " + 
			"and tb_order_course.course_id=tb_attend_course.attend_course_id " + 
			"and tb_order_course.trainer_id=tb_attend_course.trainer_id " +
			"limit #{pageStart},#{pageSize}")
	public List<OrderCourseModel> getOrderCourseBtween(@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);
	
	@Select("SELECT tb_member.member_name,tb_member.member_id," + 
			"tb_trainer.trainer_name,tb_trainer.trainer_id," + 
			"tb_course.course_name,tb_course.course_id," + 
			"tb_member.member_sex,tb_member.member_phone," + 
			"tb_member.member_address,tb_attend_course.attend_course_time,tb_order_course.order_time " + 
			"FROM tb_member,tb_course,tb_trainer,tb_attend_course,tb_order_course " + 
			"WHERE tb_order_course.member_id=tb_member.member_id " + 
			"and tb_order_course.course_id=tb_course.course_id " + 
			"and tb_order_course.trainer_id=tb_trainer.trainer_id " + 
			"and tb_order_course.attend_course_time=tb_attend_course.attend_course_time " + 
			"and tb_order_course.course_id=tb_attend_course.attend_course_id " + 
			"and tb_order_course.trainer_id=tb_attend_course.trainer_id " +
			"and tb_order_course.trainer_id=#{trainerId} " + 
			"and tb_order_course.course_id=#{courseId} " +
			"and tb_order_course.attend_course_time=#{attendCourseTime} " +
			"limit #{pageStart},#{pageSize}")
	public List<OrderCourseModel> getOrderCourseBtween_2(@Param("pageStart") Integer pageStart,
														 @Param("pageSize") Integer pageSize,
														 @Param("trainerId") Integer trainerId,
														 @Param("courseId") Integer courseId,
														 @Param("attendCourseTime") String attendCourseTime);
	
	@Select("SELECT count(1) FROM tb_order_course "
			+ "WHERE trainer_id=#{trainerId} "
			+ "and course_id=#{courseId} "
			+ "and attend_course_time=#{attendCourseTime}")
	public int countOrderCourseById(@Param("trainerId") Integer trainerId,
			 						@Param("courseId") Integer courseId,
			 						@Param("attendCourseTime") String attendCourseTime);
	
	/**
	 * 统计orderCourse表中的数据量
	 * @return
	 */
	@Select("SELECT count(1) FROM tb_order_course,tb_member,tb_course,tb_trainer,tb_attend_course "+ 
			"WHERE tb_order_course.member_id=tb_member.member_id " + 
			"and tb_order_course.course_id=tb_course.course_id " + 
			"and tb_order_course.trainer_id=tb_trainer.trainer_id " + 
			"and tb_order_course.attend_course_time=tb_attend_course.attend_course_time " + 
			"and tb_order_course.course_id=tb_attend_course.attend_course_id " + 
			"and tb_order_course.trainer_id=tb_attend_course.trainer_id ")
	public int countOrderCourse();
	
	/**
	 * 获取渲染教练下拉框的数据
	 * @return
	 */
	@Select("SELECT DISTINCT tb_attend_course.trainer_id,tb_trainer.trainer_name "
			+ "FROM tb_attend_course,tb_trainer "
			+ "WHERE tb_attend_course.trainer_id=tb_trainer.trainer_id")
	public List<OrderCourseModel> getTrainerList();
	
	/**
	 * 获取渲染课程下拉框的数据
	 * @return
	 */
	@Select("SELECT DISTINCT tb_attend_course.attend_course_id,tb_course.course_Name "
			+ "FROM tb_attend_course,tb_course "
			+ "WHERE tb_attend_course.attend_course_id=tb_course.course_id")
	public List<OrderCourseModel> getCourseList();
	
	/**
	 * 获取渲染时间下拉框的数据
	 * @return
	 */
	@Select("SELECT DISTINCT tb_attend_course.attend_course_time FROM tb_attend_course")
	public List<String> getTimeList();
}
