package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.PrivateCourseSaleModel;
import com.gym.service.PrivateCourseSaleService;

@Controller
public class PrivateCourseSaleController {
	@Autowired
	PrivateCourseSaleService privateCourseSaleService;
	
	@RequestMapping("/change_3")
	public String Change() {
		return "PrivateCourseSale";
	}
	
	@RequestMapping("/consume/private_course_sale")
	@ResponseBody
	public Map<String,Object> PrivateCourseSale(String startTime,String endTime,String trainerName,String courseName,int page,int limit) {
		List<PrivateCourseSaleModel> list=privateCourseSaleService.getPrivateCourseSale(startTime, endTime, trainerName, courseName,(page-1)*limit,limit);
		int count=privateCourseSaleService.count(startTime, endTime, trainerName, courseName);
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",list);
        return data;
	}
	
	//获取所有教练姓名
	@RequestMapping("/consume/private_course_sale/get_trainerName")
	@ResponseBody
	public List<PrivateCourseSaleModel> getTrainerName(){
		return privateCourseSaleService.getTrainerName();
	}
	
	//获取所有私教课程名称
	@RequestMapping("/consume/private_course_sale/get_private_course")
	@ResponseBody
	public List<PrivateCourseSaleModel> getPrivateCourse(){
		return privateCourseSaleService.getPrivateCourse();
	}
	
	//获取饼图1数据
	@RequestMapping("/consume/private_course_sale/get_pie_1")
	@ResponseBody
	public List<PrivateCourseSaleModel> getPrivateCourseSaleonPie_1(String startTime,String endTime){
		return privateCourseSaleService.getPrivateCourseSaleonPie_1(startTime,endTime);
	}
	
	//获取饼图2数据
	@RequestMapping("/consume/private_course_sale/get_pie_2")
	@ResponseBody
	public List<PrivateCourseSaleModel> getPrivateCourseSaleonPie_2(String startTime,String endTime){
		return privateCourseSaleService.getPrivateCourseSaleonPie_2(startTime,endTime);
	}
	
	//获取条形图，折线图数据
	@RequestMapping("/consume/private_course_sale/get_barline")
	@ResponseBody
	public List<PrivateCourseSaleModel> getPrivateCourseSaleonBarLine(String startTime,String endTime){
		return privateCourseSaleService.getPrivateCourseSaleonBarLine(startTime,endTime);
	}
}
