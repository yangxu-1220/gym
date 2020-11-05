package com.gym.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 数据库的备份与还原
 */
public class DataBase {
	
	@Scheduled(cron="0 0 1 * * ?") //每天凌晨1点执行一次
    public static void BackupByTime() throws  Exception{
        System.out.println("生成备份文件");
        
    }
	
//	public static void main(String[] args) {		//测试主方法
//		Backup("root","123456","chess","C:\\Users\\m1886\\Desktop\\数据库备份目录\\");
//	}
	
	/**
         * 执行生成备份
    */
    public static boolean Backup(String user,String password,String database,String backupPath){	//backupPath="C:\\Users\\m1886\\Desktop\\数据库备份目录\\"
    	System.out.println("现在时间是"+new Date());
        Date currentDate = new Date();
        SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String fDate = nowTime.format(currentDate);
        System.out.println("输出："+user+"/"+password+"/"+database+"/"+backupPath);
        String filepath = backupPath + database + fDate + ".sql"; 	// 备份的路径地址
        File dir = new File("D:\\download\\MySQL\\bin\\");			//执行路径
        String comStr = "mysqldump -h localhost -u "+user+" -p"+password+" --databases "+database+">"+filepath;	//命令字符串
        System.out.println(comStr);
        try {
            String[] command = { "cmd", "/c", comStr};
            Process p=Runtime.getRuntime().exec(command,null,dir);		//执行命令
            InputStream input = p.getInputStream();
            System.out.println(IOUtils.toString(input, "GBK"));
            //若有错误信息则输出
            InputStream errorStream = p.getErrorStream();
            String message=IOUtils.toString(errorStream, "GBK");
            System.out.println(message);
            if(message.indexOf("error")==-1)
            	return true;		//返回数据库备份成功
            else
            	return false;		//返回数据库备份失败
        } catch (IOException e) {
            e.printStackTrace();
            return false;			//返回数据库备份失败
        }
    }
    
    /**
          * 还原数据库
     */
    public static boolean Restore(String user,String password,String database,String restoreFile) {		//restoreFile="C:\\Users\\m1886\\Desktop\\数据库备份目录\\"+fileName
        System.out.println("现在时间是" + new Date());
        File dir = new File("D:\\download\\MySQL\\bin\\");			//执行路径
        System.out.println("输出："+user+"/"+password+"/"+database+"/"+restoreFile);
        try {
            String comStr = "mysql -u "+user+" -p"+password+" "+database+"<"+restoreFile;
            System.out.println(comStr);
            String[] command = {"cmd", "/c", comStr};
            Process p=Runtime.getRuntime().exec(command,null,dir);		//执行命令
            //若有错误信息则输出
            InputStream errorStream = p.getErrorStream();
            System.out.println(IOUtils.toString(errorStream, "GBK"));
            //等待操作
            int processComplete = p.waitFor();
            if (processComplete == 0) {
                //System.out.println("还原成功");
                return true;		//数据库还原成功
            } else {
                //throw new RuntimeException("还原数据库失败");
                return false;		//数据库还原失败
            }
        } catch (Exception e) {
            e.printStackTrace();
            	return false;		//数据库还原失败
        }
    }
}