package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.StockOperateMapper;
import com.gym.model.StockOperateModel;

@Service
public class StockOperateService {
	
	@Autowired
	StockOperateMapper stockOperateMapper;
	
	//拿到operate分页数据
	public List<StockOperateModel> GetOperateBtween(Integer pageStart,Integer pageSize){
		List<StockOperateModel> operate=stockOperateMapper.getOperateBtween(pageStart, pageSize);
		return operate;
	}
	
	//统计operate表中的数据数量
	public int CountOperate() {
		int count = stockOperateMapper.countOperate();
		return count;
	}
	
	//根据操作ID删除操作记录
	public void DeleteOperateById(String operateStockId) {
		stockOperateMapper.deleteOperateById(operateStockId);
	}
	
	//三个参数的查询分页
	public List<StockOperateModel> QueryBy3(String startTime,String endTime,String gOS,Integer pageStart,Integer pageSize){
		List<StockOperateModel> stockOperate=stockOperateMapper.queryBy3(startTime, endTime, gOS, pageStart, pageSize);
		return stockOperate;
	}
	
	//三个参数的数据总数
	public int CountQuery3(String startTime,String endTime,String gOS) {
		int count=stockOperateMapper.countQuery3(startTime, endTime, gOS);
		return count;
	}
	
	//一个条件的查询分页
	public List<StockOperateModel> QueryBy1(String gOS,Integer pageStart,Integer pageSize){
		List<StockOperateModel> stockOperate=stockOperateMapper.queryBy1(gOS, pageStart, pageSize);
		return stockOperate;
	}
	
	//一个参数的数据总数
	public int CountQuery1(String gOS) {
		int count=stockOperateMapper.conuntQuery1(gOS);
		return count;
	}
	
	//两个个条件的查询分页
	public List<StockOperateModel> QueryBy2(String startTime,String endTime,Integer pageStart,Integer pageSize){
		List<StockOperateModel> stockOperate=stockOperateMapper.queryBy2(startTime, endTime, pageStart, pageSize);
		return stockOperate;
	}
	
	//两个参数的数据总数
		public int CountQuery2(String startTime,String endTime) {
			int count=stockOperateMapper.countQuery2(startTime, endTime);
			return count;
		}
}
