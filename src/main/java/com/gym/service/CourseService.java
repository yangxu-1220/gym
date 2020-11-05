package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.CourseMapper;
import com.gym.model.CourseModel;

/**
 * 课程设置业务逻辑层
 * @author jxn 2019/09/09 更改于2019年10月6日
 * 
 */
@Service
public class CourseService {

	@Autowired
	CourseMapper courseMapper;
	/**
	 * 查询所有的课程
	 * @return
	 */
	public List<CourseModel> getAllCourse(){
	 	return courseMapper.getAllCourse();
	}
	
	 /**
	  * 将根据id查询到课程存为courses
	  * @param courseId
	  * @return
	  */
    public CourseModel getCourseById(int courseId) {
    	CourseModel courses=courseMapper.getCourseById(courseId);
		return courses;		
	}		
    
    /**
     * 根据课程的名称查ID
     * @param courseName
     * @return
     */
    public int GetCourseIdByName(String courseName) {
    	int courseId=courseMapper.getCourseIdByName(courseName);
    	return courseId;
    }

   	 /**
   	  * 新增课程
   	  * @param courseModel
   	  * @return
   	  */
    public boolean InsertCourse(CourseModel courseModel) {
		boolean count=courseMapper.InsertCourse(courseModel);
		if(count)
			return true;
		else
			return false;
	}
    
   	  /**
   	   * 修改课程
   	   * @param courseModel
   	   * @return
   	   */
	public boolean UpdateCourse(CourseModel courseModel) {
		CourseModel courses_1=courseMapper.getCourseById(courseModel.getCourseId());
	    if(courseModel.getCourseImage()==null)
	    	courseModel.setCourseImage(courses_1.getCourseImage());
	    boolean result=courseMapper.UpdateCourse(courseModel);
	    if(result)
	    	return true;
	    else
	    	return false;
	}
	
   	  /**
   	   * 删除会员卡
   	   * @param courseId
   	   * @return6
   	   */
	public boolean DeleteCourseInfo(int courseId) {
		int count=courseMapper.DeleteCourseInfo(courseId);
		if(count>0)
			return true;
		return false;
	} 
	
	/**
	   * 动态渲染table和分页 2019年10月6日加
	   * @param pageStart
	   * @param pageSize
	   * @return
	   */
	public List<CourseModel> getCourseBtween(int pageStart,int pageSize){
		List<CourseModel> course1=courseMapper.getCourseBtween(pageStart, pageSize);
		return course1;		
	}
	 /**
	  * 获取card表中数据的总数 2019年10月6日加
	  * @return
	  */
	public int getCourseTotal() {
		int rowSize=courseMapper.getCourseTotal();
		return rowSize;
	}
	/**
	  * 将课程id,课程名称查询课程信息，将信息存为card对象 2019年10月7日加
	 */
    public CourseModel getCourseInfo(String courseA) {
 	CourseModel courseS=courseMapper.getCourseInfo(courseA);
		return courseS;		
	}
}
