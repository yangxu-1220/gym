package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.GoodsModel;
import com.gym.model.StockOperateModel;
import com.gym.service.GoodsStockService;

@RequestMapping("/stock")
@Controller
public class GoodsStockController {
	
	@Autowired
	GoodsStockService goodsStockService;
	
	@RequestMapping("/index")
	public String StockIndex() {
		return "GoodsStockIndex";
	}
	
	/**
	 * 库存页面的拿取分页数据
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getStockBtween")
	@ResponseBody	
	public Map<String,Object> getGoodsBtween(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<GoodsModel> goodsStock=this.goodsStockService.GetStockBtween(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.goodsStockService.CountStock();//获取数据库中数据总条数		
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",goodsStock);//List对象存为data
		data.put("limit", pageSize);	
		return data;		
	}
	
	/**
	 * 商品入库
	 * @param goodsModel
	 */
	@RequestMapping("/stockIn")
	@ResponseBody
	public void StockIn(GoodsModel goodsModel,HttpServletRequest request) {
		HttpSession session=request.getSession();
		int stuffId=(int)session.getAttribute("id");					//获取到员工ID
		StockOperateModel stockOperateModel=new StockOperateModel();	//创建一个库存操作对象
		stockOperateModel.setStuffId(stuffId);							//给stuffID赋初值传到Service层		
		goodsStockService.StockIn(goodsModel,stockOperateModel);
	}
	
	/**
	 * 商品出库
	 * @param goodsModel
	 */
	@RequestMapping("/stockOut")
	@ResponseBody
	public void StockOut(GoodsModel goodsModel,HttpServletRequest request) {
		HttpSession session=request.getSession();
		int stuffId=(int)session.getAttribute("id");					//获取到员工ID
		StockOperateModel stockOperateModel=new StockOperateModel();	//创建一个库存操作对象
		stockOperateModel.setStuffId(stuffId);							//给stuffID赋初值传到Service层	
		goodsStockService.StockOut(goodsModel,stockOperateModel);
	}
	
	/**
	 * 根据ID查库存，跳转到商品入库页面
	 * @param goodsId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getStockByIdForIn")
	public String GetStockByIdForIn(Integer goodsId,Model model) {
		GoodsModel stock=goodsStockService.GetStockById(goodsId);
		model.addAttribute("stockIn", stock);
		return "GoodsStockIn";
	}
	
	/**
	 * 根据ID查库存，跳转到商品出库页面
	 * @param goodsId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getStockByIdForOut")
	public String GetStockByIdForOut(Integer goodsId,Model model) {
		GoodsModel stock=goodsStockService.GetStockById(goodsId);
		model.addAttribute("stockOut", stock);
		return "GoodsStockOut";
	}	
}
