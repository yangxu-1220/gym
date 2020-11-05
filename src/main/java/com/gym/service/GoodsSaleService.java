package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.GoodsSaleMapper;
import com.gym.model.OrderDetailModel;
import com.gym.model.OrderModel;

@Service
public class GoodsSaleService {
	
	@Autowired
	GoodsSaleMapper goodsSaleMapper;
	
	//获取主页分页数据
	public List<OrderModel> GetGoodsSalaBtween(Integer pageStart,Integer pageSize){
		List<OrderModel> goodsSale=goodsSaleMapper.getGoodsSaleBtween(pageStart, pageSize);
		return goodsSale;
	}
	
	//获取order表的数据量
	public int CountGoodsSale() {
		int count=goodsSaleMapper.countGoodsSale();
		return count;
	}
	
	//根据ID删除order
	public void DeleteOrderById(String orderId) {
		goodsSaleMapper.deleteOrderById(orderId);
	}
	
	//根据ID查询对应的商品详情集
	public List<OrderDetailModel> GetDetailById(String orderId){
		List<OrderDetailModel> orderDetail=goodsSaleMapper.getDetailById(orderId);
		return orderDetail;
	}		
	
	//根据三个条件获取分页数据
	public List<OrderModel> QueryBy3(String startTime,String endTime,String idOrName,Integer pageStart,Integer pageSize){
		List<OrderModel> order_3=goodsSaleMapper.queryBy3(startTime, endTime, idOrName, pageStart, pageSize);
		return order_3;
	}
	
	//统计三个条件的数据数量
	public int CountQuery3(String startTime,String endTime,String idOrName) {
		int count=goodsSaleMapper.countQuery3(startTime, endTime, idOrName);
		return count;
	}
	
	//获取两个条件的分页数据
	public List<OrderModel> QueryBy2(String startTime,String endTime,Integer pageStart,Integer pageSize){
		List<OrderModel> order_2=goodsSaleMapper.queryBy2(startTime, endTime, pageStart, pageSize);
		return order_2;
	}
	
	//统计两个条件的数据量
	public int CountQuery2(String startTime,String endTime) {
		int count=goodsSaleMapper.countQuery2(startTime, endTime);
		return count;
	}
	
	//获取一个条件的分页数据
	public List<OrderModel> QueryBy1(String idOrName,Integer pageStart,Integer pageSize){
		List<OrderModel> order_1=goodsSaleMapper.queryBy1(idOrName, pageStart, pageSize);
		return order_1;
	}
	
	//统计一个条件的数据量
	public int CountQuery1(String idOrName) {
		int count=goodsSaleMapper.countQuery1(idOrName);
		return count;
	}
}
