package com.gym.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.model.OrderModel;
import com.gym.service.OrderService;

@RequestMapping("/order")
@Controller
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	//创建订单的时候去查询数据库中是否有对应的记录了，如果有了打印1，如果没有则创建订单
	@RequestMapping("/createOrder")
	public void CreateOrder(OrderModel order,HttpServletRequest request) {	
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("id");
		System.out.println("用户的ID为："+id);
		order.setUserId(id);
		order.setOrderState("未支付");
		orderService.CreateOrder(order);	
	}
	//更新订单的总价
	@RequestMapping("/renewOrderTotalPrice")
	public void RenewOrderTotalPrice(OrderModel order) {
		orderService.renewOrderTotalPrice(order);
	}
	//更新订单的状态
	@RequestMapping("/renewOrderState")
	public void RenewOrderState(OrderModel order) {
		orderService.renewOrderState(order);
	}
	//用户取消订单则删除订单
	@RequestMapping("/cancelOrderById")
	public void CancelOrderById(Integer id) {
		orderService.CancelOrderById(id);
	}
}
