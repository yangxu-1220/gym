package com.gym.controller;

import javax.servlet.http.HttpSession;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.config.SHA;
import com.gym.model.MemberModel;
import com.gym.service.MemberService;
import com.gym.service.UserLoginService;

@Controller
public class UserLoginController {
	@Autowired
	UserLoginService userLoginService;
	@Autowired 
	MemberService memberService;
	
	@RequestMapping("/login")
	public String Login() {
		return "UserLogin";
	}
	
//	@RequestMapping("login_check")
//	public String LoginCheck(String name,String password,Map<String,Object> paramMap,HttpSession session) {
////		System.out.println("name"+name+"/"+"password"+password);
//		UserModel user=userLoginService.getUserInformation(name,password);
//		if(user.getStatus()==3) {
//			System.out.println("返回到地址next");
//			session.setAttribute("user",user);
//			return "forward:/consume";
//		}else {
//			System.out.println("应该要返回到Login页面");
//			paramMap.put("status",user.getStatus());
//			return "UserLogin";
//		}
//	}
	
	@RequestMapping("/login_check")
	public String LoginCheck(String name,String 
		password,Model model,HttpSession session) {
		//使用Shiro编写认证操作
		//1. 获取Subject
		Subject subject=SecurityUtils.getSubject();
		
		SHA sHA=new SHA();
		password=sHA.getResult(name+password);
		
		//2. 封装用户数据
		UsernamePasswordToken token=new UsernamePasswordToken(name,password);
		
		//3. 执行登录方法
		try {
			//执行登录
			subject.login(token);
			//获取用户Id
			int id=userLoginService.getUserIdByPhone(name);
//			System.out.println("id="+id);
			//验证成功
			session.setAttribute("id",id);		//将用户名的Id存成Session
			if(id<100000) {			//判断为管理员
				return "redirect:/change_1";
			}else if(id/100000==1) {	//判断为会员
				MemberModel member=memberService.getUserBy(id);
				System.out.println(member.getState());
				if("到期".equals(member.getState()) || "挂失".equals(member.getState())) {
					return "UnAuth";
				}
				else {
					return "redirect:/user/index";
				}
			}else if(id/100000==8) {	//判断为教练
				return "redirect:/shop/index";
			}else if(id/100000==6) {	//判断为前台u
				return "redirect:/member/index";
			}else {						//异常情况
				return "redirect:/login";
			}
		} catch (UnknownAccountException e) {
//			e.printStackTrace();
			//登录失败：用户名不存在
			model.addAttribute("status","1");
			System.out.println("用户名不存在！！！");
			return "UserLogin";
		}catch (IncorrectCredentialsException e) {
//			e.printStackTrace();
			//登录失败：密码错误
			model.addAttribute("status","2");
			System.out.println("密码错误！！！");
			return "UserLogin";
		}
	}
	
	//执行登出操作
	@RequestMapping("/login_out")
	public String LoginOut() {
		//1. 获取Subject
		Subject subject=SecurityUtils.getSubject();
		//2.执行登出操作
		subject.logout();
		System.out.println("退出登录！！！");
		return "UserLogin";
	}
	
	//非授权用户跳转页面
	@RequestMapping("/unAuth")
	public String UnAuth() {
		return "UnAuth";
	}
	
	//网站首页
	@RequestMapping("/index")
	public String Index() {
		return "index";
	}
	
//	@RequestMapping("/test")
//	public String Test() {
//		return "Test";
//	}
}
