package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.OrderDetailModel;
import com.gym.model.OrderModel;
import com.gym.service.GoodsSaleService;

@RequestMapping("/goodsSale")
@Controller
public class GoodsSaleController {
	
	@Autowired
	GoodsSaleService goodsSaleService;
	
	@RequestMapping("/index")
	public String GoodsSaleIndex() {
		return "GoodsSaleIndex";
	}
	
	//获取goodsSale的分页数据
	@RequestMapping("/getGoodsSaleBtween")
	@ResponseBody
	public Map<String,Object> GetGoodsSaleBtween(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<OrderModel> goodsSale=this.goodsSaleService.GetGoodsSalaBtween(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.goodsSaleService.CountGoodsSale();//获取数据库中数据总条数		
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",goodsSale);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	//根据ID删除order
	@RequestMapping("/deleteOrderById")
	@ResponseBody
	public void DeleteOrderById(String orderId) {
		goodsSaleService.DeleteOrderById(orderId);
	}
	
	//根据ID查询对应的商品详情对象集
	@RequestMapping("/getDetailById")	
	public String GetDetailById(String orderId,Model model){
		List<OrderDetailModel> orderDetail=goodsSaleService.GetDetailById(orderId);
		model.addAttribute("details", orderDetail);
		return "GoodsSaleSearchDetail";
	}	
	
	//获取三个条件查询的分页数据
	@RequestMapping("/queryBy3")
	@ResponseBody
	public Map<String,Object> QueryBy3(String startTime,String endTime,String idOrName,
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<OrderModel> order_3=this.goodsSaleService.QueryBy3(startTime, endTime, idOrName, pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.goodsSaleService.CountQuery3(startTime, endTime, idOrName);//获取数据库中数据总条数		
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",order_3);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	//获取两个条件查询的分页数据
	@RequestMapping("/queryBy2")
	@ResponseBody
	public Map<String,Object> QueryBy2(String startTime,String endTime,String idOrName,
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<OrderModel> order_2=this.goodsSaleService.QueryBy2(startTime,endTime,pageStart,pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.goodsSaleService.CountQuery2(startTime,endTime);//获取数据库中数据总条数		
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",order_2);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	//获取一个条件查询的分页数据
		@RequestMapping("/queryBy1")
		@ResponseBody
		public Map<String,Object> QueryBy1(String startTime,String endTime,String idOrName,
		@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
		@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
			List<OrderModel> order_1=this.goodsSaleService.QueryBy1(idOrName,pageStart,pageSize);
			Map<String,Object> data=new HashMap<String,Object>();
			int rowSize=this.goodsSaleService.CountQuery1(idOrName);//获取数据库中数据总条数		
			data.put("pageStart", pageStart);
			data.put("rowSize", rowSize);
			data.put("data",order_1);//List对象存为data
			data.put("limit", pageSize);	
			return data;
		}
}
