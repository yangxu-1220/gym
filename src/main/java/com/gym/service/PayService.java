package com.gym.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.PayMapper;
import com.gym.model.PayModel;
@Service
public class PayService {
	@Autowired
	PayMapper payMapper;
	
	//将交易记录写入数据库中
	public void insertBusiness(PayModel pay) {
		payMapper.insertBusiness(pay);
	}
}
