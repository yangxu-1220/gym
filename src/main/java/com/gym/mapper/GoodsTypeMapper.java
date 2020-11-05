package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gym.model.GoodsTypeModel;

@Repository
public interface GoodsTypeMapper {
	
	@Select("SELECT * FROM tb_goods_type")
	public List<GoodsTypeModel> getAllGoodsType();
	
	@Select("SELECT * FROM tb_goods_type WHERE goods_type_id=#{goodsTypeId}")
	public GoodsTypeModel getGoodsTypeById(int id);
	
	@Insert("INSERT INTO tb_goods_type VALUES(null,#{goodsTypeName})")
	public boolean insertGoodsType(GoodsTypeModel goodsTypeModel);
	
	@Update("UPDATE tb_goods_type SET goods_type_name=#{goodsTypeName} WHERE goods_type_id=#{goodsTypeId}")
	public boolean updateGoodsType(GoodsTypeModel goodsTypeModel);
	
	@Delete("DELETE FROM tb_goods_type WHERE goods_type_id=#{goodsTypeId}")
	public boolean deleteGoodsTypeById(int id);
	
	@Select("SELECT * FROM tb_goods_type limit #{pageStart},#{pageSize}")
	public List<GoodsTypeModel> getGoodsTypeBtween(@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);
	
	@Select("SELECT count(1) FROM tb_goods_type")
	public int getTypeTotal();	
}
