package com.gym.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.PrivateCourseManagementMapper;
import com.gym.model.PrivateCourseForBoughtModel;
import com.gym.model.PrivateCourseForBuyModel;
import com.gym.model.PrivateCourseForSelectModel;

@Service
public class PrivateCourseManagementService {
	@Autowired
	PrivateCourseManagementMapper privateCourseManagementMapper;
	
	//通过会员ID查询会员将要上的私教课
	public List<PrivateCourseForSelectModel> selectPrivateCourseForSelectByMemberId(int id,int page,int limit){
		Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = simpleDateFormat.format(currentDate);
		return privateCourseManagementMapper.selectPrivateCourseForSelectByMemberId(nowTime, id, page, limit);
	}
	
	//统计数量3
	public int count_3(int id){
		Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = simpleDateFormat.format(currentDate);
		return privateCourseManagementMapper.count_3(nowTime, id);
	}
	
	//查询所有私教课程
	public List<PrivateCourseForBuyModel> selectPrivateCourseForBuy(int page,int limit){
		return privateCourseManagementMapper.selectPrivateCourseForBuy(page, limit);
	}
	
	//统计数量1
	public int count_1(){
		return privateCourseManagementMapper.count_1();
	}
	
	//通过私教课程名查询购买私教课程信息
	public PrivateCourseForBuyModel selectPrivateCourseForBuyByPrivateCourseName(String privateCourseName){
		return privateCourseManagementMapper.selectPrivateCourseForBuyByPrivateCourseName(privateCourseName);
	}
	
	//将私教课程购买信息写入到数据库中
	public void insertBuyPrivateCourse(String privateCourseName,String trainerName,int memberId,String privateCourseNumber) {
		if(memberId/100000==1) {
			List<PrivateCourseForBuyModel> privateCourse=privateCourseManagementMapper.selectPrivateCourse();
			List<PrivateCourseForBuyModel> trainer=privateCourseManagementMapper.selectTrainer();
			int privateCourseId=0;
			int trainerId=0;
			for(PrivateCourseForBuyModel l1:privateCourse) {
				if((l1.getPrivateCourseName()).equals(privateCourseName)) {
					privateCourseId=l1.getPrivateCourseId();
					break;
				}
			}
			for(PrivateCourseForBuyModel l2:trainer) {
				if((l2.getTrainerName()).equals(trainerName)) {
					trainerId=l2.getTrainerId();
					break;
				}
			}
			if(privateCourseId==0 || trainerId==0)
				return ;
			Date currentDate = new Date();							//将时间戳作为商户订单号
	        SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String date = nowTime.format(currentDate);
	        privateCourseManagementMapper.insertPrivateCourseForBuy(privateCourseId,trainerId,memberId,date,privateCourseNumber);		//将信息写入数据库中
		}else
			return ;
	}
	
	//通过会员ID查询会员购买的私教课程信息
	public List<PrivateCourseForBoughtModel> selectPrivateCourseForBought(int memberId,int page,int limit){
		return privateCourseManagementMapper.selectPrivateCourseForBought(memberId,page,limit);
	}
	
	//通过会员ID查询会员购买的私教课程信息
	public int count_2(int memberId){
		return privateCourseManagementMapper.count_2(memberId);
	}
	
	
	
	//通过教练ID查询教练将要上的私教课
	public List<PrivateCourseForSelectModel> selectPrivateCourseForSelectByTrainerId(int id,int page,int limit){
		Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = simpleDateFormat.format(currentDate);
		return privateCourseManagementMapper.selectPrivateCourseForSelectByTrainerId(nowTime, id, page, limit);
	}
	
	//统计数量4
	public int count_4(int id){
		Date currentDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = simpleDateFormat.format(currentDate);
		return privateCourseManagementMapper.count_4(nowTime, id);
	}
	
	//通过教练ID查询该教练私教课程售卖情况
	public List<PrivateCourseForBoughtModel> selectPrivateCourseForAttend(int trainerId){
		return privateCourseManagementMapper.selectPrivateCourseForAttend(trainerId);
	}
}
