package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.MemberConsumptionMapper;
import com.gym.model.MemberConsumptionModel;

@Service
public class MemberConsumptionService {
	
	@Autowired
	MemberConsumptionMapper memberConsumptionMapper;
	
	public List<MemberConsumptionModel> getMemberConsumption(String startTime,String endTime,String member,int page,int limit){
		if(member!="") {
			System.out.println("1");
			return memberConsumptionMapper.selectMemberConsumptionByMember(startTime, endTime, member, page, limit);
		}else{
			System.out.println("2");
			return memberConsumptionMapper.selectMemberConsumptionByNull(startTime, endTime, page, limit);
		}
	}
	
	public int count(String startTime,String endTime,String member){
		if(member!="") {
			System.out.println("1");
			return memberConsumptionMapper.count_2(startTime, endTime, member);
		}else{
			System.out.println("2");
			return memberConsumptionMapper.count_4(startTime, endTime);
		}
	}
	
	//查询所有员工姓名
//	public List<MemberConsumptionModel> getStuffByNull(){
//		return memberConsumptionMapper.selectStuffByNull();
//	}
	
	//查询数据--饼图
	public List<MemberConsumptionModel> getMemberonPie(String startTime,String endTime){
		return memberConsumptionMapper.selectMemberonPie(startTime,endTime);
	}
	
	//查询数据--条形图和折线图
	public List<MemberConsumptionModel> getMemberonBarLine(String startTime,String endTime){
		return memberConsumptionMapper.selectMemberonBarLine(startTime,endTime);
	}
}
