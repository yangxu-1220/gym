package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.MTrainerMapper;
import com.gym.model.MTrainerModel;

@Service
public class MTrainerService {
	
	@Autowired
	MTrainerMapper mTrainerMapper;
	
	
	//将查询到的所有教练信息存储为一个MTrainerModel对象
	public List<MTrainerModel> GetAllTrainer(){
		List<MTrainerModel> trainer=mTrainerMapper.GetAllTrainer();
		return trainer;
	}
	//根据姓名查询教练ID
	public int GetIdByName(String trainerName) {
		int trainerId=mTrainerMapper.getIdByName(trainerName);
		return trainerId;
	}

}
