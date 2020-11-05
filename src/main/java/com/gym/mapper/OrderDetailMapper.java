package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gym.model.OrderDetailModel;

@Repository
public interface OrderDetailMapper {
	
	/**
	 * 功能:用户在商品购买页点击加入购物车时向订单详情表中插入一条商品详情记录
	 */
	@Insert("INSERT INTO tb_order_detail VALUES (#{goodsId},#{orderId},#{goodsName},#{goodsNumber},#{goodsPrice},#{totalPrice})")
	public void createOrderDetail(OrderDetailModel orderDetail);
	
	/**
	 * 功能:检查一个订单中是否添加了重复商品
	 */
	@Select("SELECT * FROM tb_order_detail WHERE goods_id=#{goodsId} and order_id=#{orderId}")
	public OrderDetailModel checkOrderDetailByGoodsIdAndOrderId(OrderDetailModel orderDetail);
	
	/**
	 * 功能:根据用户ID查询订单ID，再根据订单ID查询对应的所有订单详情
	 */
	@Select("select * from tb_order,tb_order_detail" + 
			"where tb_order.user_id=#{userId} && tb_order.order_id=tb_order_detail.order_id && tb_order.order_state=\"未支付\"")
	public List<OrderDetailModel> getOrderDetailByUserId(@Param("userId") int userId);
	
	/**
	 * 功能:用户在购物车中更改商品数量后需要结算商品总价的时候修改商品数量
	 */
	@Update("UPDATE tb_order_detail SET goods_number=#{goodsNumber} WHERE goods_id=#{goodsId}")
	public void renewGoodsNumber(OrderDetailModel orderDetail);
	
	/**
	 * 功能:用户在购物车中删除选中的商品记录
	 */
	@Delete("DELTE FROM tb_order_detail WHERE goods_id=#{goodsId}")
	public void delelteGoodsDetailById(int id);
	
	/**
	 * 功能:拿到订单详情里面属于这个订单的所有订单详情信息
	 */
	@Select("SELECT * FROM tb_order_detail WHERE order_id=#{id}")
	public List<OrderDetailModel> selectOrderDetailById(@Param("id") int id);
}
