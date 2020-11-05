package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.MemberVisitModel;
import com.gym.service.MemberVisitService;

@Controller
public class MemberVisitController {
	@Autowired
	MemberVisitService memberVisitService;
	
	@RequestMapping("/change_7")
	public String Change() {
		return "MemberVisit";
	}
	
	@RequestMapping("/consume/member_come")
	@ResponseBody
	public Map<String,Object> MemberCome(String startTime,String endTime,String member,int page,int limit) {
		List<MemberVisitModel> list = memberVisitService.getMemberVisit(startTime, endTime, member,(page-1)*limit,limit);
		int count=memberVisitService.count(startTime, endTime, member);
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",list);
        return data;
		
	}
	
	//获取条形图和折线图的数据
	@RequestMapping("/consume/member_come/get_barline")
	@ResponseBody
	public List<MemberVisitModel> MemberComeonBarLine(String startTime,String endTime) {
		return memberVisitService.getMemberVisitonBarLine(startTime,endTime);
	}
}
