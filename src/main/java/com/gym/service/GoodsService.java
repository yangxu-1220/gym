package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.GoodsMapper;
import com.gym.model.GoodsModel;

@Service
public class GoodsService {
	
	@Autowired
	GoodsMapper goodsMapper;	
	//获取goods表中以pageStart开始的pageSize条数据
	public List<GoodsModel> getGoodsBtween(int pageStart,int pageSize){
		List<GoodsModel> currentGoods=goodsMapper.getGoodsBtween(pageStart, pageSize);
		return currentGoods;		
	}
	//获取goods表中记录的总数
	public int getGoodsTotal() {
		int rowSize=goodsMapper.getGoodsTotal();	
		return rowSize;
	}
	//获取goods表中所有的数据
	public List<GoodsModel> getAllGoods(){
		List<GoodsModel> allGoods=goodsMapper.getAllGoods();
		return allGoods;
	}
	//根据ID去删除对应的记录
	public boolean deleteGoodsById(int id) {
		boolean result=goodsMapper.deleteGoodsById(id);
		if(result)
			return true;
		else
			return false;
	}
	//根据选择的ID去查询数据库中对应的商品信息
	public GoodsModel selectGoodsById(int id) {
		GoodsModel goodsModel = goodsMapper.selectGoodsById(id);
		return goodsModel;	
	}
	//修改商品
	public boolean updateGoods(GoodsModel goods) {
		GoodsModel goods_1=goodsMapper.selectGoodsById(goods.getGoodsId());
		if(goods.getGoodsImage()==null)
			goods.setGoodsImage(goods_1.getGoodsImage());
		boolean result=goodsMapper.updateGoods(goods);
		if(result)
			return true;
		else
			return false;
	}
	//添加商品
	public boolean insertGoods(GoodsModel goodsModel) {
		int id=goodsModel.getGoodsId();
		goodsModel.setGoodsState("未上架");
		GoodsModel result=goodsMapper.selectGoodsById(id);
		if(result!=null) {
			return false;
		}
		else {
			goodsMapper.insertGoods(goodsModel);//新增商品信息
			goodsMapper.insertGoodsStock(goodsModel);//新增商品库存信息	
			return true;
		}	
	}
	
	//上架商品
	public void SetStateToYes(Integer goodsId) {
		goodsMapper.setStateToYes(goodsId);
	}
	
	//下架商品
	public void SetStateToNo(Integer goodsId) {
		goodsMapper.setStateToNo(goodsId);
	}
}
