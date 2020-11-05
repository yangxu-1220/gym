package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.BusinessConsumptionModel;
import com.gym.service.BusinessConsumptionService;

@Controller
public class BusinessConsumptionController {
	@RequestMapping("/change_2")
	public String Change() {
		return "BusinessConsume";
	}
	
	@Autowired
	BusinessConsumptionService businessConsumptionService;
	
	@RequestMapping("/consume/business_consume")
	@ResponseBody
	public Map<String,Object> Business(String startTime,String endTime,int page,int limit) {
		List<BusinessConsumptionModel> businessConsumptionList=businessConsumptionService.getBusinessConsumption(startTime, endTime,(page-1)*limit,limit);
		int count=businessConsumptionService.count(startTime, endTime);
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",businessConsumptionList);
		return data;
	}
	
	//获取饼图所需数据
	@RequestMapping("/consume/business_consume/get_pie")
	@ResponseBody
	public List<BusinessConsumptionModel> BusinessonPie(String startTime,String endTime) {
		System.out.println("我被执行了！！！");
		return businessConsumptionService.getBusinessonPie(startTime,endTime);
	}
	
	//获取条形图和折线图所需数据
	@RequestMapping("/consume/business_consume/get_barline")
	@ResponseBody
	public List<BusinessConsumptionModel> Businessonbarline(String startTime,String endTime) {
		return businessConsumptionService.getBusinessonBarLine(startTime,endTime);
	}
}
