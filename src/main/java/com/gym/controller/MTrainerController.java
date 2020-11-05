package com.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.MTrainerModel;
import com.gym.service.MTrainerService;

@RequestMapping("/MTrainer")
@Controller
public class MTrainerController {
	
	@Autowired
	MTrainerService mTrainerService;
	
	
	@RequestMapping("/GetAllTrainer")
	@ResponseBody  //将查询到的List<MTrainerModel>转换为Ajax可以接受的json类型
	public List<MTrainerModel> GetAllTrainer(){
		List<MTrainerModel> trainer=mTrainerService.GetAllTrainer();		
		return trainer;
	}
}
