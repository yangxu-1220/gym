package com.gym.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import com.gym.model.TrainerManagementModel;

public interface TrainerManagementMapper {
//	sql字符串
	//查询所有教练的信息
	String sqlString_1="select * "
			+ "from tb_trainer ";
	//添加新的教练信息
	String sqlString_2="insert into tb_trainer " + 
			"set trainer_id=#{trainerId},trainer_name=#{trainerName},trainer_sex=#{trainerSex},trainer_birthday=#{trainerBirthday},idcard_type=#{idcardType},idcard_number=#{idcardNumber},"
			+ "trainer_address=#{trainerAddress},trainer_phone=#{trainerPhone},join_time=#{joinTime},trainer_note=#{trainerNote},trainer_image=#{trainerImage}";
	//修改教练密码
	String sqlString_3="update tb_user set user_password=#{userPassword} where user_name=#{trainerPhone}";
	//修改教练信息
	String sqlString_4="update tb_trainer " +
			"set trainer_name=#{trainerName},trainer_sex=#{trainerSex},trainer_birthday=#{trainerBirthday},idcard_type=#{idcardType},idcard_number=#{idcardNumber}," + 
			"trainer_address=#{trainerAddress},trainer_phone=#{trainerPhone},join_time=#{joinTime},trainer_note=#{trainerNote},trainer_image=#{trainerImage} " +
			"where trainer_id=#{trainerId}";
	//删除教练信息
	String sqlString_5="delete from tb_trainer where trainer_id=#{trainerId}";
	//通过教练ID查询教练的信息
	String sqlString_6="select * " + 
			"from tb_trainer,tb_user " + 
			"where tb_trainer.trainer_phone=tb_user.user_name and trainer_id=#{trainerId}";
	//统计总数量
	String sqlString_7="select count(*) "
			+ "from tb_trainer ";
	
	//查询所有教练的信息
	@Select(sqlString_1+" limit #{page},#{limit} ")
	public List<TrainerManagementModel> selectTrainerManagementByNull(int page,int limit);
	
	//查询所有教练的信息
	@Select(sqlString_7)
	public int count();
	
	//添加新的教练信息
	@Select(sqlString_2)
	public void insertTrainerManagement(TrainerManagementModel trainer);
	
	//修改教练密码
	@Select(sqlString_3)
	public void updateTrainerPassword(TrainerManagementModel trainer);
	
	//修改教练信息
	@Select(sqlString_4)
	public void updateTrainerManagement(TrainerManagementModel trainer);
	
	//通过教练ID查询教练的信息
	@Select(sqlString_6)
	public TrainerManagementModel selectTrainerManagementByTrainerId(int trainerId);
	
	//删除教练信息
	@Select(sqlString_5)
	public void deleteTrainerManagementByTrainerId(int trainerId);
	
	//查询指定教练的信息
	@Select(sqlString_1+" where trainer_id=#{search} or trainer_name=#{search} limit #{page},#{limit} ")
	public List<TrainerManagementModel> selectTrainerManagementByTrainerIdorTrainerName(String search,int page,int limit);
	
	//查询指定教练的信息
	@Select(sqlString_7+" where trainer_id=#{search} or trainer_name=#{search}")
	public int countByTrainerIdorTrainerName(String search);
}
