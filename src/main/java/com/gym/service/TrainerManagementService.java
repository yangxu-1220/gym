package com.gym.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.config.SHA;
import com.gym.mapper.TrainerManagementMapper;
import com.gym.model.TrainerManagementModel;

@Service
public class TrainerManagementService {
	@Autowired
	TrainerManagementMapper trainerManagementMapper;
	
	public List<TrainerManagementModel> getTrainerManagementByNull(int page,int limit){		//查询所有教练信息
		return trainerManagementMapper.selectTrainerManagementByNull(page,limit);
	}
	
	public int count(){																		//统计总数量
		return trainerManagementMapper.count();
	}
	
	public void insertTrainerManagement(TrainerManagementModel trainer){		//添加新的教练信息
		SHA sHA=new SHA();
		trainer.setUserPassword(sHA.getResult(trainer.getTrainerPhone()+trainer.getUserPassword()));	//加密
		trainerManagementMapper.insertTrainerManagement(trainer);		//在tb_trainer表中添加数据
		trainerManagementMapper.updateTrainerPassword(trainer); 		//在tb_user表中修改教练密码
	}
	
	public void updateTrainerManagement(TrainerManagementModel trainer) {
		TrainerManagementModel trainerSelect=trainerManagementMapper.selectTrainerManagementByTrainerId(trainer.getTrainerId());
//		System.out.println("1:"+trainer.getUserPassword()+"2:"+trainerSelect.getUserPassword());
		SHA sHA=new SHA();
		String pass=sHA.getResult(trainer.getTrainerPhone()+trainer.getUserPassword());
		if(trainerSelect.getUserPassword().equals(trainer.getUserPassword()))		//判断用户是否修改密码
			trainer.setUserPassword(trainerSelect.getUserPassword());
		else
			trainer.setUserPassword(pass);
		trainerManagementMapper.updateTrainerManagement(trainer);		//在tb_trainer表中修改数据
		trainerManagementMapper.updateTrainerPassword(trainer);			//在tb_user表中修改教练密码
	}
	
	public TrainerManagementModel getTrainerManagementByTrainerId(int trainerId){		//按教练ID返回教练信息
		return trainerManagementMapper.selectTrainerManagementByTrainerId(trainerId);
	}
	
	public void deleteTrainerByTrainerId(int trainerId){		//删除教练信息
		trainerManagementMapper.deleteTrainerManagementByTrainerId(trainerId);
	}
	
	public List<TrainerManagementModel> getTrainerManagementByTrainerIdorTrainerName(String search,int page,int limit){		//查询指定教练信息
		return trainerManagementMapper.selectTrainerManagementByTrainerIdorTrainerName(search,page,limit);
	}
	
	public int countByTrainerIdorTrainerName(String search){																		//统计总数量
		return trainerManagementMapper.countByTrainerIdorTrainerName(search);
	}
}
