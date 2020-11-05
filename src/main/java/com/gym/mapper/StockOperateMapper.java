package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.gym.model.StockOperateModel;

@Repository
public interface StockOperateMapper {
	
	/**
	 * 向库存操作记录表中添加一条新的记录
	 * @param stockOperateModel
	 */
	@Insert("INSERT INTO tb_operate_stock " + 
			"VALUES(#{operateStockId},#{stuffId},#{goodsId}," + 
			"#{operateStockState},#{operateStockNumber}," + 
			"#{operateStockPrice},#{operateStockTime})")
	public void insertOperateStock(StockOperateModel stockOperateModel);
	
	/**
	 * 根据ID去删除对应的操作记录
	 * @param operateStockId
	 */
	@Delete("DELETE FROM tb_operate_stock WHERE operate_stock_id=#{operateStockId}")
	public void deleteOperateById(@Param("operateStockId") String operateStockId);
	
	/**
	 * 获取Operate页面的分页数据
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("SELECT tb_operate_stock.*,tb_stuff.stuff_name,tb_goods.goods_name " + 
			"FROM tb_operate_stock,tb_stuff,tb_goods " + 
			"WHERE tb_stuff.stuff_id=tb_operate_stock.stuff_id " + 
			"and tb_goods.goods_id=tb_operate_stock.goods_id " +
			"order by tb_operate_stock.operate_stock_id desc " + 
			"limit #{pageStart},#{pageSize}")
	public List<StockOperateModel> getOperateBtween(@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);

	/**
	 * 统计operate表中数据数量
	 * @return
	 */
	@Select("SELECT count(1) FROM tb_operate_stock")
	public int countOperate();
	
	/**
	 * 根据三个条件查询
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@Select("select * " + 
			"from " + 
			"	(select tb_operate_stock.operate_stock_id, " + 
			"	stuff_name,goods_name,operate_stock_state, " + 
			"	operate_stock_number,operate_stock_price, " + 
			"	operate_stock_time,tb_goods.goods_id,tb_stuff.stuff_id " + 
			"	from tb_operate_stock,tb_goods,tb_stuff " + 
			"   where tb_operate_stock.operate_stock_time>=#{startTime}  " + 
			"	and tb_operate_stock.operate_stock_time<=#{endTime}  " + 
			"	and tb_operate_stock.goods_id=tb_goods.goods_id  " + 
			"	and tb_operate_stock.stuff_id=tb_stuff.stuff_id) as temp  " + 
			"where goods_id=#{gOS} or goods_name=#{gOS} or stuff_id=#{gOS} or stuff_name=#{gOS}" +
			"LIMIT #{pageStart},#{pageSize}")
	public List<StockOperateModel> queryBy3(@Param("startTime") String startTime,
											@Param("endTime") String endTime,
											@Param("gOS") String gOS,
											@Param("pageStart") Integer pageStart,
											@Param("pageSize") Integer pageSize);
	
	/**
	 * 三个条件的数量统计
	 * @return
	 */
	@Select("select count(*) " + 
			"from  " + 
			"	(select tb_operate_stock.operate_stock_id,stuff_name, " + 
			"	goods_name,operate_stock_state,operate_stock_number,  " + 
			"	operate_stock_price,operate_stock_time,tb_goods.goods_id,tb_stuff.stuff_id " + 
			"   from tb_operate_stock,tb_goods,tb_stuff " + 
			"   where tb_operate_stock.operate_stock_time>=#{startTime}  " + 
			"   and tb_operate_stock.operate_stock_time<=#{endTime} " + 
			"	and  tb_operate_stock.goods_id=tb_goods.goods_id and tb_operate_stock.stuff_id=tb_stuff.stuff_id) as temp  " + 
			"where goods_id=#{gOS} or goods_name=#{gOS} or stuff_id=#{gOS} or stuff_name=#{gOS} ")
	public int countQuery3(@Param("startTime") String startTime,
						   @Param("endTime") String endTime,
						   @Param("gOS") String gOS);
	
	/**
	 * 根据一个条件去查询
	 * @param gOS
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("select * " + 
			"from  " + 
			"			(select tb_operate_stock.operate_stock_id,stuff_name,goods_name,operate_stock_state,operate_stock_number," +
			"			operate_stock_price,operate_stock_time,tb_goods.goods_id,tb_stuff.stuff_id " + 
			"		  	from tb_operate_stock,tb_goods,tb_stuff " + 
			"     		where tb_operate_stock.goods_id=tb_goods.goods_id and tb_operate_stock.stuff_id=tb_stuff.stuff_id) as temp  " + 
			"where goods_id=#{gOS} or goods_name=#{gOS} or stuff_id=#{gOS} or stuff_name=#{gOS} " + 
			"LIMIT #{pageStart},#{pageSize}")
	public List<StockOperateModel> queryBy1(@Param("gOS") String gOS,
											@Param("pageStart") Integer pageStart,
											@Param("pageSize") Integer pageSize);
	
	/**
	 * 统计一个条件的查询数量
	 * @param gOS
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("select count(*) " + 
			"from  " + 
			"			(select tb_operate_stock.operate_stock_id,stuff_name,goods_name,operate_stock_state,operate_stock_number," +
			"			operate_stock_price,operate_stock_time,tb_goods.goods_id,tb_stuff.stuff_id " + 
			"		  	from tb_operate_stock,tb_goods,tb_stuff " + 
			"     		where tb_operate_stock.goods_id=tb_goods.goods_id and tb_operate_stock.stuff_id=tb_stuff.stuff_id) as temp  " + 
			"where goods_id=#{gOS} or goods_name=#{gOS} or stuff_id=#{gOS} or stuff_name=#{gOS} ")
	public int conuntQuery1(@Param("gOS") String gOS);
	
	/**
	 * 根据两个时间查询结果集
	 * @param startTime
	 * @param endTime
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("select tb_operate_stock.operate_stock_id,stuff_name," + 
			"goods_name,operate_stock_state,operate_stock_number," + 
			"operate_stock_price,operate_stock_time,tb_goods.goods_id,tb_stuff.stuff_id " + 
			"from tb_operate_stock,tb_goods,tb_stuff " + 
			"where tb_operate_stock.operate_stock_time>=#{startTime} " + 
			"and tb_operate_stock.operate_stock_time<=#{endTime} " + 
			"and tb_operate_stock.goods_id=tb_goods.goods_id  " + 
			"and tb_operate_stock.stuff_id=tb_stuff.stuff_id " + 
			"LIMIT #{pageStart},#{pageSize}")
	public List<StockOperateModel> queryBy2(@Param("startTime") String startTime,
											@Param("endTime") String endTime,											
											@Param("pageStart") Integer pageStart,
											@Param("pageSize") Integer pageSize);
	
	/**
	 * 统计根据两个条件查询的数量
	 * @param startTime
	 * @param endTime
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("select count(*) " + 
			"from tb_operate_stock,tb_goods,tb_stuff " + 
			"where tb_operate_stock.operate_stock_time>=#{startTime} " + 
			"and tb_operate_stock.operate_stock_time<=#{endTime} " + 
			"and tb_operate_stock.goods_id=tb_goods.goods_id  " + 
			"and tb_operate_stock.stuff_id=tb_stuff.stuff_id ")
	public int countQuery2(@Param("startTime") String startTime,@Param("endTime") String endTime);

}

	
