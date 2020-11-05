package com.gym.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.gym.model.CardEntityModel;

@Repository
public interface CardEntityMapper {
	/**
	 * 根据ID查询对应的member记录
	 * @param memberId
	 * @return
	 */
	@Select("SELECT member_id as id,member_type as type,join_time as create_time,due_time "
			+ "FROM tb_member "
			+ "WHERE member_id=#{memberId}")
	public CardEntityModel getCardEntityById(@Param("memberId") int memberId);
	/**
	 * 向card_entity表中插入数据
	 * @param cardEntity
	 */
	@Insert("INSERT INTO tb_card_entity "
			+ "SET card_entity_id=#{id},"
			+ "card_entity_type=#{type},"
			+ "card_entity_create_time=#{createTime},"
			+ "card_entity_due_time=#{dueTime},"
			+ "card_entity_state=#{state}")
	public void insertCardEntity(CardEntityModel cardEntity);
	/**
	 * 向会员办卡表中插入数据
	 * @param memberId
	 * @param cardInt
	 */
	@Insert("INSERT INTO tb_member_card "
			+ "SET member_id=#{memberId},card_entity_id=#{cardId}")
	public void insertMemberHandleCard(@Param("memberId") int memberId,@Param("cardId") int cardId);
}
