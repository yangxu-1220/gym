package com.gym.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.DataBaseManagementMapper;
import com.gym.model.DataBaseManagementModel;

@Service
public class DataBaseManagementService {
	@Autowired
	DataBaseManagementMapper dataBaseManagementMapper;
	
	public List<DataBaseManagementModel> getDataBase(){
//		for(DataBaseManagementModel a:dataBaseManagementMapper.selectDataBaseByNull()) {
//			System.out.println(a.getDatabase());
//		}
		return dataBaseManagementMapper.selectDataBaseByNull();
	}
}
