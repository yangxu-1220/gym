 package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.OrderDetailMapper;
import com.gym.mapper.OrderMapper;
import com.gym.model.OrderDetailModel;

@Service
public class OrderDetailService {
	
	@Autowired
	OrderDetailMapper orderDetailMapper;
	OrderMapper orderMapper;
	
	public boolean CreateOrderDetail(OrderDetailModel orderDetail) {
		OrderDetailModel result=orderDetailMapper.checkOrderDetailByGoodsIdAndOrderId(orderDetail);
		if(result!=null){ //如果没有重复添加的商品则创建一个商品详情
			return false;
		}
		else {	
			orderDetailMapper.createOrderDetail(orderDetail);
			return true;
		}
	}
	
	//根据UserId查询该用户对应的未支付的订单详情
	public List<OrderDetailModel> GetOrderDetailByUserId(int id){
		return orderDetailMapper.getOrderDetailByUserId(id);
	}
	
	public void RenewGoodsNumber(OrderDetailModel orderDetail) {
		orderDetailMapper.renewGoodsNumber(orderDetail);
	}
	
	public void DelelteGoodsDetailById(int id) {
		orderDetailMapper.delelteGoodsDetailById(id);
	}
	
	public List<OrderDetailModel> SelectOrderDetailById(int id){
		List<OrderDetailModel> orderDetail=orderDetailMapper.selectOrderDetailById(id);
		return orderDetail;
	}
}
