package com.gym.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.config.SHA;
import com.gym.mapper.StuffManagementMapper;
import com.gym.model.StuffManagementModel;

@Service
public class StuffManagementService {
	@Autowired
	StuffManagementMapper stuffManagementMapper;
	
	//查询全部员工信息
	public List<StuffManagementModel> getStuff(int page,int limit){
		return stuffManagementMapper.selectStuffByNull(page,limit);
	}
	
	//统计总数量
	public int count(){
		return stuffManagementMapper.count();
	}
	
	//查询员工职位类型
	public List<StuffManagementModel> getStuffType(){
		return stuffManagementMapper.selectStuffTypeByNull();
	}

	//新增员工信息
	public void getInsertStuff(StuffManagementModel stuff) {
		SHA sHA=new SHA();
		System.out.println("新增员工未加密密码："+stuff.getUserPassword());
		stuff.setUserPassword(sHA.getResult(stuff.getStuffPhone()+stuff.getUserPassword()));	//加密
		System.out.println("新增员工加密密码："+stuff.getUserPassword());
		stuffManagementMapper.insertStuffByStuff(stuff);
		stuffManagementMapper.updateStuffByStuff(stuff);
	}
	
	//在修改功能中显示默认值
	public StuffManagementModel getUpdateStuffByStuffId(int stuffId) {
		return stuffManagementMapper.selectStuffByStuffId(stuffId);
	}
	
	//修改员工信息以及相应的员工密码
	public void getUpdateStuff(StuffManagementModel stuff) {
		int stuffId=stuff.getStuffId();
		String stuffType=stuff.getStuffType();
		String stuffName=stuff.getStuffName();
		String stuffSex=stuff.getStuffSex();
		String stuffBirthday=stuff.getStuffBirthday();
		String idcardType=stuff.getIdcardType();
		String idcardNumber=stuff.getIdcardNumber();
		String stuffAddress=stuff.getStuffAddress();
		String stuffPhone=stuff.getStuffPhone();
		String joinTime=stuff.getJoinTime();
		String stuffNote=stuff.getStuffNote();
		String stuffImage=stuff.getStuffImage();
//		String userPassword=stuff.getUserPassword();
		SHA sHA=new SHA();
		StuffManagementModel checkPassword=stuffManagementMapper.selectStuffByStuffId(stuff.getStuffId());		//判断用户是否修改密码
		System.out.println("数据库查询的密码："+checkPassword.getUserPassword());
		String pass=sHA.getResult(stuff.getStuffPhone()+stuff.getUserPassword());		//前端密码加密
		if(checkPassword.getUserPassword().equals(stuff.getUserPassword())) {
			stuff.setUserPassword(checkPassword.getUserPassword());
			System.out.println("未修改加密密码"+stuff.getUserPassword());
		} else {
			stuff.setUserPassword(pass);
			System.out.println("修改加密密码："+stuff.getUserPassword());
		}
		stuffManagementMapper.updateStuffById(stuffId, stuffType, stuffName, stuffSex, stuffBirthday, idcardType, idcardNumber, stuffAddress, stuffPhone, joinTime, stuffNote, stuffImage);
		stuffManagementMapper.updateStuffByStuff(stuff);
	}
	
	public void getDeleteStuff(String stuffId) {
		stuffManagementMapper.deleteStuffById(stuffId);
	}
	
	//查询指定员工信息
	public List<StuffManagementModel> getStuffByStuffIdorStuffName(String search,int page,int limit){
		return stuffManagementMapper.selectStuffByStuffIdorStuffName(search, page, limit);
	}
	
	//统计指定员工数量
	public int count_2(String search) {
		return stuffManagementMapper.countByStuffIdorStuffName(search);
	}
}
