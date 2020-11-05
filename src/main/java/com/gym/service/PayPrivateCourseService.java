package com.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.PayPrivateCourseMapper;
import com.gym.model.PayPrivateCourseModel;

@Service
public class PayPrivateCourseService {
	@Autowired
	PayPrivateCourseMapper payMapper;
	
	//将交易记录写入数据库中
	public void insertBusiness(PayPrivateCourseModel pay) {
		payMapper.insertBusiness(pay);
	}
}
