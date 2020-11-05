package com.gym.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.gym.mapper.MemberMapper;
import com.gym.model.MemberModel;
import com.gym.service.MemberService;


/**
 * 会员管理实现控制层
 * @author jxn2019/09/12
 * 添加分页后改于 2019/10/10 
 */
@RequestMapping("/member")//分级路径
@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	/*
	 * 首页
	 */
	@RequestMapping("/index")
	public String GetAllMember(Model model) {
		List<MemberModel> member = memberService.getAllMember();
		model.addAttribute("member_A", member);
		return "MemberIndex";
	}	 	
	/*
	 * 添加会员
	 */
	@RequestMapping("/turnToMemberAdd")
	public String TurnToMemberAdd() {
		return "MemberAdd";
	}
	@RequestMapping("/memberAdd")
	@ResponseBody
	public void InsertMember(MemberModel memberModel,Model model) {
		//System.out.println(memberModel.getMemberImage());
		boolean count=memberService.InsertMember(memberModel);
		if(count) { 
			model.addAttribute("msg", "插入成功");
		}
		else {
			model.addAttribute("msg", "插入失败");						
	}
	//return "forward://MemberIndex";
  }
	/*
	 * 分页实现拿到前端Ajax传递的数据通过Service层去调用Mapper层查询数据库
	 */
	@RequestMapping("/getMemberBtween")
	@ResponseBody
	public Map<String,Object> getMemberBtween(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<MemberModel> memberModel_A=this.memberService.getMemberBtween(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.memberService.getMemberTotal();//获取数据库中数据总条数
		int total=rowSize%pageSize==0?rowSize/pageSize:rowSize/pageSize+1;
		data.put("total", total);//页数total
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data", memberModel_A);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	/*
	 * 根据选择的ID查询对应的会员信息去修改页面
	 */
	@RequestMapping("/selectMemberById")
	public String SelectMemberById(Integer id,Model model) {
		MemberModel memberModel_B=memberService.getMemberById(id);
		model.addAttribute("member_B", memberModel_B);
		return "MemberUpdate";
	}
	//给修改页面的下拉框赋初值
	@RequestMapping("/selectMemberToRender")
	@ResponseBody
	public Map<String,Object> SelectMemberToRender(Integer id,Model model) {
		Map<String,Object> data=new HashMap<String,Object>();
		MemberModel memberModel_B=memberService.getMemberById(id);
		data.put("data", memberModel_B);
		return data;
	}
	
	/*
	 * 判断是否有这一条数据
	 */
	@RequestMapping("/checkMemberById")
	@ResponseBody
	public String CheckMemberById(String id) {
		MemberModel memberModel_C=memberService.getMemberInfo(id);
		if(memberModel_C!=null) {
			return "true";
		}
		else {
			return "false";
		}
	}
	
	/*
	 * MemberUpdate提交的表单请求
	 */
	@RequestMapping("/doUpdate")
	@ResponseBody
	public void doUpdate(MemberModel memberModel,Model model) {
		boolean result=memberService.UpdateMember(memberModel);
		if(result)
			model.addAttribute("msg", "修改成功");
		else
			model.addAttribute("msg", "修改失败");
	}
	
	/*
	 * 根据ID去删除一条会员信息
	 */
	@RequestMapping("/deleteMemberById")
	@ResponseBody
	public boolean DeleteMemberById(Integer id,Model model) {
		boolean result=memberService.DeleteMemberById(id);
		if(result)
			return true;
		else
			return false;
	}
	
	/*
	 * 根据ID,姓名或者电话号码查询的会员信息
	 */
	@RequestMapping("/getMemberInfo")
	public String getMemberInfo(String memberA,Model model) {
		MemberModel memberS=memberService.getMemberInfo(memberA);
		model.addAttribute("memberS", memberS);
		//System.out.println(memberS.getMemberImage());
		return "MemberSearch";
	}
	
	/*
	 * Member转卡的表单请求 根据ID查询对应的会员信息
	 */
	@RequestMapping("/selectMemberToTrans")
	public String MemberTrans(Integer id,Model model) {
		MemberModel memT=memberService.getMemberById(id);
		model.addAttribute("memT", memT);
		return "MemberTrans";
	}
	//给续费页面拿初值
	@RequestMapping("/selectMemberToRenewal")
	public String MemberRenewal(Integer id,Model model) {
		MemberModel memR=memberService.getMemberToRenewal(id);
		model.addAttribute("memR", memR);
		return "Renewal";
	}
	//执行续费操作
	@RequestMapping("/doRenewal")
	@ResponseBody
	public void DoRenewal(Integer cardId,Integer number,String timeUnit) throws ParseException {
		memberService.DoRenewal(cardId, number, timeUnit);
	}
	
	@RequestMapping("/Transfer")
	@ResponseBody
	public void Transfer(MemberModel memberModel,Model model) {
		boolean result=memberService.MemberTrans(memberModel);
		if(result)
			model.addAttribute("msg", "转卡成功");
		else
			model.addAttribute("msg", "转卡失败");
		}
	/*
	 * Member升级的表单请求 根据ID查询对应的会员类型
	 */
	@RequestMapping("/getMemberTypeToUpgrade")
	@ResponseBody
	public List<String> MemberTypeToUpgrade(Integer id,Model model) {
		//MemberModel memU=memberService.getMemberById(id);
		//model.addAttribute("memU", memU);
	return memberService.getMemberUpgrade(id);
	}
	/*
	 * 根据ID查询对应的会员信息，存储到memU中
	 */
	@RequestMapping("/selectMemberToUpgrade")		
	public String MemberToUpgrade(Integer id,Model model) {
		MemberModel memU=memberService.getMemberById(id);
		int cardId=memberService.GetCardIdByMemberId(id);
		memU.setCardId(cardId);
		model.addAttribute("memU", memU);
	return "MemberUpgrade";
	}
	/*
	 * 会员升级
	 */
	@RequestMapping("/Upgrade")
	@ResponseBody
	public void Upgrade(MemberModel memberModel,Model model) throws ParseException {
		boolean result=memberService.MemberUpgrade(memberModel);
		//System.out.println("会员卡ID为："+memberModel.getCardId());
		//System.out.println("目标升级类型为："+memberModel.getMemberType());
		if(result)
		   model.addAttribute("msg", "升级成功");
		else
		   model.addAttribute("msg", "升级失败");
	}
	
	/*
	 * Member补卡的表单请求  根据ID查询对应的会员信息
	 */
	@RequestMapping("/selectMemberToIssue")
	public String MemberToIssue(Integer id,Model model) {
		MemberModel memI=memberService.getMemberById(id);
		model.addAttribute("memI", memI);
		return "MemberIssue";
	}
	/*
	 * 会员补卡
	 */
	@RequestMapping("/Issue")
	@ResponseBody
//	public void Issue(Integer id,Integer memberId,String memberPhone,String memberNote,String userPassword,Model model) {
//		boolean result=memberService.MemberIssue(id,memberId,memberPhone,memberNote,userPassword);
//		if(result)
//			model.addAttribute("msg", "补卡成功");
//		else
//		    model.addAttribute("msg", "补卡失败");
//	}
	public void Issue(Integer oldCardId,Integer newCardId) {
		memberService.MemberIssue(oldCardId, newCardId);
	}
	//获取禁用会员——分页
	@RequestMapping("/getOverTime")
	@ResponseBody
	public Map<String,Object> GetOverTime(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		Date currentDate = new Date();
		SimpleDateFormat nowTime = new SimpleDateFormat("yyyyMMdd");
        String currTime = nowTime.format(currentDate);
		List<MemberModel> overTime=this.memberService.GetOverTime(currTime, pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.memberService.CountOverTime(currTime);//获取数据库中数据总条数
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data", overTime);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	//获取可用会员——分页
	@RequestMapping("/getInTime")
	@ResponseBody
	public Map<String,Object> GetInTime(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		Date currentDate = new Date();
		SimpleDateFormat nowTime = new SimpleDateFormat("yyyyMMdd");
        String currTime = nowTime.format(currentDate);	
		List<MemberModel> overTime=this.memberService.GetInTime(currTime, pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.memberService.CountInTime(currTime);//获取数据库中数据总条数
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data", overTime);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	//挂失卡
	@RequestMapping("/reportLost")
	@ResponseBody
	public void ReportLost(Integer cardId) {
		memberService.ReportLost(cardId);
	}
}
