package com.gym.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.VisitModel;
import com.gym.service.VisitService;

@RequestMapping("/visit")
@Controller
public class VisitController {

	@Autowired
	VisitService visitService;
	
	/**
	 * 会员来访首页
	 * @return
	 */
	@RequestMapping("/index")
	public String VisitIndex() {
		return "Visit";
	}
	/**
	 * 根据来访会员ID获取这个会员的一些信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/visitById")
	@ResponseBody
	public Map<String,Object> VisitById(Integer id) {
		System.out.println(id);
		Map<String,Object> data=new HashMap<String,Object>();
		VisitModel visit=visitService.VisitById(id);
		data.put("data", visit);
		return data;
	}
	/**
	 * 向会员来访表中添加一条来访记录
	 * @param visitModel
	 */
	@RequestMapping("/onceVisit")
	@ResponseBody
	public void OnceVisit(Integer id) {
		visitService.OnceVisit(id);;
	}
	/**
	 * 统计该会员的来访次数
	 * @param id
	 * @return
	 */
	@RequestMapping("/countVisit")
	@ResponseBody
	public int CountVisit(Integer id) {
		int count=visitService.countVisit(id);
		return count;
	}
}
