package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.OrderCourseModel;
import com.gym.service.CourseService;
import com.gym.service.MTrainerService;
import com.gym.service.SearchOrderService;

@RequestMapping("/searchOrder")
@Controller
public class SearchOrderController {
	
	@Autowired
	SearchOrderService searchOrderService;
	@Autowired
	MTrainerService mTrainerService;
	@Autowired
	CourseService courseService;
	
	@RequestMapping("/index")
	public String Index() {
		return "SearchOrderIndex";
	}
	
	/**全查分页
	 * 2019/10/25
	 */
	@RequestMapping("/getSearchOrderBtween")
	@ResponseBody
	public Map<String,Object> GetSearchOrderBtween(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){		
		List<OrderCourseModel> orderCourse=this.searchOrderService.GetOrderCourseBtween(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int row=this.searchOrderService.CountOrderCourse();//获取数据库中数据总条数		
		data.put("pageStart", pageStart);
		data.put("row", row);
		data.put("data", orderCourse);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	//条件查询分页
	@RequestMapping("/getSearchOrderBtween_2")
	@ResponseBody
	public Map<String,Object> GetSearchOrderBtween_2(String trainerName,String courseName,String attendCourseTime,
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		int trainerId=mTrainerService.GetIdByName(trainerName);
		int courseId=courseService.GetCourseIdByName(courseName);
		List<OrderCourseModel> orderCourse=this.searchOrderService.GetOrderCourseBtween_2(pageStart, pageSize, trainerId, courseId, attendCourseTime);
		Map<String,Object> data=new HashMap<String,Object>();
		int row=this.searchOrderService.CountOrderCourseById(trainerId, courseId, attendCourseTime);//获取数据库中数据总条数		
		data.put("pageStart", pageStart);
		data.put("row", row);
		data.put("data", orderCourse);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	// 获取渲染教练下拉框的数据
	@RequestMapping("/getTrainerList")
	@ResponseBody
	public List<OrderCourseModel> GetTrainerList(){
		List<OrderCourseModel> trainerList=searchOrderService.GetTrainerList();
		return trainerList;
	}

	//获取渲染课程下拉框的数据	
	@RequestMapping("/getCourseList")
	@ResponseBody
	public List<OrderCourseModel> GetCourseList(){
		List<OrderCourseModel> courseList=searchOrderService.GetCourseList();
		return courseList;
	}	
		
	//获取渲染时间下拉框的数据	
	@RequestMapping("/getTimeList")
	@ResponseBody
	public List<String> GetTimeList(){
		List<String> timeList=searchOrderService.GetTimeList();
		return timeList;
	}

}
