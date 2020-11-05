package com.gym.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.GoodsTypeMapper;
import com.gym.model.GoodsTypeModel;

@Service
public class GoodsTypeService {
	
	@Autowired
	GoodsTypeMapper goodsTypeMapper;
	
	//查询商品类型表中的全部类型
	public List<GoodsTypeModel> GetAllGoodsType(){
		return goodsTypeMapper.getAllGoodsType();
	}
	
	
	//根据点击的那一条记录的ID去查询对应的商品类型记录
	public GoodsTypeModel getGoodsTypeById(int id) {
		return goodsTypeMapper.getGoodsTypeById(id);
	}
	//插入一条商品类型记录，插入成功返回true，插入失败返回false
	public boolean InsertGoodsType(GoodsTypeModel goodsTypeModel) {
		boolean count=goodsTypeMapper.insertGoodsType(goodsTypeModel);
		if(count)
			return true;
		else
			return false;
	}
	//修改商品类型记录，插入成功返回true，插入失败返回false
	public boolean UpdateGoodsType(GoodsTypeModel goodsTypeModel) {
		boolean count=goodsTypeMapper.updateGoodsType(goodsTypeModel);
		if(count)
			return true;
		else
			return false;
	}
	//删除商品类型记录，删除成功返回true，删除失败返回false
	public boolean DeleteGoodsTypeById(Integer id) {
		boolean count=goodsTypeMapper.deleteGoodsTypeById(id);
		if(count)
			return true;
		else
			return false;
	}
	//根据起始位置和条数去查询商品类型
	public List<GoodsTypeModel> GetGoodsTypeBtween(int pageStart,int pageSize){
		List<GoodsTypeModel> currentTypeModel=goodsTypeMapper.getGoodsTypeBtween(pageStart, pageSize);
		return currentTypeModel;
	}
	//查询商品类型表中的商品数据
	public int GetTypeTotal() {
		int count=goodsTypeMapper.getTypeTotal();
		return count;
	}
}
