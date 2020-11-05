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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.AttendCourseModel;
import com.gym.model.MemberModel;
import com.gym.model.OrderCourseModel;
import com.gym.service.MemberService;
import com.gym.service.OrderCourseService;

@RequestMapping("/orderCourse")
@Controller
public class OrderCourseController {
	
	@Autowired
	OrderCourseService orderCourseService;
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/index")
	public String index() {
		return "OrderCourseIndex";
	}
	
	@RequestMapping("/getCourseByStateBtween")
	@ResponseBody
	public Map<String,Object> GetCourseByStateBtween(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<OrderCourseModel> orderCourse=orderCourseService.GetCourseByState(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.orderCourseService.GetCourseTotalOfState();//获取数据库中数据总条数
		//int total=rowSize%pageSize==0?rowSize/pageSize:rowSize/pageSize+1;
		//data.put("total", total);//页数total
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data", orderCourse);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	//根据三个主键确定一条上课记录
	@RequestMapping("/selectAttendByPrimary")
	//@ResponseBody
	public String SelectAttendByPrimary(Integer courseId,Integer trainerId,String attendCourseTime,Model model,HttpServletRequest request) {
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("id");
		MemberModel member=memberService.getMemberById(id);
		AttendCourseModel result=orderCourseService.SelectAttendByPrimary(courseId,trainerId,attendCourseTime);		
		model.addAttribute("member", member);
		model.addAttribute("result", result);	
		return "OrderOk";
	}
	
	//插入一条约课记录
	@RequestMapping("/insertOrderCourse")
	@ResponseBody
	public String InsertOrderCourse(OrderCourseModel orderCourseModel) {
		String result=orderCourseService.InsertOrderCourse(orderCourseModel);
		return result;
	}
	
	@RequestMapping("/cancelOrder")
	@ResponseBody
	public void CancelOrder(HttpServletRequest request,Integer courseId,Integer trainerId,String attendCourseTime) {
		System.out.println(trainerId);
		HttpSession session=request.getSession();
		int memberId=(int)session.getAttribute("id");
		orderCourseService.DeleteOrderCourseByPrimary(memberId, courseId, trainerId, attendCourseTime);
	}
	
	@RequestMapping("/toOrderList")
	public String ToOrderList() {
		return "OrderCourseList";
	}		
	
	@RequestMapping("/getOrderList")
	@ResponseBody
	public Map<String,Object> OrderList(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize,
	HttpServletRequest request){
		HttpSession session=request.getSession();
		int memberId=(int)session.getAttribute("id");
		List<OrderCourseModel> orderCourse=orderCourseService.OrderList(memberId, pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.orderCourseService.GetCountByMemberId(memberId);//获取数据库中数据总条数
		//int total=rowSize%pageSize==0?rowSize/pageSize:rowSize/pageSize+1;
		//data.put("total", total);//页数total
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data", orderCourse);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
}
