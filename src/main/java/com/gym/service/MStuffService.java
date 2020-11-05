package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.MStuffMapper;
import com.gym.model.MStuffModel;

@Service
public class MStuffService {

	@Autowired
	MStuffMapper mStuffMapper;
	
	//将查询到的所有会籍信息存储为一个MStaffModel对象
		public List<MStuffModel> GetAllStuff(){
			List<MStuffModel> stuff=mStuffMapper.GetAllStuff();
			return stuff;
		}

}
