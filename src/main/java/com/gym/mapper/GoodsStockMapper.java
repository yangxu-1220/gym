package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gym.model.GoodsModel;

@Repository
public interface GoodsStockMapper {
	
	/**
	 * 库存页面的拿取分页数据
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("SELECT * FROM tb_goods_stock LIMIT #{pageStart},#{pageSize}")
	public List<GoodsModel> getStockBtween(@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);
	
	/**
	 * 统计stock的数据数量
	 * @return
	 */
	@Select("SELECT count(1) FROM tb_goods_stock")
	public int countStock();
	
	/**
	 * 商品入库
	 * @param goodsModel
	 */
	@Update("UPDATE tb_goods_stock SET goods_stock_number=goods_stock_number+#{number} WHERE goods_id=#{goodsId}")
	public void stockIn(GoodsModel goodsModel);
	
	/**
	 * 商品出库
	 * @param goodsModel
	 */
	@Update("UPDATE tb_goods_stock SET goods_stock_number=goods_stock_number-#{number} WHERE goods_id=#{goodsId}")
	public void stockOut(GoodsModel goodsModel);
	
	/**
	 * 根据商品ID获取库存
	 * @param goodsId
	 * @return
	 */
	@Select("SELECT * FROM tb_goods_stock WHERE goods_id=#{goodsId}")
	public GoodsModel getStockById(@Param("goodsId") Integer goodsId);	
	
	/**
	 * 根据ID获得商品的库存量
	 * @param goodsId
	 * @return
	 */
	@Select("SELECT goods_stock_number FROM tb_goods_stock WHERE goods_id=#{goodsId}")
	public int getNumberById(@Param("goodsId") Integer goodsId);
	
	/**
	 * 根据商品ID及数量去修改库存数量
	 * @param number
	 * @param goodsId
	 */
	@Update("UPDATE tb_goods_stock SET goods_stock_number=goods_stock_number-#{number} WHERE goods_id=#{goodsId}")
	public void subStockByGoodsId(@Param("goodsId") Integer goodsId,@Param("number") Integer number);
}
