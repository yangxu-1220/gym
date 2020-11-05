package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.gym.model.GoodsModel;

@Repository
public interface ShopMapper {
	/**
	 * 功能：查询商品表和库存表的商品名称、商品单价、商品库存
	 * @param
	 * @return
	 */
	@Select("SELECT tb_goods.goods_id,tb_goods.goods_name,"
			+ "tb_goods.goods_price,tb_goods_stock.goods_stock_number,"
			+ "tb_goods.goods_type FROM tb_goods,tb_goods_stock "
			+ "WHERE tb_goods.goods_id=tb_goods_stock.goods_id && tb_goods.goods_state=\"已上架\""
			+ "limit #{pageStart},#{pageSize}")
	public List<GoodsModel> SelectAllShop(@Param("pageStart") int pageStart,@Param("pageSize") int pageSize);
	
	/**
	 * 功能：查询商品记录数量
	 */
	@Select("SELECT count(1) FROM tb_goods WHERE tb_goods.goods_state=\"已上架\"")
	public int GetGoodsTotal();
	
	/**
	 * 功能：根据ID查询对应的商品信息和商品库存信息
	 */
	@Select("SELECT tb_goods.goods_id,tb_goods.goods_name,tb_goods.goods_price,"
			+ "tb_goods.goods_type,tb_goods.goods_unit,"
			+ "tb_goods_stock.goods_stock_number,tb_goods.goods_image "
			+ "FROM tb_goods,tb_goods_stock where tb_goods.goods_id=#{goodsId} "
			+ "&& tb_goods.goods_id=tb_goods_stock.goods_id ")
	public GoodsModel selectGoodsInfoById(int id);
	
	/**
	 * 功能:根据商品的类型去查询所属商品的信息
	 */
	@Select("SELECT * FROM tb_goods,tb_goods_stock "
			+ "WHERE tb_goods.goods_id=tb_goods_stock.goods_id "
			+ "&& tb_goods.goods_type=#{goodsType} "
			+ "&& tb_goods.goods_state=\"已上架\" "
			+ "limit #{pageStart},#{pageSize}")
	public List<GoodsModel> getGoodsByType(@Param("goodsType") String goodsType
										  ,@Param("pageStart") int pageStart
										  ,@Param("pageSize") int pageSize);
	
	/**
	 * 功能：根据类型获得该类型的数据条数
	 */
	@Select("SELECT count(1) FROM tb_goods "
			+ "WHERE goods_type=#{goodsType} "
			+ "&& tb_goods.goods_state=\"已上架\"")
	public int getNumOfType(@Param("goodsType") String goodsType);
}
