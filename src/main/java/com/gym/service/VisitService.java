package com.gym.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.VisitMapper;
import com.gym.model.VisitModel;

@Service
public class VisitService {
	
	@Autowired
	VisitMapper visitMapper;
	
	//会员来访查询会员信息
	public VisitModel VisitById(Integer cardId) {
		VisitModel visitModel=visitMapper.visitById(cardId);
		return visitModel;
	}
	//插入一条新的会员来访记录
	public void OnceVisit(Integer id) {
		Date currentDate = new Date();
		SimpleDateFormat nowTime = new SimpleDateFormat("yyyyMMddHHmmss");
        String visitTime = nowTime.format(currentDate);
        int memberId=visitMapper.getMemberIdByCardId(id);
		visitMapper.onceVisit(memberId, visitTime);//执行插入操作
	}
	//统计一个会员的来访次数
	public int countVisit(Integer id) {
		int memberId=visitMapper.getMemberIdByCardId(id);
		int count=visitMapper.countVisit(memberId);
		return count;
	}

}
