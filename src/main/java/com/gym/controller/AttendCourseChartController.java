package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.AttendCourseChartModel;
import com.gym.service.AttendCourseChartService;

@Controller
public class AttendCourseChartController {
	@Autowired
	AttendCourseChartService attendCourseChartService;
	
	@RequestMapping("/change_5")
	public String Change() {
		return "AttendCourse";
	}
	
	@RequestMapping("/consume/attend_course")
	@ResponseBody
	public Map<String,Object> AttendCourse(String startTime,String endTime,String trainerName,int page,int limit) {
		List<AttendCourseChartModel> attendCourseList=attendCourseChartService.getAttendCourse(startTime,endTime,trainerName,(page-1)*limit,limit);
		int pageData=attendCourseChartService.Count(startTime,endTime,trainerName);
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",pageData);
        data.put("data",attendCourseList);
		return data;
	}
	
	//获取教练姓名
	@RequestMapping("/consume/attend_course/get_trainer_name")
	@ResponseBody
	public List<AttendCourseChartModel> getTrainerName() {
		return attendCourseChartService.getTrainerName();
	}	
	
	//获取饼图1数据
	@RequestMapping("/consume/attend_course/get_pie_1")
	@ResponseBody
	public List<AttendCourseChartModel> getAttendCourse_1(String startTime,String endTime) {
		return attendCourseChartService.getAttendCourseonPie_1(startTime,endTime);
	}
	
	//获取饼图2数据
	@RequestMapping("/consume/attend_course/get_pie_2")
	@ResponseBody
	public List<AttendCourseChartModel> getAttendCourse_2(String startTime,String endTime) {
		return attendCourseChartService.getAttendCourseonPie_2(startTime,endTime);
	}
}
