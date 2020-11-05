package com.gym.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.MemberVisitMapper;
import com.gym.model.MemberVisitModel;

@Service
public class MemberVisitService {
	@Autowired
	MemberVisitMapper memberVisitMapper;
	List<MemberVisitModel> memberVisitList;
	
	public List<MemberVisitModel> getMemberVisit(String startTime,String endTime,String member,int page,int limit){
		if(member=="") {			
			//System.out.println("1");
			memberVisitList=memberVisitMapper.selectMemberVisitByDetailNull(startTime, endTime, page, limit);
			return memberVisitList;
		}else {						
			//System.out.println("2");
			memberVisitList=memberVisitMapper.selectMemberVisitByDetailMember(startTime, endTime, member, page, limit);
			return memberVisitList;
		}
	}
	
	public int count(String startTime,String endTime,String member){
		if(member=="") {			
			return memberVisitMapper.count_1(startTime, endTime);
		}else {						
			return memberVisitMapper.count_2(startTime, endTime, member);
		}
	}
	
	//获取条形图和折线图的数据
	public List<MemberVisitModel> getMemberVisitonBarLine(String startTime,String endTime){
		return memberVisitMapper.selectMemberVisitonBarLine(startTime,endTime);
	}
}
