package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.gym.model.MTrainerModel;

@Repository
public interface MTrainerMapper {
	
	
	//查询所有的教练信息
	@Select("SELECT * FROM tb_trainer")
	public List<MTrainerModel> GetAllTrainer();
	//根据姓名查ID
	@Select("SELECT trainer_id FROM tb_trainer WHERE trainer_name=#{trainerName}")
	public int getIdByName(@Param("trainerName") String trainerName);

}
