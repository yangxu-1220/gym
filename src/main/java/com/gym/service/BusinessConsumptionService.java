package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.BusinessConsumptionMapper;
import com.gym.model.BusinessConsumptionModel;

@Service
public class BusinessConsumptionService {
	@Autowired
	BusinessConsumptionMapper businessConsumptionMapper;
	
	public List<BusinessConsumptionModel> getBusinessConsumption(String startTime,String endTime,int page,int limit){
		return businessConsumptionMapper.selectBusinessConsumption(startTime, endTime, page, limit);
	}
	
	public int count(String startTime,String endTime){
		return businessConsumptionMapper.count(startTime, endTime);
	}
	
	//获取饼图数据
	public List<BusinessConsumptionModel> getBusinessonPie(String startTime,String endTime){
		return businessConsumptionMapper.selectBusinessonPie(startTime,endTime);
	}
	
	//获取条形图和折线图的数据
	public List<BusinessConsumptionModel> getBusinessonBarLine(String startTime,String endTime){
		return businessConsumptionMapper.selectBusinessonBarLine(startTime,endTime);
	}
}
