package com.gym.controller;

import java.util.Map;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.GoodsModel;
import com.gym.service.ShopService;

@RequestMapping("/shop")
@Controller
public class ShopController {
	
	@Autowired
	ShopService shopService;
	
	//进入主页的路径
	@RequestMapping("/index")
	public String ShopIndex() {
		return "ShopIndex";
	}
	
	//查询渲染表格的数据
	@RequestMapping("/selectShopBtween")
	@ResponseBody
	public Map<String,Object> SelectAllShop(String goodsType,Integer pageStart,Integer pageSize){
		System.out.println(goodsType);
		Map<String,Object> data=shopService.SelectAllShop(goodsType,pageStart, pageSize);
		return data;
	}
	
	//拿到购买页面的数据
	@RequestMapping("/selectGoodsInfoById")
	public String SelectGoodsInfoById(Integer id,Model model) {
//		HttpSession session=request.getSession();
//		int userId=(int)session.getAttribute("id");
		//获取用户数据（合并时需要进行）
		GoodsModel data=shopService.selectGoodsInfoById(id);
		model.addAttribute("buyModel", data);
		return "ShopBuy";
	}
	
	//根据商品类型去查所属商品信息
	@RequestMapping("/getGoodsByType")
	@ResponseBody
	public Map<String,Object> GetGoodsByType(String goodsType,Integer pageStart,Integer pageSize){
		Map<String,Object> data=shopService.GetGoodsByType(goodsType,pageStart,pageSize);
		return data;
	}
	
	//进入购物车
	@RequestMapping("/toCart")
	public String ToCart() {
		return "CartIndex";
	}
}
