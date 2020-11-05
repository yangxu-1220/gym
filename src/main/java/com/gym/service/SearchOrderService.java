package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.SearchOrderMapper;
import com.gym.model.OrderCourseModel;

@Service
public class SearchOrderService {
	
	@Autowired
	SearchOrderMapper searchOrderMapper;
	
	//拿分页数据
	public List<OrderCourseModel> GetOrderCourseBtween(Integer pageStart,Integer pageSize){
		List<OrderCourseModel> orderCourse=searchOrderMapper.getOrderCourseBtween(pageStart, pageSize);
		return orderCourse;
	}
	
	//拿加了查询条件的分页数据
	public List<OrderCourseModel> GetOrderCourseBtween_2(Integer pageStart,Integer pageSize,Integer trainerId,Integer courseId,String attendCourseTime){
		List<OrderCourseModel> orderCourse_2=searchOrderMapper.getOrderCourseBtween_2(pageStart, pageSize, trainerId, courseId, attendCourseTime);
		return orderCourse_2;
	}
	
	//统计数据数量
	public int CountOrderCourse() {
		int count=searchOrderMapper.countOrderCourse();
		return count;
	}
	
	//统计根据条件查询的数量
	public int CountOrderCourseById(Integer trainerId,Integer courseId,String attendCourseTime) {
		int count=searchOrderMapper.countOrderCourseById(trainerId, courseId, attendCourseTime);
		return count;
	}
	
	// 获取渲染教练下拉框的数据
	public List<OrderCourseModel> GetTrainerList(){
		List<OrderCourseModel> trainerList=searchOrderMapper.getTrainerList();
		return trainerList;
	}

	//获取渲染课程下拉框的数据	 	 	
	public List<OrderCourseModel> GetCourseList(){
		List<OrderCourseModel> courseList=searchOrderMapper.getCourseList();
		return courseList;
	}	
	
	//获取渲染时间下拉框的数据	
	public List<String> GetTimeList(){
		List<String> timeList=searchOrderMapper.getTimeList();
		return timeList;
	}

}
