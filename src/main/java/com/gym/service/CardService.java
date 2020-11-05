package com.gym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mapper.CardMapper;
import com.gym.model.CardEntityModel;
import com.gym.model.CardModel;

/**
 * 会员卡管理业务逻辑层
 * @author jxn 2019/09/05 16:00 
 * 添加分页后改于 2019/10/04 20:17
 */

@Service
public class CardService {

	@Autowired
	CardMapper cardMapper;
	 /**
	  * 查询所有的会员卡信息
	  * @return  
	  */
	public List<CardModel> getAllCard(){
	 	return cardMapper.getAllCard();
	}
	 
	 /**
	  * 将根据id查询到的所有会员卡信息存为card对象
	  * @param cardId
	  * @return cards
	  */
    public CardModel getCardById(int id) {
		CardModel cards=cardMapper.getCardById(id);
		return cards;		
	}	
    
   	  /**
   	   * 插入会员卡
   	   * @param cardModel
   	   * @return 添加的会员卡信息
   	   */
    public boolean InsertCard(CardModel cardModel) {
		int count=cardMapper.InsertCard(cardModel);
		if(count>0)
			return true;
		return false;
	}
   	 /**
   	  *  修改会员卡
   	  * @param cardModel
   	  * @return  修改的会员信息
   	  */
	public boolean UpdateCard(CardModel cardModel) {
		int count=cardMapper.UpdateCard(cardModel);
		if(count>0)
			return true;
		return false;
		
	}
   	  /**
   	   * 删除会员卡
   	   * @param cardId
   	   * @return 
   	   */
	public boolean DeleteCardInfo(int id) {
		int count=cardMapper.DeleteCardInfo(id);
		if(count>0)
			return true;
		return false;
		
	} 
	  /**
	   * 动态渲染table和分页 2019年10月4日加
	   * @param pageStart
	   * @param pageSize
	   * @return
	   */
	public List<CardModel> getCardBtween(int pageStart,int pageSize){
		List<CardModel> cardModelA=cardMapper.getCardBtween(pageStart, pageSize);
		return cardModelA;		
	}
	 /**
	  * 获取card表中数据的总数 2019年10月4日加
	  * @return
	  */
	public int getCardTotal() {
		int rowSize=cardMapper.getCardTotal();
		return rowSize;
	}
	/**
	  * 将根据id,卡类型查询会员信息，将信息存为card对象 2019年10月5日加
	 */
   public CardModel getCardInfo(String cardA) {
   	CardModel cardS=cardMapper.getCardInfo(cardA);
		return cardS;		
	}
   /**
    * 获取会员卡对象集
    * @param pageStart
    * @param pageSize
    * @return
    */
   public List<CardEntityModel> GetCardEntityBtween(Integer pageStart,Integer pageSize){
	   List<CardEntityModel> cardEntityModel=cardMapper.getCardEntityBtween(pageStart, pageSize);
	   return cardEntityModel;
   }
   /**
    * 统计会员卡数量
    * @return
    */
   public int CountCardEntity() {
	   int count=cardMapper.countCardEntity();
	   return count;
   }
   
   /**
    * 获取可用会员卡对象集
    * @param pageStart
    * @param pageSize
    * @return
    */
   public List<CardEntityModel> GetAvailable(Integer pageStart,Integer pageSize){
	   List<CardEntityModel> cardEntityModel=cardMapper.getAvailable(pageStart, pageSize);
	   return cardEntityModel;
   }
	
   /**
    * 统计可用会员卡数量
    * @return
    */
   public int CountAvailable() {
	   int count=cardMapper.countAvailable();
	   return count;
   }
   
   /**
    * 获取到期会员卡对象集
    * @param pageStart
    * @param pageSize
    * @return
    */
   public List<CardEntityModel> GetUnAvailable(Integer pageStart,Integer pageSize){
	   List<CardEntityModel> cardEntityModel=cardMapper.getUnAvailable(pageStart, pageSize);
	   return cardEntityModel;
   }
	
   /**
    * 统计到期会员卡数量
    * @return
    */
   public int CountUnAvailable() {
	   int count=cardMapper.countUnAvailable();
	   return count;
   }
   
   /**
    * 获取挂失会员卡对象集
    * @param pageStart
    * @param pageSize
    * @return
    */
   public List<CardEntityModel> GetLost(Integer pageStart,Integer pageSize){
	   List<CardEntityModel> cardEntityModel=cardMapper.getLost(pageStart, pageSize);
	   return cardEntityModel;
   }
	
   /**
    * 统计挂失会员卡数量
    * @return
    */
   public int CountLost() {
	   int count=cardMapper.countLost();
	   return count;
   }
}
