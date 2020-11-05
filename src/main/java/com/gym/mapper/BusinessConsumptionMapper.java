package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.gym.model.BusinessConsumptionModel;

public interface BusinessConsumptionMapper {
//	sql字符串
	String sqlString="select tb_goods.goods_price as singlePrice,tb_sale.sale_id as saleId,tb_goods.goods_name as goodsName,tb_sale.sale_time as saleTime,tb_sale.sale_price as price,tb_sale.sale_number as number " + 
			"from tb_sale,tb_goods " + 
			"where tb_sale.goods_id=tb_goods.goods_id and sale_time>=#{startTime} and sale_time<=#{endTime}";
	//绘制条形图和折线图，计算一段时间内商品销售数量和销售总金额
	String sqlString_2="select SUBSTRING_INDEX(sale_time,' ',1) as saleTime,SUM(sale_number) as number,SUM(sale_price) as price " + 
			"from tb_sale " + 
			"where sale_time>=#{startTime} and sale_time<=#{endTime} " + 
			"GROUP BY SUBSTRING_INDEX(sale_time,' ',1)";
	//绘制饼图，计算一段时间内单个商品的销售数量和销售金额
	String sqlString_3="select goods_name as goodsName,SUM(sale_number) as number,SUM(sale_price) as price " + 
			"from tb_sale,tb_goods " + 
			"where tb_sale.goods_id=tb_goods.goods_id and sale_time>=#{startTime} and sale_time<=#{endTime} " + 
			"GROUP BY goods_name";
	//统计总数量
	String sqlString_4="select count(DISTINCT tb_sale.sale_id) " + 
			"from tb_sale,tb_goods " + 
			"where tb_sale.goods_id=tb_goods.goods_id and sale_time>=#{startTime} and sale_time<=#{endTime}";
	
	@Select(sqlString+" limit #{page},#{limit}")
	public List<BusinessConsumptionModel> selectBusinessConsumption(String startTime,String endTime,int page,int limit);
	
	@Select(sqlString_4)
	public int count(String startTime,String endTime);
	
	//绘制条形图和折线图，计算一段时间内商品销售数量和销售总金额
	@Select(sqlString_2)
	public List<BusinessConsumptionModel> selectBusinessonBarLine(String startTime,String endTime);
	
	//绘制饼图，计算一段时间内单个商品的销售数量和销售金额
	@Select(sqlString_3)
	public List<BusinessConsumptionModel> selectBusinessonPie(String startTime,String endTime);
}
