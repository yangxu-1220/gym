package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.MemberConsumptionModel;
import com.gym.service.MemberConsumptionService;

/**
 * @author wuhunyu
 * @function 会员消费
 */

@Controller
public class MemberConsumptionCotroller {
	//	地址中转
	@RequestMapping("/consume")
	public String Member(HttpServletRequest request) {
//		HttpSession session=request.getSession();
//		int id=(int)session.getAttribute("id");
//		System.out.println("这里是统计报表页面，获取id的数据为："+id);
		return "Consume";
	}
	//	地址中转
	@RequestMapping("/change_1")
	public String Change() {
		return "MemberConsume";
	}
	
	@Autowired
	MemberConsumptionService memberConsumptionService;
	
	@RequestMapping("/consume/member_consume")
	@ResponseBody
	public Map<String,Object> MemberConsune(String startTime,String endTime,String member,int page,int limit) {
		List<MemberConsumptionModel> list = memberConsumptionService.getMemberConsumption(startTime, endTime, member,(page-1)*limit,limit);
		int count=memberConsumptionService.count(startTime, endTime, member);
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",list);
        return data;
	}
	
	//获取员工姓名
//	@RequestMapping("/consume/member_consume/get_stuff")
//	@ResponseBody
//	public List<MemberConsumptionModel> getStuff(){
//		return memberConsumptionService.getStuffByNull();
//	}
	
	//获取饼图数据
	@RequestMapping("/consume/member_consume/get_pie")
	@ResponseBody
	public List<MemberConsumptionModel> getMemberonPie(String startTime,String endTime){
		return memberConsumptionService.getMemberonPie(startTime,endTime);
	}
	
	//获取条形图，折线图数据
	@RequestMapping("/consume/member_consume/get_barline")
	@ResponseBody
	public List<MemberConsumptionModel> getMemberonBarLine(String startTime,String endTime){
		return memberConsumptionService.getMemberonBarLine(startTime,endTime);
	}
}
