package com.gym.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.OrderCourseChartMapper;
import com.gym.model.OrderCourseChartModel;

@Service
public class OrderCourseChartService {
	@Autowired
	OrderCourseChartMapper orderCourseChartMapper;
	
	public List<OrderCourseChartModel> getOrderCourse(String startTime,String endTime,String courseName,int page,int limit){
		if(courseName.equals("所有课程")) {
			System.out.println("1");
			return orderCourseChartMapper.selectOrderCourseByNull(startTime, endTime, page, limit);
		}else {
			System.out.println("2");
			return orderCourseChartMapper.selectOrderCourseByCourse(startTime, endTime, courseName, page, limit);
		}
	}
	
	public int count(String startTime,String endTime,String courseName){
		if(courseName.equals("所有课程")) {
			return orderCourseChartMapper.count_1(startTime, endTime);
		}else {
			return orderCourseChartMapper.count_2(startTime, endTime, courseName);
		}
	}
	
	//查询所有操课信息
	public List<OrderCourseChartModel> getCourseByNull(){
		return orderCourseChartMapper.selectCourseByNull();
	}
	
	//获取饼图数据
	public List<OrderCourseChartModel> getOrderCourseonPie(String startTime,String endTime){
		return orderCourseChartMapper.selectOrderCourseonPie(startTime,endTime);
	}
	
	//获取折线图和条形图数据
	public List<OrderCourseChartModel> getOrderCourseonBarLine(String startTime,String endTime){
		return orderCourseChartMapper.selectOrderCourseonBarLine(startTime,endTime);
	}
}
