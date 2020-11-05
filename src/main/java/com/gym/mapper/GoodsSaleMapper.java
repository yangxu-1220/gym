package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.gym.model.OrderDetailModel;
import com.gym.model.OrderModel;

@Repository
public interface GoodsSaleMapper {
	
	/**
	 * 获取销售信息页面的分页数据
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("SELECT tb_order.*,tb_member.member_name,tb_member.member_id " +
			"FROM tb_order,tb_member "  +
			"WHERE tb_order.user_id=tb_member.member_id " +
			"order by tb_order.order_id desc " +
			"LIMIT #{pageStart},#{pageSize}")
	public List<OrderModel> getGoodsSaleBtween(@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);
	
	/**
	 * 获取order表中的数据总数
	 * @return
	 */
	@Select("SELECT count(1) FROM tb_order")
	public int countGoodsSale();
	
	/**
	 * 根据订单号删除一条记录
	 * @param orderId
	 */
	@Delete("DELETE FROM tb_order WHERE order_id=#{orderId}")
	public void deleteOrderById(@Param("orderId") String orderId);
	
	/**
	 * 根据orderId查对应的商品详情
	 * @param orderId
	 * @return
	 */
	@Select("SELECT goods_name,goods_number,total_price FROM tb_order_detail WHERE order_id=#{orderId}")
	public List<OrderDetailModel> getDetailById(@Param("orderId") String orderId);
	
	/**
	 * 三个条件查询的分页数据
	 * @param startTime
	 * @param endTime
	 * @param idOrName
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("select * " + 
			"from  " + 
			"	(select order_id,order_total_price,create_time,order_state,member_id,member_name " + 
			"   from tb_order,tb_member " + 
			"   where tb_order.create_time>=#{startTime} and tb_order.create_time<=#{endTime} and tb_order.user_id=tb_member.member_id) as temp  " + 
			"where member_id=#{idOrName} or member_name=#{idOrName} " + 
			"Limit #{pageStart},#{pageSize}")
	public List<OrderModel> queryBy3(@Param("startTime") String startTime,
									 @Param("endTime") String endTime,
									 @Param("idOrName") String idOrName,
									 @Param("pageStart") Integer pageStart,
									 @Param("pageSize") Integer pageSize);
	
	/**
	 * 统计三个条件查询的数量
	 * @param startTime
	 * @param endTime
	 * @param idOrName
	 * @return
	 */
	@Select("select count(*) " + 
			"from  " + 
			"	(select order_id,order_total_price,create_time,order_state,member_id,member_name " + 
			"   from tb_order,tb_member " + 
			"   where tb_order.create_time>=#{startTime} and tb_order.create_time<=#{endTime} and tb_order.user_id=tb_member.member_id) as temp  " + 
			"where member_id=#{idOrName} or member_name=#{idOrName} ")
	public int countQuery3(@Param("startTime") String startTime,
										@Param("endTime") String endTime,
										@Param("idOrName") String idOrName);
	
	/**
	 * 拿到两个数据的分页数据
	 * @param startTime
	 * @param endTime
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("select order_id,order_total_price,create_time,order_state,member_id,member_name " + 
			"from tb_order,tb_member " + 
			"where tb_order.create_time>=#{startTime}  " + 
			"and tb_order.create_time<=#{endTime}  " + 
			"and tb_order.user_id=tb_member.member_id " + 
			"Limit #{pageStart},#{pageSize}")
	public List<OrderModel> queryBy2(@Param("startTime") String startTime,
									 @Param("endTime") String endTime,
									 @Param("pageStart") Integer pageStart,
									 @Param("pageSize") Integer pageSize);
	
	/**
	 * 统计两个条件查询的数量
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@Select("select count(*) " + 
			"from tb_order,tb_member " + 
			"where tb_order.create_time>=#{startTime}  " + 
			"and tb_order.create_time<=#{endTime}  " + 
			"and tb_order.user_id=tb_member.member_id ")
	public int countQuery2(@Param("startTime") String startTime,
						   @Param("endTime") String endTime);
	
	/**
	 * 拿到一个条件查询的分页数据
	 * @param idOrName
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("select * " + 
			"from  " + 
			"	(select order_id,order_total_price,create_time,order_state,member_id,member_name " + 
			"   from tb_order,tb_member " + 
			"   where tb_order.user_id=tb_member.member_id) as temp " + 
			"where member_id=#{idOrName} or member_name=#{idOrName} " + 
			"Limit #{pageStart},#{pageSize}")
	public List<OrderModel> queryBy1(@Param("idOrName") String idOrName,
									 @Param("pageStart") Integer pageStart,
									 @Param("pageSize") Integer pageSize);
	
	/**
	 * 统计一个条件查询到的数据量
	 * @param idOrName
	 * @return
	 */
	@Select("select count(*) " + 
			"from  " + 
			"	(select order_id,order_total_price,create_time,order_state,member_id,member_name " + 
			"   from tb_order,tb_member " + 
			"   where tb_order.user_id=tb_member.member_id) as temp " + 
			"where member_id=#{idOrName} or member_name=#{idOrName}")
	public int countQuery1(@Param("idOrName") String idOrName);
		
}
