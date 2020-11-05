package com.gym.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.OrderDetailModel;
import com.gym.model.OrderModel;
import com.gym.service.OrderDetailService;
import com.gym.service.OrderService;

@RequestMapping("/orderDetail")
@Controller
public class OrderDetailController {
	
	@Autowired
	OrderDetailService orderDetailService;
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/createOrderDetail")
	@ResponseBody
	public String CreateOrderDetail(OrderDetailModel orderDetail,HttpServletRequest request) {
		HttpSession session=request.getSession();
		int userId=(int)session.getAttribute("id");//获取到用户的ID
		String orderState="未支付";				   //state为未支付
		OrderModel order=new OrderModel();		   //创建一个order对象
		order.setUserId(userId);
		order.setOrderState(orderState);
		OrderModel result=orderService.SelectOrderByStateAndId(orderState,userId);//根据用户的ID和未支付两个条件去查order
		if(result!=null) {//如果查到了数据，只创建一个新的商品详情
			orderDetail.setOrderId(result.getOrderId());
			boolean b=orderDetailService.CreateOrderDetail(orderDetail);
			if(b)
				return "true";//创建成功
			else
				return "false";//创建失败
		}
		else {//如果没查到，创建一个新的订单和一个新的商品详情	
			OrderModel order_2=orderService.CreateOrder(order);
			orderDetail.setOrderId(order_2.getOrderId());
			boolean c=orderDetailService.CreateOrderDetail(orderDetail);
			if(c)
				return "true";//创建成功
			else
				return "false";//创建失败
		}
		
		
	}
	
	@RequestMapping("/renewGoodsNumber")
	public void RenewGoodsNumber(OrderDetailModel orderDetail) {
		orderDetailService.RenewGoodsNumber(orderDetail);
	}
	
	@RequestMapping("/delelteGoodsDetailById")
	public void DelelteGoodsDetailById(Integer id) {
		orderDetailService.DelelteGoodsDetailById(id);
	}
	
	@RequestMapping("/selectOrderDetailById")
	@ResponseBody
	public List<OrderDetailModel> SelectOrderDetailById(Integer id,Model model) {
		List<OrderDetailModel> orderDetail=orderDetailService.SelectOrderDetailById(id);
		model.addAttribute("details", orderDetail);
		return orderDetail;
	}
}
