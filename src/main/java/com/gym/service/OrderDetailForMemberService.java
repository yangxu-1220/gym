package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.OrderDetailForMemberMapper;
import com.gym.model.OrderDetailModel;
import com.gym.model.OrderModel;

@Service
public class OrderDetailForMemberService {
	
	@Autowired
	OrderDetailForMemberMapper orderDetailForMemberMapper;
	
	//根据用户自己的ID去查询自己的订单  分页数据
	public List<OrderModel> GetMyOrderBtween(Integer userId,Integer pageStart,Integer pageSize){
		List<OrderModel> myOrder=orderDetailForMemberMapper.getMyOrderBtween(userId, pageStart, pageSize);
		return myOrder;
	}
	
	//根据用户自己的ID统计自己订单的数量以便分页
	public int CountMyOrder(Integer userId) {
		int count=orderDetailForMemberMapper.countMyOrder(userId);
		return count;		
	}
	
	//根据订单ID去删除一条订单记录
	public void DeleteOrderById(String orderId) {
		orderDetailForMemberMapper.deleteOrderById(orderId);
	}
	
	//根据订单ID查询所有的订单详情
	public List<OrderDetailModel> GetDetailById(String orderId){
		List<OrderDetailModel> myOrderDetail=orderDetailForMemberMapper.getDetailById(orderId);
		return myOrderDetail;
	}
}
