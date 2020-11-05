package com.gym.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.PrivateCourseSaleMapper;
import com.gym.model.PrivateCourseSaleModel;

@Service
public class PrivateCourseSaleService {
	@Autowired
	PrivateCourseSaleMapper privateCourseSaleMapper;
	
	public List<PrivateCourseSaleModel> getPrivateCourseSale(String startTime,String endTime,String trainerName,String courseName,int page,int limit) {
		if(trainerName.equals("所有教练") && courseName.equals("所有课程")) {
			return privateCourseSaleMapper.selectPrivateCourseSaleByNull(startTime, endTime, page, limit);
		}else if(!trainerName.equals("所有教练") && courseName.equals("所有课程")) {
			return privateCourseSaleMapper.selectPrivateCourseSaleByTrainer(startTime, endTime, trainerName, page, limit);
		}else if(trainerName.equals("所有教练") && !courseName.equals("所有课程")) {
			return privateCourseSaleMapper.selectPrivateCourseSaleByCourse(startTime, endTime, courseName, page, limit);
		}else{
			return privateCourseSaleMapper.selectPrivateCourseSaleByTrainerCourse(startTime, endTime, trainerName, courseName, page, limit);
		}
	}
	
	//统计总人数
	public int count(String startTime,String endTime,String trainerName,String courseName) {
		if(trainerName.equals("所有教练") && courseName.equals("所有课程")) {
			return privateCourseSaleMapper.count_1(startTime, endTime);
		}else if(!trainerName.equals("所有教练") && courseName.equals("所有课程")) {
			return privateCourseSaleMapper.count_2(startTime, endTime, trainerName);
		}else if(trainerName.equals("所有教练") && !courseName.equals("所有课程")) {
			return privateCourseSaleMapper.count_3(startTime, endTime, courseName);
		}else{
			return privateCourseSaleMapper.count_4(startTime, endTime, trainerName, courseName);
		}
	}
	
	//查询教练姓名
	public List<PrivateCourseSaleModel> getTrainerName(){
		return privateCourseSaleMapper.selectTrainerNameByNull();
	}
	
	//查询所有私教课程
	public List<PrivateCourseSaleModel> getPrivateCourse(){
		return privateCourseSaleMapper.selectPrivateCourseByNull();
	}
	
	//获取饼图1数据
	public List<PrivateCourseSaleModel> getPrivateCourseSaleonPie_1(String startTime,String endTime){
		return privateCourseSaleMapper.selectPrivateCourseSaleonPie_1(startTime,endTime);
	}
	
	//获取饼图2数据
	public List<PrivateCourseSaleModel> getPrivateCourseSaleonPie_2(String startTime,String endTime){
		return privateCourseSaleMapper.selectPrivateCourseSaleonPie_2(startTime,endTime);
	}
	
	//获取条形图折线图数据
	public List<PrivateCourseSaleModel> getPrivateCourseSaleonBarLine(String startTime,String endTime){
		return privateCourseSaleMapper.selectPrivateCourseSaleonBarLine(startTime,endTime);
	}
}
