package com.gym.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.gym.model.VisitModel;

@Repository
public interface VisitMapper {
	
	/**
	 * 会员来访 输入会员卡号 查询会员信息
	 * @param cardId
	 * @return
	 */
	@Select("SELECT tb_card_entity.card_entity_type as type,tb_member_card.card_entity_id as id,tb_member.member_image as image,tb_member.member_name as name,tb_card_entity.card_entity_create_time as joinTime,tb_card_entity.card_entity_due_time as dueTime,tb_card_entity.card_entity_state as state " + 
			"FROM tb_member,tb_card_entity,tb_member_card " + 
			"WHERE tb_member.member_id=tb_member_card.member_id " + 
			"and tb_card_entity.card_entity_id=tb_member_card.card_entity_id " + 
			"and tb_card_entity.card_entity_id=#{cardId}")
	public VisitModel visitById(@Param("cardId") int cardId);
	/**
	 * 向会员来访表中插入一条新的来访记录
	 * @param visitModel
	 */
	@Insert("INSERT INTO tb_member_visit VALUES(null,#{id},#{visitTime})")
	public void onceVisit(@Param("id") int id,@Param("visitTime") String visitTime);
	/**
	 * 统计该会员的来访次数
	 * @param id
	 * @return
	 */
	@Select("SELECT count(1) FROM tb_member_visit WHERE tb_member_visit.member_id=#{id}")
	public int countVisit(@Param("id") int id);
	/**
	 * 根据卡ID获得会员ID
	 * @param id
	 * @return
	 */
	@Select("SELECT tb_member_card.member_id FROM tb_member_card WHERE tb_member_card.card_entity_id=#{id}")
	public int getMemberIdByCardId(@Param("id") Integer id);
}