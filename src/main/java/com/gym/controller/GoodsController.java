package com.gym.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;

import com.gym.model.GoodsModel;
import com.gym.service.GoodsService;

@RequestMapping("/goods")
@Controller
public class GoodsController {
	
	@Autowired
	GoodsService goodsService;
	
	@RequestMapping("/index")
	public String GoodsIndex(HttpServletRequest request) {
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("id");
		System.out.println("这里是统计报表页面，获取id的数据为："+id);
		return "GoodsIndex";
	}
	
	@RequestMapping("/toAdd")
	public String ToAdd() {
		return "GoodsAdd";
	}
				
	//拿到前端Ajax传递的数据通过Service层去调用Mapper层查询数据库
	@RequestMapping("/getGoodsBtween")
	@ResponseBody
	public Map<String,Object> getGoodsBtween(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<GoodsModel> goodsModel_A=this.goodsService.getGoodsBtween(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.goodsService.getGoodsTotal();//获取数据库中数据总条数
		int total=rowSize%pageSize==0?rowSize/pageSize:rowSize/pageSize+1;
		data.put("total", total);//页数total
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",goodsModel_A);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	
	@RequestMapping("/deleteGoodsById")
	@ResponseBody
	public String deleteGoodsById(Integer id,Model model) {
		boolean result = goodsService.deleteGoodsById(id);
		if(result) {
			model.addAttribute("msg", "删除成功");
			return "true";
		}
		else {
			model.addAttribute("msg", "删除失败");
			return "false";
		}
	}
	
	//get根据ID查询的DemoModel对象
	@RequestMapping("/selectById")
	//@ResponseBody
	public String selectGoodsById(Integer id,Model model) {
		GoodsModel AGoods = goodsService.selectGoodsById(id);
		model.addAttribute("goods_C", AGoods);
		//return demoModel;
		return "GoodsUpdate";
	}
	
	@RequestMapping("/doUpdate")
	@ResponseBody
	public String UpdateGoods(@RequestParam("image") MultipartFile image,GoodsModel goods) {
		if(!image.isEmpty()) {			//判断是否上传文件
			//获取当前时间
			Date currentDate = new Date();
	        SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String fDate = nowTime.format(currentDate);
	        
	        String fileName = goods.getGoodsName()+fDate+".png";			//文件名称
	        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\image\\goods\\";
	        System.out.println(filePath + fileName);
	        File dest = new File(filePath + fileName);
	        try {
	        	image.transferTo(dest);
	        } catch (IOException e) {
	        	 e.printStackTrace();
	        }
	        String goodsImage="<img src=\""+"http:\\\\localhost:8080\\image\\goods\\"+fileName+"\" width=\"398px\" height=\"398px\">";
	        System.out.println(goodsImage);
	        goods.setGoodsImage(goodsImage);
		}
		//System.out.println(goods.getGoodsImage());
		goodsService.updateGoods(goods);//<img src='http:\\localhost:8080\images\stuff_images\2019-10-11_16-36-00.png'  class="layui-nav-img">
		return "redirect:/goods/selectById";
	}
	
	@RequestMapping("/doInsert")
	@ResponseBody
	public String InsertGoods(@RequestParam("image") MultipartFile image,GoodsModel goods) {
		if(!image.isEmpty()) {			//判断是否上传文件
			//获取当前时间
			Date currentDate = new Date();
	        SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String fDate = nowTime.format(currentDate);
	        
	        String fileName = goods.getGoodsName()+fDate+".png";			//文件名称
	        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\image\\goods\\";
	        System.out.println(filePath + fileName);
	        File dest = new File(filePath + fileName);
	        try {
	        	image.transferTo(dest);
	        } catch (IOException e) {
	        	 e.printStackTrace();
	        }
	        String goodsImage="<img src=\""+"http:\\\\localhost:8080\\image\\goods\\"+fileName+"\" width=\"398px\" height=\"398px\">";
//	        System.out.println(stuffImage);
	        goods.setGoodsImage(goodsImage);
		}
		boolean result=goodsService.insertGoods(goods);//<img src='http:\\localhost:8080\images\stuff_images\2019-10-11_16-36-00.png'  class="layui-nav-img">
		if(result) {
			return "true";
		}
		else {
			return "false";
		}
	}
	
	@RequestMapping("/detail")
	public String Detail(Integer id,Model model) {
		GoodsModel BGoods = goodsService.selectGoodsById(id);
		model.addAttribute("goods_D", BGoods);
		//return demoModel;
		return "GoodsDetail";
	}
	//上架商品
	@RequestMapping("/setStateToYes")
	@ResponseBody
	public void SetStateToYes(Integer goodsId) {
		goodsService.SetStateToYes(goodsId);
	}
	//下架商品
	@RequestMapping("/setStateToNo")
	@ResponseBody
	public void SetStateToNo(Integer goodsId) {
		System.out.println(goodsId);
		goodsService.SetStateToNo(goodsId);
	}
}
