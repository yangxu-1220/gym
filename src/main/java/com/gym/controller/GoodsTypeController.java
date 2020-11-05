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

import com.gym.model.GoodsTypeModel;
import com.gym.service.GoodsTypeService;

@RequestMapping("/goodsType")
@Controller
public class GoodsTypeController {
	
	@Autowired
	GoodsTypeService goodsTypeService;
	
	@RequestMapping("/toInsert")
	public String ToInsert() {
		return "GoodsTypeAdd";
	}
	
	//查询所有的商品类型信息记录封装到goodsType中添加到goodsType_A给前端页面使用，
	//跳转目标是商品类型首页GoodsTypeIndex.html
	@RequestMapping("/index")	
	public String GoodsTypeIndex(Model model) {
		return "GoodsTypeIndex";
	}
		//查询全部的商品类型作为List注入List对象	
		@RequestMapping("/getTypeList")
		@ResponseBody
		public List<GoodsTypeModel> GetTypeList(Model model) {
			List<GoodsTypeModel> goodsTypeList=goodsTypeService.GetAllGoodsType();
			return goodsTypeList;							
		}						
		//根据所选记录的ID查询数据库把查询到的数据存进
		//goodsType加入goodsType_B然后跳转到修改页面去完成修改
		@RequestMapping("/selectTypeById")
		public String SelectGoodsTypeById(Model model,Integer id) {
			GoodsTypeModel goodsType=goodsTypeService.getGoodsTypeById(id);
			model.addAttribute("goodsType_B", goodsType);
			return "GoodsTypeUpdate";
		}
		//添加一条商品类型
		@RequestMapping("/doInsert")
		@ResponseBody
		public String InsertGoodsType(GoodsTypeModel goodsTypeModel,Model model) {
			boolean result=goodsTypeService.InsertGoodsType(goodsTypeModel);
			//Map<String,Object> data=new HashMap<String,Object>();
			if(result)
				//data.put("result", "ture");
				return "true";
			else
				//data.put("result", "false");
			return "false";
		}
		//修改商品类型
		@RequestMapping("/update")
		@ResponseBody
		public boolean UpdateGoodsType(GoodsTypeModel goodsTypeModel,Model model) {
			boolean result=goodsTypeService.UpdateGoodsType(goodsTypeModel);
			if(result)
				return true;
			else
				return false;
		}
		//根据选择的id删除商品类型
		@RequestMapping("/deleteTypeById")
		@ResponseBody
		public boolean DeleteGoodsType(Integer id) {
			boolean result=goodsTypeService.DeleteGoodsTypeById(id);
			if(result)
				return true;
			else
				return false;
		}
		//拿到前端Ajax传递的数据通过Service层去调用Mapper层查询数据库
		@RequestMapping("/getGoodsTypeBtween")
		@ResponseBody
		public Map<String,Object> getGoodsTypeBtween(
		@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
		@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
			List<GoodsTypeModel> goodsType_A=this.goodsTypeService.GetGoodsTypeBtween(pageStart, pageSize);
			Map<String,Object> data=new HashMap<String,Object>();
			int rowSize=this.goodsTypeService.GetTypeTotal();//获取数据库中数据总条数
			data.put("pageStart", pageStart);
			data.put("rowSize", rowSize);
			data.put("data",goodsType_A);//List对象存为data
			data.put("limit", pageSize);	
			return data;
		}								
}
