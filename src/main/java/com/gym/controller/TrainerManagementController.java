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

import com.gym.model.TrainerManagementModel;
import com.gym.service.TrainerManagementService;

@Controller
public class TrainerManagementController {
	@Autowired
	TrainerManagementService trainerManagementService;
	
	@RequestMapping("/trainer_management")
	public String TrainerManagement() {			//返回教练管理界面
		return "TrainerManagement";
	}
	
	@RequestMapping("/trainer_management/get_trainer")
	@ResponseBody
	public Map<String,Object> getTrainerManagementByNull(int page,int limit) {		//获取所有教练信息
		List<TrainerManagementModel> list=trainerManagementService.getTrainerManagementByNull((page-1)*limit,limit);
		for(TrainerManagementModel trainer:list) {
			trainer.setTrainerImage("<img src=\""+trainer.getTrainerImage()+"\" class=\"layui-nav-img\">");
		}
		int count=trainerManagementService.count();
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",list);
        return data;
	}
	
	@RequestMapping("/trainer_management/insert_trainer")
	public String insertTrainerManagement() {		//返回教练添加界面
		return "TrainerAdd";
	}
	
	@RequestMapping("/trainer_management/insert_trainer_do")
	@ResponseBody
	public void insertTrainerManagement(@RequestParam("image") MultipartFile image,TrainerManagementModel trainer) {		//添加新的教练信息
		if(!image.isEmpty()) {			//判断是否上传文件
			//获取当前时间
			Date currentDate = new Date();
	        SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String fDate = nowTime.format(currentDate);
	        
	        String fileName = trainer.getTrainerName()+fDate+".png";			//文件名称
	        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
	        System.out.println(filePath + fileName);
	        File dest = new File(filePath + fileName);
	        try {
	        	image.transferTo(dest);
	        } catch (IOException e) {
	        	 e.printStackTrace();
	        }
	        String trainerImage="http:\\\\localhost:8080\\images\\"+fileName;			//"<img src=\""+"http:\\\\localhost:8080\\images\\"+fileName+"\" class=\"layui-nav-img\">";
//	        System.out.println(stuffImage);
	        trainer.setTrainerImage(trainerImage);
		}
		trainerManagementService.insertTrainerManagement(trainer);	//<img src='http:\\localhost:8080\images\stuff_images\2019-10-11_16-36-00.png'  class="layui-nav-img">
	}
	
	@RequestMapping("/trainer_management/update_trainer")
	public String updateTrainerManagement(int trainerId,Model model) {		//返回教练修改界面
		TrainerManagementModel trainer=trainerManagementService.getTrainerManagementByTrainerId(trainerId);
		trainer.setTrainerImage("<img src=\""+trainer.getTrainerImage()+"\" class=\"layui-nav-img\">");
		model.addAttribute("trainer",trainer);
		return "TrainerUpdate";
	}
	
	@RequestMapping("/trainer_management/update_trainer_do")
	@ResponseBody
	public void updateTrainerManagement(@RequestParam("image") MultipartFile image,TrainerManagementModel trainer) {		//修改教练信息
		if(!image.isEmpty()) {			//判断是否上传文件
			//获取当前时间
			Date currentDate = new Date();
	        SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String fDate = nowTime.format(currentDate);
	        
	        String fileName = trainer.getTrainerName()+fDate+".png";			//文件名称
	        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
	        System.out.println(filePath + fileName);
	        File dest = new File(filePath + fileName);
	        try {
	        	image.transferTo(dest);
	        } catch (IOException e) {
	        	 e.printStackTrace();
	        }
	        String trainerImage="http:\\\\localhost:8080\\images\\"+fileName;		//"<img src=\""+"http:\\\\localhost:8080\\images\\"+fileName+"\" class=\"layui-nav-img\">";
//	        System.out.println(stuffImage);
	        trainer.setTrainerImage(trainerImage);
		} else {
			trainer.setTrainerImage(trainerManagementService.getTrainerManagementByTrainerId(trainer.getTrainerId()).getTrainerImage());
		}
		trainerManagementService.updateTrainerManagement(trainer);	//<img src='http:\\localhost:8080\images\stuff_images\2019-10-11_16-36-00.png'  class="layui-nav-img">
	}
	
	@RequestMapping("/trainer_management/delete_trainer")
	public String deleteTrainerManagement(int trainerId) {		//删除教练信息
		trainerManagementService.deleteTrainerByTrainerId(trainerId);
		return "TrainerManagement";
	}
	
	@RequestMapping("/trainer_management/trainer_search")
	@ResponseBody
	public Map<String,Object> getTrainerManagementByTrainerIdorTrainerName(String search,int page,int limit) {		//获取指定教练信息
		List<TrainerManagementModel> list=trainerManagementService.getTrainerManagementByTrainerIdorTrainerName(search,(page-1)*limit,limit);
		for(TrainerManagementModel trainer:list) {
			trainer.setTrainerImage("<img src=\""+trainer.getTrainerImage()+"\" class=\"layui-nav-img\">");
		}
		int count=trainerManagementService.countByTrainerIdorTrainerName(search);
		Map<String,Object> data =new HashMap<String,Object>();
        data.put("count",count);
        data.put("data",list);
        return data;
	}
}
