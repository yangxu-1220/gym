package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.PrivateCourseForBoughtModel;
import com.gym.model.PrivateCourseForBuyModel;
import com.gym.model.PrivateCourseForSelectModel;
import com.gym.service.PrivateCourseManagementService;

@Controller
public class PrivateCourseManagementController {
	@Autowired
	PrivateCourseManagementService privateCourseManagementService;
	
	@RequestMapping("/private_course_management")
	public String MemberorTrainer(HttpSession session){		//返回到私教管理页面
		session.setAttribute("pastUrl","http://localhost:8080/private_course_management");		//将网址存成Session以便支付后返回
		return "PrivateCourseManagement";
	}
	
	@RequestMapping("/private_course_management/user_type")
	@ResponseBody()
	public String MemberorTrainer(HttpServletRequest request){
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("id");
		if(id/100000==1) {		//判断用户为会员
			System.out.println("我是会员");
			return "会员";
		}else if(id/100000==8) { 	//判断用户为教练
			System.out.println("我是教练");
			return "教练";
		}else
			System.out.println("我什么都不是");
			return null;
	}
	
	//以下是有关会员的方法
	
	@RequestMapping("/private_course_management/member/select_member_private")
	@ResponseBody()
	public Map<String,Object> selectPrivateCourseForSelectByMemberId(int page,int limit,HttpServletRequest request){ 	//判断用户为会员
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("id");
		List<PrivateCourseForSelectModel> list=privateCourseManagementService.selectPrivateCourseForSelectByMemberId(id,(page-1)*limit,limit);
		int count=privateCourseManagementService.count_3(id);
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",list);
        return data;
	}
	
	@RequestMapping("/private_course_management/member/buy_private")
	@ResponseBody()
	public Map<String,Object> selectPrivateCourseForBuy(int page,int limit){ 				//查询所有私教课程
		List<PrivateCourseForBuyModel> list=privateCourseManagementService.selectPrivateCourseForBuy((page-1)*limit,limit);
		int count=privateCourseManagementService.count_1();
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",list);
        return data;
	}
	
	@RequestMapping("/private_course_management/member/buy_private_do")						//通过私教课程名查询购买私教课程信息
	public String selectPrivateCourseForBuyByPrivateCourseName(String privateCourseName,Model model){ 
		PrivateCourseForBuyModel buyPrivateCourse=privateCourseManagementService.selectPrivateCourseForBuyByPrivateCourseName(privateCourseName);
		model.addAttribute("buyPrivateCourse",buyPrivateCourse);
		return "PrivateCourseBuy";
	}
	
	@RequestMapping("/private_course_management/member/select_bought_private")
	@ResponseBody()
	public Map<String,Object> selectPrivateCourseForBought(int page,int limit,HttpServletRequest request){ 				//查询已购买的私教课程
		HttpSession session=request.getSession();
		int memberId=(int)session.getAttribute("id");
		List<PrivateCourseForBoughtModel> list=privateCourseManagementService.selectPrivateCourseForBought(memberId,(page-1)*limit,limit);
		int count=privateCourseManagementService.count_2(memberId);
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",list);
        return data;
	}
	
	//以下是有关教练的方法
	
	@RequestMapping("/private_course_management/trainer/select_trainer_private")
	@ResponseBody()
	public Map<String,Object> selectPrivateCourseForSelectByTrainerId(int page,int limit,HttpServletRequest request){ 	//判断用户为教练
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("id");
		List<PrivateCourseForSelectModel> list=privateCourseManagementService.selectPrivateCourseForSelectByTrainerId(id,(page-1)*limit,limit);
		int count=privateCourseManagementService.count_4(id);
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",list);
        return data;
	}
	
	@RequestMapping("/private_course_management/trainer/select_private_sale")
	@ResponseBody()
	public List<PrivateCourseForBoughtModel> selectPrivateCourseForAttend(HttpServletRequest request){ 	//查询教练私教课程售卖情况
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("id");
		return privateCourseManagementService.selectPrivateCourseForAttend(id);
	}
}
