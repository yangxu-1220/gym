package com.gym.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gym.model.OrderModel;

@Repository
public interface OrderMapper {
	
	/**
	 * 功能：创建一条用户订单记录，并给部分字段赋初值orderId,createTime,orderState,userId
	 */
	@Insert("INSERT INTO tb_order VALUES(#{orderId},#{orderTotalPrice},#{createTime},#{orderState},#{userId})")
	public void createOrder(OrderModel order);
				
	/**
	 * 功能：用户结算价格时修改总价orderTotalPrice
	 */
	@Update("UPDATE tb_order SET order_total_price=#{orderTotalPrice} WHERE order_state=#{orderState} && user_id=#{userId}")
	public void renewOrderTotalPrice(OrderModel order);
	
	/**
	 * 功能：用户支付成功后需要修改订单的支付状态orderState
	 */
	@Update("UPDATE tb_order SET order_state=#{orderState} WHERE order_state=#{orderState} && user_id=#{userId}")
	public void renewOrderState(OrderModel order);
	
	/**
	 * 功能：用户取消订单时删除订单记录
	 */
	@Delete("DELETE FROM tb_order WHERE order_id=#{orderId}")
	public void cancelOrderById(int id);
	
	/**
	 * 功能:根据用户ID和订单的支付状态去查询订单,检查订单是否存在
	 */
	@Select("SELECT * FROM tb_order WHERE order_state=#{orderState} and user_id=#{userId}")
	public OrderModel selectOrderByStateAndId(@Param("orderState") String orderState,@Param("userId") int userId);
	
	/**
	 * 根据userId去删除未支付的订单
	 * @param userId
	 */
	@Delete("DELETE FROM tb_order WHERE user_id=#{userId} and order_state='未支付'")
	public void deleteOrderById(@Param("userId") Integer userId);
	
	/**
	 * 根据订单ID去修改订单的状态为已支付 修改订单总价
	 * @param orderId
	 */
	@Update("UPDATE tb_order SET order_state=\"已支付 \",order_total_price=#{orderTotalPrice}  WHERE order_id=#{orderId}")
	public void renewStateByOrderId(@Param("orderTotalPrice") float orderTotalPrice,@Param("orderId") String orderId);
}
