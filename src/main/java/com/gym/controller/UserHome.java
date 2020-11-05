package com.gym.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.model.MemberModel;
import com.gym.service.MemberService;

@RequestMapping("/user")
@Controller
public class UserHome {

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/index")
	public String getUserByUserName(Integer memberId,Model model,HttpServletRequest request) {
		HttpSession session=request.getSession();
		int id=(int)session.getAttribute("id");
		MemberModel user=memberService.getUserBy(id);
		model.addAttribute("user",user);
		return "UserHome";
	}
//	@RequestMapping("/index_1")
//	public String First() {
//		return "UserFirst";
//	}
}
