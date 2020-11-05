package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.AttendCourseMapper;
import com.gym.mapper.CourseMapper;
import com.gym.model.AttendCourseModel;
import com.gym.model.CourseModel;
import com.gym.model.OrderCourseModel;

/**
 * 课程上架业务逻辑层
 * @author 姬西南2019/10/11/  16:30
 * 添加查看约课信息 2019/10/25/
 */
@Service
public class AttendCourseService {
 
	@Autowired
	AttendCourseMapper attendCourseMapper;
	@Autowired
	CourseMapper courseMapper;
	
	/**
	  * 查询所有需要上架课程
	 * @return
	 */
	public List<AttendCourseModel> getAllAttendCourse(){
		return attendCourseMapper.getAllAttendCourse();
	}
	/**
	 * 根据三个主键去查询对应的唯一一条数据
	 * @param courseId,trainerId,attendTime
	 * @return attendCourse
	 */
	public AttendCourseModel getAttendCourseInfo(int courseId,int trainerId, String attendTime) {
		AttendCourseModel attendCourse=attendCourseMapper.getAttendCourseInfo(courseId, trainerId, attendTime);
		return attendCourse;
	}
	/**
	 * 动态渲染table和分页
	 */
	public List<AttendCourseModel> getAttendCourseBtween(int pageStart,int pageSize){
		List<AttendCourseModel> attendCourse_A=attendCourseMapper.getAttendCourseBtween(pageStart, pageSize);
		return attendCourse_A;		
	}
	
	/**
	 * 获取表中数据的总数
	 */
	public int getAttendCourseTotal() {
		int rowSize=attendCourseMapper.getAttendCourseTotal();
		return rowSize;
	}
	
	/**
   	  * 新增上架课程
   	 */
    public String InsertAttendCourse(AttendCourseModel attendCourseModel) {
    	//用attendCourseId去查这堂课的容纳人数
    	CourseModel course=courseMapper.getCourseByName(attendCourseModel.getCourseName());
    	int courseNumber=course.getCourseNumber();
    	//把courseNumber作为attendStock插入
    	attendCourseModel.setAttendStock(courseNumber);
    	//用教练姓名去查教练ID
		int trainerId=attendCourseMapper.getTrainerByTrainerName(attendCourseModel.getTrainerName());
		//用课程名称去查课程ID
		int courseId=attendCourseMapper.getCourseIdByCourseName(attendCourseModel.getCourseName());
		//给要插入数据库的attendCourseModel.courseId赋值
		attendCourseModel.setAttendCourseId(courseId);
		//给要插入数据库的attendCourseModel.trainerId赋值
		attendCourseModel.setTrainerId(trainerId);
    	//根据课程id，教练id和上课时间去查数据库中有没有这条数据
    	AttendCourseModel result=attendCourseMapper.checkAttendCourse(attendCourseModel);
    	if (result!=null){//要插入的这条数据不存在    		
    		//System.out.println("我不为空哈哈哈哈");
    		return "教练和时间冲突";
    	}
    	else {//执行Insert语句
    		AttendCourseModel result_2=attendCourseMapper.checkAttendCourse_1_2(attendCourseModel);
    		if(result_2!=null) {//上课时间和上课地点冲突
    			return "时间和地点冲突";
    		}
    		else {
    			attendCourseMapper.InsertAttendCourse(attendCourseModel);
        		//System.out.println("上面不为空我就执行了");
        		return "true";
    		}
    	}   	
	}
    
    /**
   	  * 修改
   	 */
	public String UpdateAttendCourse(AttendCourseModel attendCourseModel) {
		AttendCourseModel result=attendCourseMapper.checkAttendCourse_2(attendCourseModel);
    	if (result!=null){//教练和上课时间冲突		
    		System.out.println("我不为空哈哈哈哈");
    		return "教练和时间冲突";
    	}
    	else {//执行Insert语句
    		AttendCourseModel result_2=attendCourseMapper.checkAttendCourse_2_2(attendCourseModel);
    		if(result_2!=null) {//上课时间和上课地点冲突
    			return "时间和地点冲突";
    		}
    		else {
    			int count=attendCourseMapper.UpdateAttendCourse(attendCourseModel);
        		System.out.println("上面不为空我就执行了");
        		return "true";
    		}
    	}   
	}
	
    /**
   	  * 删除
   	 */
	public boolean DeleteAttendCourse(int attendCourseId,int trainerId,String attendCourseTime) {
		int count=attendCourseMapper.DeleteAttendCourse(attendCourseId, trainerId, attendCourseTime);
		if(count>0)
			return true;
		return false;
	} 
	
	/**
	 * 状态位跳到 未上架
	 */
	public void RenewStateToNo(int courseId,int trainerId,String attendCourseTime) {
		attendCourseMapper.renewStateToNo(courseId,trainerId,attendCourseTime);
	}
	
	/**
	 * 状态位跳到 已上架
	 */
	public void RenewStateToYes(int courseId,int trainerId,String attendCourseTime) {
		attendCourseMapper.renewStateToYes(courseId,trainerId,attendCourseTime);
	}
	
	/**
	 * 查看课程预约情况
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public List<OrderCourseModel> getAttendOrder(int pageStart,int pageSize,int cId,int tId,String tim){
		List<OrderCourseModel> order_A=attendCourseMapper.getAttendOrder(cId, tId, tim, pageStart, pageSize);
		return order_A;		
	}
	/**
	 * 根据条件显示约课条数
	 * @return
	 */
	public int getAttendOrderTotal() {
		int row=attendCourseMapper.getAttendOrderTotal();
		return row;
	}
}
