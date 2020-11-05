package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.AttendPrivateCourseModel;
import com.gym.service.AttendPrivateCourseService;

@Controller
public class AttendPrivateCourseController {
	@Autowired
	AttendPrivateCourseService attendPrivateCourseService;
	
	@RequestMapping("/change_4")
	public String Change() {
		return "AttendPrivateCourse";
	}
	
	@RequestMapping("/consume/attend_private_course")
	@ResponseBody
	public Map<String,Object> AttendPrivateCourse(String startTime,String endTime,String trainerName,String courseName,String memberName,int page,int limit) {
		List<AttendPrivateCourseModel> list=attendPrivateCourseService.getAttendPrivateCourse(startTime,endTime,trainerName,courseName,memberName,(page-1)*limit,limit);
		int pageData=attendPrivateCourseService.count(startTime, endTime, trainerName, courseName, memberName);
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",pageData);
        data.put("data",list);
        return data;
	}
	
	//获取私教课程名称
	@RequestMapping("/consume/attend_private_course/get_course_name")
	@ResponseBody
	public List<AttendPrivateCourseModel> getCourseName() {
		return attendPrivateCourseService.getCourseName();
	}
	
	//获取饼图1数据
	@RequestMapping("/consume/attend_private_course/get_pie_1")
	@ResponseBody
	public List<AttendPrivateCourseModel> getAttendPrivateCourseonPie_1(String startTime,String endTime) {
		return attendPrivateCourseService.getAttendPrivateCourseonPie_1(startTime,endTime);
	}
	
	//获取饼图2数据
	@RequestMapping("/consume/attend_private_course/get_pie_2")
	@ResponseBody
	public List<AttendPrivateCourseModel> getAttendPrivateCourseonPie_2(String startTime,String endTime) {
		return attendPrivateCourseService.getAttendPrivateCourseonPie_2(startTime,endTime);
	}
}
