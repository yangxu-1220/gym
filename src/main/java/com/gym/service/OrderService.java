package com.gym.service;

import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.List;

//import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.OrderMapper;
import com.gym.model.OrderModel;

@Service
public class OrderService {

	@Autowired
	OrderMapper orderMapper;
	
	public OrderModel CreateOrder(OrderModel order) {	
		Date currentDate = new Date();
		SimpleDateFormat nowTime = new SimpleDateFormat("yyyyMMddHHmmss");
        String fDate = nowTime.format(currentDate);			//date对象字符串化
		int id=order.getUserId();	//获取从controller传过来的userId
		String str=id+fDate;	//把userId和时间字符串作为订单id
		order.setOrderId(str);			//初始化订单编号		
		order.setCreateTime(fDate);//初始化订单创建时间						
		System.out.println(str);
		orderMapper.createOrder(order);	
		return order;
	}
	
	public void renewOrderTotalPrice(OrderModel order) {
		orderMapper.renewOrderTotalPrice(order);
	}
	
	public void renewOrderState(OrderModel order) {
		orderMapper.renewOrderState(order);
	}
	
	public void CancelOrderById(int id) {
		orderMapper.cancelOrderById(id);
	}
	
	public OrderModel SelectOrderByStateAndId(String orderState,Integer userId){
		OrderModel result=orderMapper.selectOrderByStateAndId(orderState,userId);
		return result;
	}
	
	public void DeleteOrderById(Integer userId) {
		orderMapper.deleteOrderById(userId);
	}
	//根据订单ID修改订单状态为已支付 修改订单总价
	public void RenewStateByOrderId(float orderTotalPirce,String orderId) {
		orderMapper.renewStateByOrderId(orderTotalPirce,orderId);
	}
}
