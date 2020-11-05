package com.gym.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gym.config.SHA;
import com.gym.model.UserModel;
import com.gym.service.UserManagementService;

@Controller
public class UserManagementController {
	@Autowired
	UserManagementService userManagementService;
	
	//获取user信息
	@RequestMapping("/user/get_user")
	@ResponseBody
	public UserModel getUserMessage(HttpServletRequest request,Model model) {
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("id");
//		System.out.println(id);
		UserModel user=userManagementService.getUserManagement(id);
//		String img=user.getImage();
//		if(img!=null) {
//			String[] str=img.split("\"");
//			user.setImage(str[1]);
//		}
//		System.out.println(user);
		return user;
	}
	
	//获取user信息
	@RequestMapping("/user/get_detail_user")
	public String getUserDetailMessage(HttpServletRequest request,Model model) {
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("id");
		UserModel user=userManagementService.getDetailUserManagement(id);
		System.out.println("用户密码："+user.getPassword());
		user.setId(id);
		user.setImage("<img src=\""+user.getImage()+"\" class=\"layui-nav-img\">");
		model.addAttribute("user",user);
		return "UserManagement";
	}
	
	//获取user密码
	@RequestMapping("/user/get_user_password")
	@ResponseBody
	public String getUserDetailMessage(String password,HttpServletRequest request) {
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("id");
		UserModel user=userManagementService.getDetailUserManagement(id);
		SHA sHA=new SHA();
		if(user.getPassword().equals(sHA.getResult(user.getName()+password)))
			return "true";			//密码一致
		else
			return "false";			//密码不一致
	}
	
	@RequestMapping("/user/user_update")
	@ResponseBody
	public void StuffAddDo(@RequestParam("userImage") MultipartFile userImage,UserModel user,HttpServletRequest request) {
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("id");
		if(!userImage.isEmpty()) {			//判断是否上传文件
			//获取当前时间
			Date currentDate = new Date();
	        SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	        String fDate = nowTime.format(currentDate);
	        
	        String fileName = user.getUserName()+fDate+".png";			//文件名称
	        String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\images\\";
	        System.out.println(filePath + fileName);
	        File dest = new File(filePath + fileName);
	        try {
	        	userImage.transferTo(dest);
	        } catch (IOException e) {
	        	 e.printStackTrace();
	        }
	        String imageString="http:\\\\localhost:8080\\images\\"+fileName;		//"<img src=\""+"http:\\\\localhost:8080\\images\\"+fileName+"\" class=\"layui-nav-img\">";
	        user.setImage(imageString);
		}else {
			user.setImage(userManagementService.getDetailUserManagement(id).getImage());
		}
//		if(user.getPassword()==null)
//			System.out.println("密码为null");
//		if("".equals(user.getPassword()))
//			System.out.println("密码为空字符串");
		user.setId(id);
		System.out.println("密码："+user.getPassword()+user.getImage());
		userManagementService.updateUser(user);		//修改用户信息
	}
}
