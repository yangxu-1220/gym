package com.gym.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.gym.mapper.ShopMapper;
import com.gym.model.GoodsModel;

@Service
public class ShopService {
	
	@Autowired
	ShopMapper shopMapper;
	
	//拿到分页数据
	public Map<String,Object> SelectAllShop(String goodsType,
			@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
			@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<GoodsModel> goodsList=shopMapper.SelectAllShop(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();		
		int	rowSize=shopMapper.GetGoodsTotal();		
		data.put("rowSize",rowSize);
		data.put("pageStart", pageStart);
		data.put("limit", pageSize);
		data.put("data", goodsList);
		return data;
	}
	
	//拿到购买页面的数据
	public GoodsModel selectGoodsInfoById(int id) {
		GoodsModel data=shopMapper.selectGoodsInfoById(id);
		return data;
	}
	
	//根据类型去查所属商品
	public Map<String,Object> GetGoodsByType(String goodsType,
			@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
			@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<GoodsModel> goodsList=shopMapper.getGoodsByType(goodsType,pageStart,pageSize);
		Map<String,Object> data2=new HashMap<String,Object>();
		int rowSize=shopMapper.getNumOfType(goodsType);
		data2.put("rowSize",rowSize);
		data2.put("pageStart", pageStart);
		data2.put("limit", pageSize);
		data2.put("data", goodsList);
		return data2;
	}
}
