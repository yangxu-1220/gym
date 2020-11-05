package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.gym.model.CardEntityModel;
import com.gym.model.CardModel;

 /**
  * 会员卡管理部分的接口类
  * @author jxn 2019/09/03 14:30
  */
@Repository
public interface CardMapper {
    /**
     * 查询数据库中会员卡信息
     * @return  List<CardModel>
     */
	@Select("SELECT * FROM tb_card")
	public List<CardModel> getAllCard();
	
	/**
	 * 根据卡id，类型，查询会员卡信息
	  * 返回值：CardModel
	 */
	@Select("SELECT * FROM tb_card WHERE card_id=#{cardA} or card_type=#{cardA}")
	public CardModel getCardInfo(@Param("cardA") String cardA);
	
	@Select("SELECT * FROM tb_card")
	public List<CardModel> getAllCards();
	/**
	 * 查询tb_card表中有多少条记录 添加于2019年10月4日(分页)
	 */
	@Select("SELECT count(1) FROM tb_card")
	public int getCardTotal();
	/**
	 * 根据id查询数据库中会员卡信息
	 * @param id
	 * @return CardModel
	 */
	@Select("SELECT * FROM tb_card WHERE card_id=#{cardId}")
	public CardModel getCardById(int id);
	 
	 /**
	  * 实现分页的查询  添加于2019年10月4日
	  * @param pageStart,pageSize 通过limit来查询从pageStart开始查pageSize条数据
	  * @return 
	  */
	@Select("SELECT * FROM tb_card limit #{pageStart},#{pageSize}")
	public List<CardModel> getCardBtween(@Param("pageStart") int pageStart,
			@Param("pageSize") int pageSize);
	/**
	 * 添加会员卡信息 
	 * @param cardModel
	 * @return
	 */
	@Insert("INSERT INTO tb_card set card_id=#{cardId},card_type=#{cardType},"
			+ "card_time=#{cardTime},card_frequency=#{cardFrequency},card_point=#{cardPoint},"
			+ "card_price=#{cardPrice}")
	public int InsertCard(CardModel cardModel);
	/**
	 * 修改会员卡信息
	 * @param cardModel
	 * @return int修改的记录数
	 */
	@Update("UPDATE tb_card SET card_type=#{cardType},card_time=#{cardTime},"
			+ "card_frequency=#{cardFrequency},card_point=#{cardPoint},card_price=#{cardPrice}"
			+ " where card_id=#{cardId}")
	public int UpdateCard(CardModel cardModel);
	/**
	 * 删除会员卡信息
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM tb_card WHERE card_id=#{cardId}")
	public int DeleteCardInfo(int id);
	
	/**
	 * 获取会员卡的分页
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("SELECT tb_card_entity.card_entity_id as id," + 
			"tb_card_entity.card_entity_type as type," + 
			"tb_card_entity.card_entity_create_time as create_time," + 
			"tb_card_entity.card_entity_due_time as due_time," + 
			"tb_card_entity.card_entity_state as state " + 
			"FROM tb_card_entity " + 
			"LIMIT #{pageStart},#{pageSize}")
	public List<CardEntityModel> getCardEntityBtween(@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);
	/**
	 * 获取卡数量
	 * @return
	 */
	@Select("SELECT count(*) FROM tb_card_entity")
	public int countCardEntity();
	
	@Select("SELECT tb_card_entity.card_entity_id as id," + 
			"tb_card_entity.card_entity_type as type," + 
			"tb_card_entity.card_entity_create_time as create_time," + 
			"tb_card_entity.card_entity_due_time as due_time," + 
			"tb_card_entity.card_entity_state as state " + 
			"FROM tb_card_entity " + 
			"WHERE tb_card_entity.card_entity_state='可用' " + 
			"LIMIT #{pageStart},#{pageSize}")
	public List<CardEntityModel> getAvailable(@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);
	
	@Select("SELECT count(*) FROM tb_card_entity WHERE tb_card_entity.card_entity_state='可用' ")
	public int countAvailable();
	
	@Select("SELECT tb_card_entity.card_entity_id as id," + 
			"tb_card_entity.card_entity_type as type," + 
			"tb_card_entity.card_entity_create_time as create_time," + 
			"tb_card_entity.card_entity_due_time as due_time," + 
			"tb_card_entity.card_entity_state as state " + 
			"FROM tb_card_entity " + 
			"WHERE tb_card_entity.card_entity_state='到期' " + 
			"LIMIT #{pageStart},#{pageSize}")
	public List<CardEntityModel> getUnAvailable(@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);
	
	@Select("SELECT count(*) FROM tb_card_entity WHERE tb_card_entity.card_entity_state='到期' ")
	public int countUnAvailable();
	
	@Select("SELECT tb_card_entity.card_entity_id as id," + 
			"tb_card_entity.card_entity_type as type," + 
			"tb_card_entity.card_entity_create_time as create_time," + 
			"tb_card_entity.card_entity_due_time as due_time," + 
			"tb_card_entity.card_entity_state as state " + 
			"FROM tb_card_entity " + 
			"WHERE tb_card_entity.card_entity_state='挂失' " + 
			"LIMIT #{pageStart},#{pageSize}")
	public List<CardEntityModel> getLost(@Param("pageStart") Integer pageStart,@Param("pageSize") Integer pageSize);
	
	@Select("SELECT count(*) FROM tb_card_entity WHERE tb_card_entity.card_entity_state='挂失' ")
	public int countLost();
}
