package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.OrderCourseChartModel;
import com.gym.service.OrderCourseChartService;

@Controller
public class OrderCourseChartController {
	@Autowired
	OrderCourseChartService orderCourseChartService;
	
	@RequestMapping("/change_6")
	public String Change() {
		return "OrderCourse";
	}
	
	@RequestMapping("/consume/order_course")
	@ResponseBody
	public Map<String,Object> OrderCourse(String startTime,String endTime,String courseName,int page,int limit) {
		List<OrderCourseChartModel> list=orderCourseChartService.getOrderCourse(startTime, endTime, courseName,(page-1)*limit,limit);
		int count=orderCourseChartService.count(startTime, endTime, courseName);
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",list);
        return data;
	}
	
	//获取操课信息
	@RequestMapping("/consume/order_course/get_course")
	@ResponseBody
	public List<OrderCourseChartModel> getCourse() {
		return orderCourseChartService.getCourseByNull();
	}
	
	//获取饼图数据
	@RequestMapping("/consume/order_course/get_pie")
	@ResponseBody
	public List<OrderCourseChartModel> getOrderCourseonPie(String startTime,String endTime) {
		return orderCourseChartService.getOrderCourseonPie(startTime,endTime);
	}
	
	//获取折线图和条形图数据
	@RequestMapping("/consume/order_course/get_barline")
	@ResponseBody
	public List<OrderCourseChartModel> getOrderCourseonBarLine(String startTime,String endTime) {
		return orderCourseChartService.getOrderCourseonBarLine(startTime,endTime);
	}
}
