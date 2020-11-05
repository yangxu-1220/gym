package com.gym.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.model.CardEntityModel;
import com.gym.model.CardModel;
import com.gym.service.CardService;

/**
 * 会员卡管理实现控制层
 * @author jxn2019/09/04
 * 添加分页后改于 2019/10/04 
 */
@RequestMapping("/card")
@Controller
public class CardController {

	@Autowired
	CardService cardService;
	
	/**
	 * 会员卡管理首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String Cardindex(Model model) {
		List<CardModel> card=cardService.getAllCard();
		model.addAttribute("card",card);
		return "Cardindex";
	}
	
	 /**
	  * 返回一个CardModel类型的数据存为json给Ajax调用 此处用来提供给member使用
	  * @return
	  */
	@RequestMapping("/GetCardType")
	@ResponseBody
	public List<CardModel> GetCardType (){
		List<CardModel> card=cardService.getAllCard();
		return card;
	}
	
	 /**
	  * 根据ID,卡类型显示会员卡信息 2019年10月5日加
	  * @param cardA
	  * @param model
	  * @return
	  */
	@RequestMapping("/getCardInfo")
	public String getCardInfo(String cardA,Model model) {
		CardModel cardS=cardService.getCardInfo(cardA);
		model.addAttribute("cardS", cardS);
		return "CardDetail";
	}
	
	/**
	 * 增加会员卡
	 * @param model
	 * @return
	 */
	@RequestMapping("/turnCardAdd")
	public String Insert(Model model) {
		return "CardAdd";
	}
	
	@RequestMapping("/CardAdd")
	public void DoInsert(CardModel cardModel,Model model) {
		boolean result=cardService.InsertCard(cardModel);
		if(result) 
			model.addAttribute("msg","增加成功");
		else 
			model.addAttribute("msg","增加失败");
	}
	/**
	 * 修改会员卡信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/update")
	public String Update(int id,Model model) {
		CardModel cards=cardService.getCardById(id);
		model.addAttribute("cards",cards);
		return "CardUpdate";
	}
	@RequestMapping("/doUpdate")
	public void DoUpdate(CardModel cardModel,Model model) {
		boolean result=cardService.UpdateCard(cardModel);
		if(result)
			model.addAttribute("msg","修改成功");
		else 
			model.addAttribute("msg","修改失败");
	}
	 /**
	  * 删除会员卡
	  * @param id
	  * @param model
	  * @return
	  */
	@RequestMapping("/doDelete")
	@ResponseBody
	public boolean DeleteCardInfo(int id,Model model) {
		 boolean result=cardService.DeleteCardInfo(id);
	if(result) 
			return true;
		else
	    return false;
	}
	 /**
	  * 分页 2019年10月4日 分页实现拿到前端Ajax传递的数据通过Service层去调用Mapper层查询数据库
	  */
	@RequestMapping("/getCardBtween")
	@ResponseBody
	public Map<String,Object> getCardBtween(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<CardModel> cardModelA=this.cardService.getCardBtween(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.cardService.getCardTotal();//获取数据库中数据总条数
		int total=rowSize%pageSize==0?rowSize/pageSize:rowSize/pageSize+1;
		data.put("total", total);//页数total
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",cardModelA);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	@RequestMapping("/index_entity")
	public String Index_entity() {
		return "CardManagementIndex";
	}
	/**
	 * 会员卡页面的分页接口
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getCardEntityBtween")
	@ResponseBody
	public Map<String,Object> getCardEntityBtween(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<CardEntityModel> cardModel_E=this.cardService.GetCardEntityBtween(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.cardService.CountCardEntity();//获取数据库中数据总条数
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",cardModel_E);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	/**
	 * 可用会员卡页面的分页接口
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getAvailable")
	@ResponseBody
	public Map<String,Object> GetAvailable(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<CardEntityModel> cardModel_E=this.cardService.GetAvailable(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.cardService.CountAvailable();//获取数据库中数据总条数
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",cardModel_E);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	/**
	 * 到期会员卡页面的分页接口
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getUnAvailable")
	@ResponseBody
	public Map<String,Object> GetUnAvailable(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<CardEntityModel> cardModel_E=this.cardService.GetUnAvailable(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.cardService.CountUnAvailable();//获取数据库中数据总条数
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",cardModel_E);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
	
	/**
	 * 挂失会员卡页面的分页接口
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getLost")
	@ResponseBody
	public Map<String,Object> GetLost(
	@RequestParam(value="pageStart",defaultValue="0") Integer pageStart,
	@RequestParam(value="pageSize",defaultValue="10") Integer pageSize){
		List<CardEntityModel> cardModel_E=this.cardService.GetLost(pageStart, pageSize);
		Map<String,Object> data=new HashMap<String,Object>();
		int rowSize=this.cardService.CountLost();//获取数据库中数据总条数
		data.put("pageStart", pageStart);
		data.put("rowSize", rowSize);
		data.put("data",cardModel_E);//List对象存为data
		data.put("limit", pageSize);	
		return data;
	}
}
