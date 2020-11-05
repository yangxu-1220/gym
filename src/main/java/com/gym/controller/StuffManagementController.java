package com.gym.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.gym.model.StuffManagementModel;
import com.gym.service.StuffManagementService;

@Controller
public class StuffManagementController {
	@Autowired
	StuffManagementService stuffManagementSerice;
	StuffManagementModel stuffManagementModel;
	
	@RequestMapping("/setting/stuff")
	public String Stuff() {
		return "StuffSetting";
	}
	
	@RequestMapping("/setting/get_stuff")
	@ResponseBody
	public Map<String,Object> GetStuff(int page,int limit) {
		List<StuffManagementModel> list=stuffManagementSerice.getStuff((page-1)*limit,limit);
		for(StuffManagementModel stuff:list) {
			stuff.setStuffImage("<img src=\""+stuff.getStuffImage()+"\" class=\"layui-nav-img\">");
		}
		int count=stuffManagementSerice.count();
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",list);
        return data;
	}
	
	//查询指定员工信息
	@RequestMapping("/setting/stuff_search")
	@ResponseBody
	public Map<String,Object> StuffSearch(String search,int page,int limit) {
		List<StuffManagementModel> list=stuffManagementSerice.getStuffByStuffIdorStuffName(search,(page-1)*limit,limit);
		for(StuffManagementModel stuff:list) {
			stuff.setStuffImage("<img src=\""+stuff.getStuffImage()+"\" class=\"layui-nav-img\">");
		}
		int count=stuffManagementSerice.count_2(search);
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",list);
        return data;
	}
	
	@ResponseBody
	@RequestMapping("/setting/get_stuff_type")
	public List<StuffManagementModel> GetStuffType() {
		List<StuffManagementModel> stuffList=stuffManagementSerice.getStuffType();	
		return stuffList;
	}
	
	@RequestMapping("/setting/stuff_add")
	public String StuffAdd() {
		return "StuffAdd";
	}
	
	@RequestMapping("/setting/stuff_update")
	public String StuffUpdate(int stuffId,Model model) {
//		System.out.println(status);
		StuffManagementModel stuff=stuffManagementSerice.getUpdateStuffByStuffId(stuffId);
//		System.out.println("测试数据："+stuff);
//		System.out.println("输出为："+stuff.getStuffImage());
		stuff.setStuffImage("<img src=\""+stuff.getStuffImage()+"\" class=\"layui-nav-img\">");
		model.addAttribute("stuff",stuff);
		return "StuffUpdate";
	}
	
	@RequestMapping("/setting/stuff_delete")
	public String StuffDelete(String stuffId) {
		//System.out.println(status);
		stuffManagementSerice.getDeleteStuff(stuffId);
		return "StuffSetting";
	}
	
	@RequestMapping("/setting/stuff_add_do")
	public String StuffAddDo(@RequestParam("image") MultipartFile image,StuffManagementModel stuff) {
		if(!image.isEmpty()) {			//判断是否上传文件
			//获取当前时间
			Date currentDate = new Date();
	        SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String fDate = nowTime.format(currentDate);
	        
	        String fileName = stuff.getStuffName()+fDate+".png";			//文件名称
	        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
	        System.out.println(filePath + fileName);
	        File dest = new File(filePath + fileName);
	        try {
	        	image.transferTo(dest);
	        } catch (IOException e) {
	        	 e.printStackTrace();
	        }
	        String stuffImage="http:\\\\localhost:8080\\images\\"+fileName;			//"<img src=\""+"http:\\\\localhost:8080\\images\\"+fileName+"\" class=\"layui-nav-img\">";
//	        System.out.println(stuffImage);
	        stuff.setStuffImage(stuffImage);
		}
		stuffManagementSerice.getInsertStuff(stuff);//<img src='http:\\localhost:8080\images\stuff_images\2019-10-11_16-36-00.png'  class="layui-nav-img">
		return "redirect:/setting/stuff_add";
	}
	
	@RequestMapping("/setting/stuff_update_do")
	@ResponseBody
	public void StuffUpdateDo(@RequestParam("image") MultipartFile image,StuffManagementModel stuff) {		
		if(!image.isEmpty()) {			//判断是否上传文件
			//获取当前时间
			Date currentDate = new Date();
	        SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String fDate = nowTime.format(currentDate);
	        
	        String fileName = stuff.getStuffName()+fDate+".png";			//文件名称
	        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
	        System.out.println(filePath + fileName);
	        File dest = new File(filePath + fileName);
	        try {
	        	image.transferTo(dest);
	        } catch (IOException e) {
	        	 e.printStackTrace();
	        }
	        String stuffImage="http:\\\\localhost:8080\\images\\"+fileName;		//"<img src=\""+"http:\\\\localhost:8080\\images\\"+fileName+"\" class=\"layui-nav-img\">";
//	        System.out.println(stuffImage);
	        stuff.setStuffImage(stuffImage);
		}else {
			stuff.setStuffImage(stuffManagementSerice.getUpdateStuffByStuffId(stuff.getStuffId()).getStuffImage());
		}
		System.out.println("stuffId:"+stuff.getStuffId());
		stuffManagementSerice.getUpdateStuff(stuff);	//<img src='http:\\localhost:8080\images\stuff_images\2019-10-11_16-36-00.png'  class="layui-nav-img">
	}
}
