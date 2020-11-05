package com.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.MStuffModel;
import com.gym.service.MStuffService;

@RequestMapping("/MStuff")
@Controller
public class MStuffController {

	@Autowired
	MStuffService mStuffService;
	
	@RequestMapping("/GetAllStuff")
	@ResponseBody  //将查询到的List<MStaffModel>转换为Ajax可以接受的json类型
	public List<MStuffModel> GetAllStuff(){
		List<MStuffModel> stuff=mStuffService.GetAllStuff();		
		return stuff;
	}
}
