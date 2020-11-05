package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.gym.model.OrderDetailModel;
import com.gym.model.OrderModel;

@Repository
public interface OrderDetailForMemberMapper {
	
	/**
	 * 根据用户ID获取所有该用户的订单
	 * @param userId
	 * @return
	 */
	@Select("SELECT * FROM tb_order WHERE tb_order.user_id=#{userId} LIMIT #{pageStart},#{pageSize}")
	public List<OrderModel> getMyOrderBtween(@Param("userId") Integer userId,
											 @Param("pageStart") Integer pageStart,
											 @Param("pageSize") Integer pageSize);
	
	/**
	 * 获取个人订单总数
	 * @param userId
	 * @return
	 */
	@Select("SELECT count(1) FROM tb_order WHERE tb_order.user_id=#{userId}")
	public int countMyOrder(@Param("userId") Integer userId);
	
	/**
	 * 根据订单号删除一条订单信息
	 * @param orderId	
	 */
	@Delete("DELETE FROM tb_order WHERE tb_order.order_id=#{orderId}")
	public void deleteOrderById(@Param("orderId") String orderId);
	
	/**
	 * 根据订单ID查询对应的所有商品详情
	 * @param orderId
	 * @return
	 */
	@Select("SELECT * FROM tb_order_detail WHERE tb_order_detail.order_id=#{orderId}")
	public List<OrderDetailModel> getDetailById(@Param("orderId") String orderId);
}

