package com.gym.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.AttendPrivateCourseMapper;
import com.gym.model.AttendPrivateCourseModel;

@Service
public class AttendPrivateCourseService {
	@Autowired
	AttendPrivateCourseMapper attendPrivateCourseMapper;
	
	public List<AttendPrivateCourseModel> getAttendPrivateCourse(String startTime,String endTime,String trainerName,String courseName,String memberName,int page,int limit){
		if(trainerName.equals("所有教练") && courseName.equals("所有课程") && memberName.equals("")) {
			System.out.println("1");
			return attendPrivateCourseMapper.selectAttendPrivateCourseByNull(startTime,endTime,page,limit);
		}else if(!trainerName.equals("所有教练") && !courseName.equals("所有课程") && !memberName.equals("")) {
			System.out.println("2");
			return attendPrivateCourseMapper.selectAttendPrivateCourseByTrainerCourseMember(startTime,endTime,trainerName,courseName,memberName,page,limit);
		}else if(!trainerName.equals("所有教练") && courseName.equals("所有课程") && memberName.equals("")) {
			System.out.println("3");
			return attendPrivateCourseMapper.selectAttendPrivateCourseByTrainer(startTime,endTime,trainerName,page,limit);
		}else if(trainerName.equals("所有教练") && !courseName.equals("所有课程") && memberName.equals("")) {
			System.out.println("4");
			return attendPrivateCourseMapper.selectAttendPrivateCourseByCourse(startTime,endTime,courseName,page,limit);
		}else if(trainerName.equals("所有教练") && courseName.equals("所有课程") && !memberName.equals("")) {
			System.out.println("5");
			return attendPrivateCourseMapper.selectAttendPrivateCourseByMember(startTime,endTime,memberName,page,limit);
		}else if(!trainerName.equals("所有教练") && !courseName.equals("所有课程") && memberName.equals("")) {
			System.out.println("6");
			return attendPrivateCourseMapper.selectAttendPrivateCourseByTrainerCourse(startTime,endTime,trainerName,courseName,page,limit);
		}else if(!trainerName.equals("所有教练") && courseName.equals("所有课程") && !memberName.equals("")) {
			System.out.println("7");
			return attendPrivateCourseMapper.selectAttendPrivateCourseByTrainerMember(startTime,endTime,trainerName,memberName,page,limit);
		}else{
			System.out.println("8");
			return attendPrivateCourseMapper.selectAttendPrivateCourseByCourseMember(startTime,endTime,courseName,memberName,page,limit);
		}
	}
	
	public int count(String startTime,String endTime,String trainerName,String courseName,String memberName){
		if(trainerName.equals("所有教练") && courseName.equals("所有课程") && memberName.equals("")) {
			return attendPrivateCourseMapper.count_1(startTime,endTime);
		}else if(!trainerName.equals("所有教练") && !courseName.equals("所有课程") && !memberName.equals("")) {
			return attendPrivateCourseMapper.count_8(startTime,endTime,trainerName,courseName,memberName);
		}else if(!trainerName.equals("所有教练") && courseName.equals("所有课程") && memberName.equals("")) {
			return attendPrivateCourseMapper.count_2(startTime, endTime, trainerName);
		}else if(trainerName.equals("所有教练") && !courseName.equals("所有课程") && memberName.equals("")) {
			return attendPrivateCourseMapper.count_3(startTime, endTime, courseName);
		}else if(trainerName.equals("所有教练") && courseName.equals("所有课程") && !memberName.equals("")) {
			return attendPrivateCourseMapper.count_4(startTime, endTime, memberName);
		}else if(!trainerName.equals("所有教练") && !courseName.equals("所有课程") && memberName.equals("")) {
			return attendPrivateCourseMapper.count_5(startTime, endTime, trainerName, courseName);
		}else if(!trainerName.equals("所有教练") && courseName.equals("所有课程") && !memberName.equals("")) {
			return attendPrivateCourseMapper.count_6(startTime, endTime, trainerName, memberName);
		}else{
			return attendPrivateCourseMapper.count_7(startTime, endTime, courseName, memberName);
		}
	}
	
	//获取私教课程
	public List<AttendPrivateCourseModel> getCourseName(){
		return attendPrivateCourseMapper.selectCourseName();
	}
	
	//获取饼图1数据
	public List<AttendPrivateCourseModel> getAttendPrivateCourseonPie_1(String startTime,String endTime){
		return attendPrivateCourseMapper.selectAttendPrivateCourseonPie_1(startTime,endTime);
	}
	
	//获取饼图2数据
	public List<AttendPrivateCourseModel> getAttendPrivateCourseonPie_2(String startTime,String endTime){
		return attendPrivateCourseMapper.selectAttendPrivateCourseonPie_2(startTime,endTime);
	}
}
