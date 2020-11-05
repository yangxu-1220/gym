package com.gym.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.GoodsMapper;
import com.gym.mapper.GoodsStockMapper;
import com.gym.mapper.StockOperateMapper;
import com.gym.model.GoodsModel;
import com.gym.model.StockOperateModel;

@Service
public class GoodsStockService {

	@Autowired
	GoodsStockMapper goodsStockMapper;
	@Autowired 
	StockOperateMapper operateStockMapper;
	@Autowired
	GoodsMapper goodsMapper;
	
	/**
	 * 库存页面的拿取分页数据
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public List<GoodsModel> GetStockBtween(Integer pageStart,Integer pageSize){
		List<GoodsModel> stock=goodsStockMapper.getStockBtween(pageStart, pageSize);
		return stock;
	}
	
	/**
	 * 统计stock的数据数量
	 * @return
	 */
	public int CountStock() {
		int count=goodsStockMapper.countStock();
		return count;
	}
	
	/**
	 * 商品入库
	 * @param goodsModel
	 */
	public void StockIn(GoodsModel goodsModel,StockOperateModel stockOperateModel) {
		Date currentDate = new Date();
		SimpleDateFormat nowTime = new SimpleDateFormat("yyyyMMddHHmmss");
        String operateStockTime = nowTime.format(currentDate);			//获取时间(由当前时间决定)
        int stuffId=stockOperateModel.getStuffId();						//获取ID(由登录者ID决定)
        String operateStockId=stuffId+operateStockTime;					//ID+时间作为操作ID
        int goodsId=goodsModel.getGoodsId();							//获取商品ID(goods对象所带)
        float goodsPrice=goodsMapper.getPriceById(goodsId);				//获取商品价格(由goods对象ID去查)
        int number=goodsModel.getNumber();								//获取操作商品数量(由表单传入goods对象)
        String operateStockState=goodsModel.getOperateStockState();		//获取操作状态(由表单传入goods对象)
        
        stockOperateModel.setOperateStockId(operateStockId);			//给操作ID赋值
        stockOperateModel.setOperateStockTime(operateStockTime); 		//给操作时间赋值
        stockOperateModel.setGoodsId(goodsId); 							//给商品ID赋值
        stockOperateModel.setOperateStockNumber(number);	   			//给入库数量赋值
        stockOperateModel.setOperateStockPrice(goodsPrice*number);		//给操作总价赋值
        stockOperateModel.setOperateStockState(operateStockState); 		//给操作状态赋值
        																//stuffId在上一层Controller已经赋值
        
		goodsStockMapper.stockIn(goodsModel);							//修改库存表的数量
		operateStockMapper.insertOperateStock(stockOperateModel);		//在操作表新增一条记录
	}
	
	/**
	 * 商品出库
	 * @param goodsModel
	 */
	public void StockOut(GoodsModel goodsModel,StockOperateModel stockOperateModel) {
		Date currentDate = new Date();
		SimpleDateFormat nowTime = new SimpleDateFormat("yyyyMMddHHmmss");
        String operateStockTime = nowTime.format(currentDate);			//获取时间(由当前时间决定)
        int stuffId=stockOperateModel.getStuffId();						//获取ID(由登录者ID决定)
        String operateStockId=stuffId+operateStockTime;					//ID+时间作为操作ID
        int goodsId=goodsModel.getGoodsId();							//获取商品ID(goods对象所带)
        float goodsPrice=goodsMapper.getPriceById(goodsId);				//获取商品价格(由goods对象ID去查)
        int number=goodsModel.getNumber();								//获取操作商品数量(由表单传入goods对象)
        String operateStockState=goodsModel.getOperateStockState();		//获取操作状态(由表单传入goods对象)
        
        stockOperateModel.setOperateStockId(operateStockId);			//给操作ID赋值
        stockOperateModel.setOperateStockTime(operateStockTime); 		//给操作时间赋值
        stockOperateModel.setGoodsId(goodsId); 							//给商品ID赋值
        stockOperateModel.setOperateStockNumber(number);	   			//给入库数量赋值
        stockOperateModel.setOperateStockPrice(goodsPrice*number);		//给操作总价赋值
        stockOperateModel.setOperateStockState(operateStockState); 		//给操作状态赋值
        																//stuffId在上一层Controller已经赋值
        																
		goodsStockMapper.stockOut(goodsModel);							//修改库存表的数量
		operateStockMapper.insertOperateStock(stockOperateModel);		//在操作表新增一条记录					        
	}
	
	/**
	 * 根据商品ID获取库存对象
	 * @param goodsId
	 * @return
	 */
	public GoodsModel GetStockById(Integer goodsId) {
		GoodsModel stock=goodsStockMapper.getStockById(goodsId);
		return stock;
	}
	
	public void SubStockByGoodsId(String[] goodsIds,String[] numArray) {
		for(int i=0;i<goodsIds.length;i++) {			
			goodsStockMapper.subStockByGoodsId(Integer.parseInt(goodsIds[i]),Integer.parseInt(numArray[i]));
		}
	}
}
