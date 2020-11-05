package com.gym.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.config.DataBase;
import com.gym.model.DataBaseManagementModel;
import com.gym.service.DataBaseManagementService;

@Controller
public class DataBaseManagementController {
	@Autowired
	DataBaseManagementService dataBaseManagementService;
	
	int status=1;		//表示数据库备份或恢复是否成功	1：表示失败；2：表示成功
	
	@RequestMapping("/setting/database")
	public String DataBase() {
		return "DataBase";
	}
	
	@RequestMapping("/test")
	public String Test() {
		DataBase.Backup("root","123456","chess","C:\\Users\\24376\\Desktop\\DataBase\\");
		return "DataBase";
	}
	
	@ResponseBody
	@RequestMapping("/setting/database_get")
	public List<DataBaseManagementModel> GetDataBase(Model model) {
		List<DataBaseManagementModel> dataBaseList=dataBaseManagementService.getDataBase();
//		System.out.println("执行成功！！！");
		return dataBaseList;
	}
	
	@RequestMapping("/setting/backup_do")
	@ResponseBody
	public int BackUpDo(String user,String password,String database,String backupPath) {	//执行备份数据库
		//System.out.println(user+password+database+backupPath);
		backupPath="C:\\Users\\24376\\Desktop\\DataBase\\";
		if(DataBase.Backup(user,password,database,backupPath)==true) {			//调用备份接口
			status=2;
//			System.out.println("2");
		}else {
			status=1;
//			System.out.println("1");
		}
		return status;
	}	
	
	@RequestMapping("/setting/restore_do")
	@ResponseBody
	public int RestoreDo(String user,String password,String database,String restoreFile) {	//执行恢复数据库
		System.out.println(restoreFile);
		String[] str=restoreFile.split("fakepath");
//		System.out.println(str[1]);
		restoreFile="C:\\Users\\24376\\Desktop\\DataBase"+str[1];
		System.out.println(user+password+database+restoreFile);
		if(DataBase.Restore(user,password,database,restoreFile)==true) {			//调用恢复接口
			status=2;
//			System.out.println("2");
		}else {
			status=1;
//			System.out.println("1");
		}
		return status;
	}	
}
