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

import com.gym.model.OrderDetailModel;
import com.gym.model.OrderModel;
import com.gym.service.OrderDetailForMemberService;

@RequestMapping("/myOrder")
@Controller
public class OrderDetailForMemberController {
	
	@Autowired
	OrderDetailForMemberService orderDetailForMemberService;
	
	//页面路径
	@RequestMapping("/index")	
	public String Index() {
		return "MyOrderIndex";
	}
	
	//myOrder的主表格渲染
	@RequestMapping("/getMyOrderBtween")
	@ResponseBody
	public Map<String,Object> getGoodsBtween(HttpServletRequest request,
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		HttpSession session=request.getSession();
		int userId=(int)session.getAttribute("id");
		List<OrderModel> myOrder=this.orderDetailForMemberService.GetMyOrderBtween(userId, pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.orderDetailForMemberService.CountMyOrder(userId);//获取数据库中数据总条数		
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",myOrder);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	//根据订单ID删除订单记录
	@RequestMapping("/deleteOrderById")
	@ResponseBody
	public void DeleteOrderById(String orderId) {
		orderDetailForMemberService.DeleteOrderById(orderId);
	}
	
	//根据订单Id获取对应的所有订单详情信息
	@RequestMapping("/getDetailById")
	public String GetDetailById(String orderId,Model model){
		List<OrderDetailModel> myDetails=orderDetailForMemberService.GetDetailById(orderId);
		model.addAttribute("myDetail", myDetails);
		return "MyDetail";
	}
}
