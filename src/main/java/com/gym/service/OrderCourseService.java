package com.gym.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.OrderCourseMapper;
import com.gym.model.AttendCourseModel;
import com.gym.model.OrderCourseModel;

@Service
public class OrderCourseService {

	@Autowired
	OrderCourseMapper orderCourseMapper;
	
	/**
	 * 分页，已上架课程，需要信息
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	public List<OrderCourseModel> GetCourseByState(Integer pageStart,Integer pageSize){
		List<OrderCourseModel> orderCourse=orderCourseMapper.getCourseByState(pageStart, pageSize);
		return orderCourse;
	}
	
	/**
	  * 获得状态为已上架的课程总数
	 * @return
	 */
	public int GetCourseTotalOfState() {
		int total=orderCourseMapper.getCourseTotalOfState();
		return total;
	}
	
	/**
	 * 根据三个主键唯一确定一条课程记录
	 */
	public AttendCourseModel SelectAttendByPrimary(Integer courseId,Integer trainerId,String attendCourseTime) {
		AttendCourseModel result=orderCourseMapper.selectAttendByPrimary(courseId,trainerId,attendCourseTime);
		return result;
	}
	  /**
	   * 查询约课表主键对应的所有记录
	   * @param orderbefo
	   * @return
	   */
	 public OrderCourseModel getOrderCourseInfo (OrderCourseModel orderbefo) {
		  OrderCourseModel beorder=orderCourseMapper.getOrderCourseInfo(orderbefo);
		  return beorder;
	  }
	 /**
  	  * 在约课表插入一条记录
  	  */
   public String InsertOrderCourse(OrderCourseModel orderCourseModel) {
	   //获取当前时间
	   Date currentDate = new Date();
       SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
       String fDate = nowTime.format(currentDate);
	   //根据课程名查课程id
	   int courseId=orderCourseMapper.getCourseIdByCourseName(orderCourseModel.getCourseName());
	   //根据教练名查教练id
	   int trainerId=orderCourseMapper.getTrainerByTrainerName(orderCourseModel.getTrainerName());
	   //根据约课操作的会员名查会员id
	   int memberId=orderCourseMapper.getMemberByMemberName(orderCourseModel.getMemberName());
	   String attendCourseTime=orderCourseModel.getAttendCourseTime();
	   //给要插入的字段赋值
	   orderCourseModel.setCourseId(courseId);
	   orderCourseModel.setTrainerId(trainerId);
	   orderCourseModel.setMemberId(memberId);
	   orderCourseModel.setOrderTime(fDate);
	   OrderCourseModel result=orderCourseMapper.checkOrderCourse(orderCourseModel);
        if(result!=null) {
        	//System.out.println("插入数据已存在，不能重复插入！");
            return "会员已经预约过该课程";
        }
        else if(fDate.compareTo(attendCourseTime)<0){
        	//System.out.println("插入数据不存在，我执行了");
        	OrderCourseModel result_2=orderCourseMapper.checkOrderCourse_2(orderCourseModel);
        	if(result_2!=null) {
        		//System.out.println("有课");
        		return "会员在该时段有课";
        	}
        	else {
        		//System.out.println("预约成功");
        		int rest=orderCourseMapper.QueryRestStock(orderCourseModel);
        		if(rest<0||rest==0) {
        			return "预约已满";
        		}
        		else {
        			orderCourseMapper.InsertOrderCourse(orderCourseModel);
                	orderCourseMapper.minStock(orderCourseModel);
                	return "预约成功";
        		}
        	}
        } 
        else {
        	//System.out.println("课程已经开始");
        	return "课程已经开始";
        }
   }    
   
   /**
    * 获取这个会员所有的约课信息
    * @param memberId
    * @param pageStart
    * @param pageSize
    * @return
    */
   public List<OrderCourseModel> OrderList(Integer memberId,Integer pageStart,Integer pageSize){
	   List<OrderCourseModel> orderCourse=orderCourseMapper.OrderList(memberId, pageStart, pageSize);
	   return orderCourse;
   }
   /**
    * 获取会员约课信息条数
    * @param memberId
    * @return
    */
   public int GetCountByMemberId(Integer memberId) {
	   int count=orderCourseMapper.getCountByMemberId(memberId);
	   return count;
   }
   
   public void DeleteOrderCourseByPrimary(Integer memberId,Integer courseId,Integer trainerId,String attendCourseTime) {
	   orderCourseMapper.deleteOrderCourseByPrimary(memberId, courseId, trainerId, attendCourseTime);
	   orderCourseMapper.plusStock(courseId, trainerId, attendCourseTime);
   }
}
