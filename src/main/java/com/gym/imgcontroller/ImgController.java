package com.gym.imgcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gym.model.MemberModel;

@Controller
public class ImgController {

	@RequestMapping(value="/hh" ,method=RequestMethod.GET)
	public String index(MemberModel memberModel,String memberId,Model model) {
		model.addAttribute("memberId",memberId);
		return "ImgUpload";
	}
}
