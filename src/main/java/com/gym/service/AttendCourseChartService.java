package com.gym.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.AttendCourseChartMapper;
import com.gym.model.AttendCourseChartModel;

@Service
public class AttendCourseChartService {
	@Autowired
	AttendCourseChartMapper attendCourseChartMapper;
	
	public List<AttendCourseChartModel> getAttendCourse(String startTime,String endTime,String trainerName,int page,int limit){
		if("所有教练".equals(trainerName)) {
			return attendCourseChartMapper.selectAttendCourseByNull(startTime,endTime,page,limit);
		}else{
			return attendCourseChartMapper.selectAttendCourseByTrainer(startTime,endTime,trainerName,page,limit);
		}
	}
	
	public int Count(String startTime,String endTime,String trainerName){		//统计数量
		if("所有教练".equals(trainerName)) {
			return attendCourseChartMapper.Count_1(startTime,endTime);
		}else{
			return attendCourseChartMapper.Count_2(startTime,endTime,trainerName);
		}
	}
	
	//查询教练姓名
	public List<AttendCourseChartModel> count(){
		return attendCourseChartMapper.selectTrainerNameByNull();
	}
	
	//查询教练姓名
	public List<AttendCourseChartModel> getTrainerName(){
		return attendCourseChartMapper.selectTrainerNameByNull();
	}
	
	//获取饼图1的数据
	public List<AttendCourseChartModel> getAttendCourseonPie_1(String startTime,String endTime){
		return attendCourseChartMapper.selectAttendCourseonPie_1(startTime,endTime);
	}
	
	//获取饼图2的数据
	public List<AttendCourseChartModel> getAttendCourseonPie_2(String startTime,String endTime){
		return attendCourseChartMapper.selectAttendCourseonPie_2(startTime,endTime);
	}
}
