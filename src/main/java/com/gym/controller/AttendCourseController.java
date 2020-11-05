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

import com.gym.model.AttendCourseModel;
import com.gym.model.OrderCourseModel;
import com.gym.service.AttendCourseService;

/**
 * 课程上架控制类
 * @author 姬西南2019/10/12 14:05
 */
@RequestMapping("/attendCourse")
@Controller
public class AttendCourseController {

	@Autowired
	AttendCourseService attendCourseService;
    /**
          * 课程预约后台首页
     * @param model
     * @return
     */
	@RequestMapping("/index")
	public String  getAllAttendCourse(Model model) {
		List<AttendCourseModel> attendCourse=attendCourseService.getAllAttendCourse();
		model.addAttribute("attendCourse",attendCourse);
		return "AttendCourseIndex";
	}
	/*
	 * 分页实现拿到前端Ajax传递的数据通过Service层去调用Mapper层查询数据库
	 */
	@RequestMapping("/getBtween")
	@ResponseBody
	public Map<String,Object> getAttendCourseBtween(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<AttendCourseModel> attendCourseA=this.attendCourseService.getAttendCourseBtween(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.attendCourseService.getAttendCourseTotal();//获取数据库中数据总条数
		int total=rowSize%pageSize==0?rowSize/pageSize:rowSize/pageSize+1;
		data.put("total", total);//页数total
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data", attendCourseA);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	/**
	 * 添加上架课程
	 */
	@RequestMapping("/Add")
	public String AddACourse(Model model) {
		return "AttendCourseAdd";
	}
	@RequestMapping("/doInsert")
	@ResponseBody
	public String InsertAttendCourse(AttendCourseModel attendCourseModel,Model model) {
		attendCourseModel.setAttendCourseState("未上架");
		String result=attendCourseService.InsertAttendCourse(attendCourseModel);
		//System.out.println("我被执行了");
		return result;
	}

	/**
	 * 根据对应条件查询相关信息
	 * @param courseId,trainerId,attendTime,model
	 * @return 查询相关的信息
	 */
	@RequestMapping("/updateInfo")
	public String UpdateCourseInfo(int courseId,int trainerId, String attendTime,Model model) {
		AttendCourseModel attendCourse_B=attendCourseService.getAttendCourseInfo(courseId,trainerId,attendTime);
		model.addAttribute("attendCourse_B", attendCourse_B);
		return "AttendCourseUpdate";
	}
	/**
	 * 修改上架课程信息
	 * @param memberModel
	 * @param model
	 */
	@RequestMapping("/Update")
	@ResponseBody
	public String UpdateAttendCourse(AttendCourseModel attendCourseModel) {
		String result=attendCourseService.UpdateAttendCourse(attendCourseModel);
		return result;
	}
	
	/*
	 * 根据ID去删除一条会员信息
	 */
	@RequestMapping("/Delete")
	@ResponseBody
	public boolean DeleteAttendCourse(int attendCourseId,int trainerId,String attendCourseTime,Model model) {
		boolean result=attendCourseService.DeleteAttendCourse(attendCourseId, trainerId, attendCourseTime);
		if(result)
			return true;
		else
			return false;
	}
	/**
	 * 状态位跳到 未上架
	 */
	@RequestMapping("/RenewStateToNo")
	@ResponseBody
	public void RenewStateToNo(Integer courseId,Integer trainerId,String attendCourseTime) {
		attendCourseService.RenewStateToNo(courseId,trainerId,attendCourseTime);
	}
	/**
	 * 状态位跳到 已上架
	 */
	@RequestMapping("/RenewStateToYes")
	@ResponseBody
	public void RenewStateToYes(Integer courseId,Integer trainerId,String attendCourseTime) {
		System.out.println(attendCourseTime);
		System.out.println(courseId);
		attendCourseService.RenewStateToYes(courseId,trainerId,attendCourseTime);
	}
			
	/**查询上架课程的预约情况
	 * 2019/10/25
	 */
	@RequestMapping("/SearchOrder")
	@ResponseBody
	public Map<String,Object> getAttendOrder(Integer cId,Integer tId,String tim,
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		System.out.println(cId);
		System.out.println(tId);
		System.out.println(tim);
		List<OrderCourseModel> OrderA=this.attendCourseService.getAttendOrder(pageStart, pageSize, cId, tId, tim);
		Map<String,Object> data=new HashMap<String,Object>();
		int row=this.attendCourseService.getAttendOrderTotal();//获取数据库中数据总条数
		int total=row%pageSize==0?row/pageSize:row/pageSize+1;
		data.put("total", total);//页数total
		data.put("pageStart", pageStart);
		data.put("row", row);
		data.put("data", OrderA);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
}
