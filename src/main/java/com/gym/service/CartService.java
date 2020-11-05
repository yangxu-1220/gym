package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.CartMapper;
import com.gym.model.OrderDetailModel;

@Service
public class CartService {
	
	@Autowired
	CartMapper cartMapper;
	
	/**
	 * 返回根据userId查询的订单详情,有多条订单详情
	 * @param userId
	 * @return
	 */
	public List<OrderDetailModel> SelectDetailById(Integer userId) {
		List<OrderDetailModel> orderDetail=cartMapper.selectDetailById(userId);
		return orderDetail;
	}
	
	/**
	 *  根据商品ID和订单ID去删除一条订单详情记录
	 * @param goodsId
	 * @param orderId
	 */
	public void DeleteDetailById(Integer goodsId,String orderId) {
		cartMapper.DeleteDetailById(goodsId, orderId);
	}
	
	/**
	 * 根据userId和未支付查询orderId
	 * @param userId
	 * @return
	 */
	public String GetOrderIdByUserId(Integer userId) {
		String orderId=cartMapper.getOrderIdByUserId(userId);
		return orderId;
	}
}
