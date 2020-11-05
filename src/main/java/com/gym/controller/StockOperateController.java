package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.StockOperateModel;
import com.gym.service.StockOperateService;

@RequestMapping("/operate")
@Controller
public class StockOperateController {
	
	@Autowired
	StockOperateService stockOperateService;
	
	@RequestMapping("/index")
	public String OperateIndex() {
		return "StockOperateIndex";
	}
	
	//拿到Operate页面的分页数据
	@RequestMapping("/getOperateBtween")
	@ResponseBody
	public Map<String,Object> GetOperateBtween(
			@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
			@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<StockOperateModel> operate=this.stockOperateService.GetOperateBtween(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.stockOperateService.CountOperate();//获取数据库中数据总条数		
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",operate);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	//根据ID删除库存操作记录
	@RequestMapping("/deleteOperateById")
	@ResponseBody
	public void DeleteOperateById(String operateStockId) {
		stockOperateService.DeleteOperateById(operateStockId);
	}
	
	//拿到三个参数分页数据
	@RequestMapping("/queryBy3")
	@ResponseBody
	public Map<String,Object> QueryBy3(String startTime,String endTime,String gOS,
			@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
			@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<StockOperateModel> operate_3=this.stockOperateService.QueryBy3(startTime, endTime, gOS, pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.stockOperateService.CountQuery3(startTime, endTime, gOS);//获取数据库中数据总条数		
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",operate_3);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	//拿到两个参数分页数据
	@RequestMapping("/queryBy2")
	@ResponseBody
	public Map<String,Object> QueryBy2(String startTime,String endTime,String gOS,
			@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
			@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<StockOperateModel> operate_2=this.stockOperateService.QueryBy2(startTime, endTime, pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.stockOperateService.CountQuery2(startTime, endTime);//获取数据库中数据总条数		
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",operate_2);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	//拿到一个参数分页数据
	@RequestMapping("/queryBy1")
	@ResponseBody
	public Map<String,Object> QueryBy1(String startTime,String endTime,String gOS,
			@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
			@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<StockOperateModel> operate_1=this.stockOperateService.QueryBy1(gOS, pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.stockOperateService.CountQuery1(gOS);//获取数据库中数据总条数		
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",operate_1);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
}
