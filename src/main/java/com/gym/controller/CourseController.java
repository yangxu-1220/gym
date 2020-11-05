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

import com.gym.model.CourseModel;
import com.gym.service.CourseService;

/**
 * 课程设置控制类
 * @author jxn2019/09/08
 * 添加分页后改于 2019/10/09 
 * 照片上传与修改  2019/10/19 
 */
@RequestMapping("/course")
@Controller
public class CourseController {

	@Autowired
	CourseService courseService;
	
	@RequestMapping("/GetCourse")
	@ResponseBody
	public List<CourseModel> GetAllCourse(){
		List<CourseModel> course1=courseService.getAllCourse();
		return course1;
	}
	
	/**
	  * 课程管理首页
	 * @param model
	 * @return 首页
	 */
	@RequestMapping("/index")
	public String Courseindex(Model model) {
		List<CourseModel> course=courseService.getAllCourse();
		model.addAttribute("course",course);
		return "Courseindex";
	}
	
	//跳转
	@RequestMapping("/CourseAdd")
	public String Insert(Model model) {
		return "CourseAdd";
	}
	/**
	    * 增加课程
	  * @param model
	  * @return
	  */
	@RequestMapping("/doInsertCourse")
	@ResponseBody
	public String DoInsert(@RequestParam("image") MultipartFile image,CourseModel courseModel) {
		if(!image.isEmpty()) {			//判断是否上传文件
			//获取当前时间
			Date currentDate = new Date();
	        SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String fDate = nowTime.format(currentDate);   
	        String fileName = courseModel.getCourseName()+fDate+".png";			//文件名称
	        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\image\\course\\";
	        System.out.println(filePath + fileName);
	        File dest = new File(filePath + fileName);
	        try {
	        	image.transferTo(dest);
	        } catch (IOException e) {
	        	 e.printStackTrace();
	        }
	        String courseImage="<img src=\""+"http:\\\\localhost:8080\\image\\course\\"+fileName+"\" width=\"180px\" height=\"140px\">";
//	        System.out.println(stuffImage);
	        courseModel.setCourseImage(courseImage);
		}
		boolean result=courseService.InsertCourse(courseModel);
		if(result) {
			return "true";
		}
		else {
			return "false";
		}
	}
	
	 /**
	  *根据Id获得修改的课程信息
	  */
	@RequestMapping("/updateCourse")
	public String Update(int id,Model model) {
		CourseModel courses=courseService.getCourseById(id);
		model.addAttribute("courses",courses);
		return "CourseUpdate";
	}
	/**
	 * 修改课程信息
	 * @param courseModel
	 * @param model
	 */
	@RequestMapping("/doUpdateCourse")
	@ResponseBody
	public String DoUpdate(@RequestParam("image") MultipartFile image,CourseModel courseModel) {
		if(!image.isEmpty()) {  //判断照片是否存在
			Date currentDate=new Date();//获取当前时间
			SimpleDateFormat nowTime=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
			String fDate=nowTime.format(currentDate);
			String fileName = courseModel.getCourseName()+fDate+".png";			//文件名称
		    String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\image\\course\\";
		    System.out.println(filePath + fileName);
		    File dest = new File(filePath + fileName);
		    try {
		        	image.transferTo(dest);
		    } 
		    catch (IOException e) {
		        	 e.printStackTrace();
		    }
		    String courseImage="<img src=\""+"http:\\\\localhost:8080\\image\\course\\"+fileName+"\" width=\"180px\" height=\"140px\">";
		    System.out.println(courseImage);
		    courseModel.setCourseImage(courseImage);
		}
		System.out.println(courseModel.getCourseImage());		
		boolean result=courseService.UpdateCourse(courseModel);
		if(result) {
			return "true";
		}
		else {
			return "false";
		}
}
	
	  /**
	   * 删除课程信息
	   * @param id
	   * @param model
	   */
	@RequestMapping("/doDeleteCourse")
	@ResponseBody
	public boolean doDelete(int id,Model model) {
		boolean result=courseService.DeleteCourseInfo(id);
		if(result) 
			return true;
		else 
			return false;
	}
	
	 /**
	  * 根据课程ID,课程名获取课程信息
	  * @param courseA
	  * @param model
	  * @return
	  */
	@RequestMapping("/getCourseInfo")
	public String getCourseInfo(String courseA,Model model) {
		CourseModel courseS=courseService.getCourseInfo(courseA);
		model.addAttribute("courseS", courseS);
		return "CourseDetail";
	}
	
	/**
	 * 分页实现
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getCourseBtween")
	@ResponseBody
	public Map<String,Object> getCourseBtween(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<CourseModel> course1=this.courseService.getCourseBtween(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.courseService.getCourseTotal();//获取数据库中数据总条数
		int total=rowSize%pageSize==0?rowSize/pageSize:rowSize/pageSize+1;
		data.put("total", total);//页数total
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",course1);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
}
