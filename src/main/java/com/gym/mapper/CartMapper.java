package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.gym.model.OrderDetailModel;

@Repository
public interface CartMapper {
	
	/**
	 * 根据用户的userId去查这个用户未支付订单中的订单详情
	 * @param userId
	 * @return
	 */
	@Select("select tb_order_detail.goods_name," +
			"tb_order_detail.goods_id," + 
			"tb_order_detail.order_id," + 
			"goods_number,goods_price," + 
			"total_price,goods_stock_number " + 
			"from tb_order,tb_order_detail,tb_goods_stock " + 
			"where tb_order.user_id=#{userId} " + 
			"and tb_order.order_id=tb_order_detail.order_id " + 
			"and tb_order.order_state='未支付' " + 
			"and tb_order_detail.goods_id=tb_goods_stock.goods_id")
	public List<OrderDetailModel> selectDetailById(@Param("userId") Integer userId);
	
	/**
	 * 根据订单ID和商品ID删除一条订单详情记录
	 */
	@Select("DELETE FROM tb_order_detail WHERE goods_id=#{goodsId} and order_id=#{orderId}")
	public void DeleteDetailById(@Param("goodsId") Integer goodsId,@Param("orderId") String orderId);
	
	/**
	 * 根据userID获得orderId
	 * @param userId
	 * @return
	 */
	@Select("SELECT order_id FROM tb_order WHERE user_id=#{userId} and order_state='未支付'")
	public String getOrderIdByUserId(@Param("userId") Integer userId);
}
