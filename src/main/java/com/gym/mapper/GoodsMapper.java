package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gym.model.GoodsModel;


@Repository
public interface GoodsMapper {
	//拿到分页数据
	@Select("SELECT * FROM tb_goods limit #{pageStart},#{pageSize}")
	public List<GoodsModel> getGoodsBtween(@Param("pageStart") int pageStart,@Param("pageSize") int pageSize);
	//统计记录数量
	@Select("SELECT count(1) FROM tb_goods")
	public int getGoodsTotal();
	//全查
	@Select("SELECT * FROM tb_goods")
	public List<GoodsModel> getAllGoods();
	//根据ID删
	@Delete("DELETE FROM tb_goods where goods_id=#{goodsId}")
	public boolean deleteGoodsById(int id);
	//根据ID查
	@Select("SELECT * FROM tb_goods where goods_id=#{goodsId}")
	public GoodsModel selectGoodsById(int id);
	//根据ID改
	@Update("UPDATE tb_goods SET goods_name=#{goodsName},goods_type=#{goodsType},goods_price=#{goodsPrice},goods_unit=#{goodsUnit},goods_note=#{goodsNote},goods_image=#{goodsImage} WHERE goods_id=#{goodsId}")
	public boolean updateGoods(GoodsModel goodsModel);
	//新增商品信息
	@Insert("INSERT INTO tb_goods VALUES(#{goodsId},#{goodsName},#{goodsType},#{goodsPrice},#{goodsUnit},#{goodsNote},#{goodsState},#{goodsImage})")
	public void insertGoods(GoodsModel goodsModel);
	//新增商品库存信息
	@Insert("INSERT INTO tb_goods_stock VALUES(#{goodsId},#{goodsName},#{goodsStockNumber})")
	public void insertGoodsStock(GoodsModel goodsModel);
	//上架商品
	@Update("UPDATE tb_goods SET goods_state=\"已上架\" WHERE goods_id=#{goodsId}")
	public void setStateToYes(@Param("goodsId") int goodsId);
	//下架商品
	@Update("UPDATE tb_goods SET goods_state=\"未上架\" WHERE goods_id=#{goodsId}")
	public void setStateToNo(@Param("goodsId") int goodsId);	
	//根据ID获得商品价格
	@Select("SELECT goods_price FROM tb_goods WHERE goods_id=#{goodsId}")
	public float getPriceById(@Param("goodsId") Integer goodsId);
}
