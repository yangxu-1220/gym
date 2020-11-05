package com.gym.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.gym.model.CardEntityModel;
import com.gym.model.MemberModel;

/**
 * @jxn 2019/9/5
  * 接口类
 */
public interface MemberMapper {
	/**
	 * 多表联查会员信息
	  * 返回值：List<MemberModel>
	 */
	@Select("SELECT tb_member.*,tb_card_entity.card_entity_id as card_id,tb_stuff.stuff_name as member_stuff,tb_trainer.trainer_name as member_trainer " + 
			"FROM tb_member,tb_stuff,tb_trainer,tb_member_card,tb_card_entity " + 
			"WHERE tb_member.member_stuff_id=tb_stuff.stuff_id " + 
			"and tb_member.member_trainer_id=tb_trainer.trainer_id " +
			"and tb_member.member_id=tb_member_card.member_id " +
			"and tb_card_entity.card_entity_id=tb_member_card.card_entity_id")
	public List<MemberModel> getAllMember();
	
	/**
	 * 根据会员id，姓名，电话查询会员信息
	  * 返回值：List<MemberModel>
	 */
	@Select("SELECT * from " + 
			"(SELECT tb_member.*,"+
			"tb_card_entity.card_entity_create_time as card_create_time," +
			"tb_card_entity.card_entity_due_time as card_due_time " + 
			"FROM tb_member,tb_card_entity,tb_member_card " + 
			"WHERE tb_member.member_id=tb_member_card.member_id and tb_member_card.card_entity_id=tb_card_entity.card_entity_id) as temp " + 
			"WHERE member_id=#{memberA} " + 
			"or member_name=#{memberA} " + 
			"or member_phone=#{membereA}")
	public MemberModel getMemberInfo(String memberA);
	
	/**
	 * 查询会员类型所对应的会员卡信息
	  * 返回值：List<MemberModel>
	 */
	@Select("SELECT DISTINCT tb_card.card_type,tb_card.card_time,tb_card.card_frequency,"
			+ "tb_card.card_point,tb_card.card_price FROM tb_member,"
				+ "tb_card WHERE tb_member.member_type=tb_card.card_type")
	public List<MemberModel> getMemberStuff();
	
	
	/**
	 * 添加会员
	 *  返回值 int新增记录数
	 */
	@Insert("INSERT INTO tb_member SET member_id=#{memberId},member_name=#{memberName},member_type=#{memberType},"
			+ "member_sex=#{memberSex},join_time=#{joinTime},due_time=null,member_birthday=#{memberBirthday},"
			+ "member_phone=#{memberPhone},payment=#{payment},idcard_type=#{idcardType},idcard_number=#{idcardNumber},"
			+ "member_address=#{memberAddress},member_stuff_id=#{memberStuffId},member_trainer_id=#{memberTrainerId},"
			+ "member_needs=#{memberNeeds},member_point=null,member_note=#{memberNote},member_image=#{memberImage}")
	public int InsertMember(MemberModel memberModel);
	
	/**
	 * 修改会员
	 *  返回值 int修改记录数
	 */
	@Update("UPDATE tb_member SET member_name=#{memberName},member_type=#{memberType},member_sex=#{memberSex},"
			+ "due_time=#{dueTime},member_birthday=#{memberBirthday}, member_phone=#{memberPhone},payment=#{payment},idcard_type=#{idcardType},"
			+ "idcard_number=#{idcardNumber},member_address=#{memberAddress},member_stuff_id=#{memberStuffId},member_trainer_id=#{memberTrainerId},"
			+ "member_needs=#{memberNeeds},member_point=#{memberPoint},member_note=#{memberNote} where member_id=#{memberId}")
	public int UpdateMember(MemberModel memberModel);
	/**
	 * 删除会员
	 *  返回值 int 删除记录数
	 */
	@Delete("DELETE FROM tb_member WHERE member_id=#{memberId}")
	public int DeleteMemberById(int id);
	
	
	/**
	 * 转卡
	 * 返回转卡的Model
	 */
	@Update("UPDATE tb_member SET member_name=#{memberName},member_sex=#{memberSex},member_birthday=#{memberBirthday}, member_phone=#{memberPhone},idcard_type=#{idcardType}," 
	 +"idcard_number=#{idcardNumber},member_address=#{memberAddress},member_needs=#{memberNeeds},member_note=#{memberNote},member_image=#{memberImage} WHERE member_id=#{memberId}")
	public int MemberTrans(MemberModel memberModel);
	
	/**
	 * 升级
	 *  返回升级的Model
	 */
	@Select("SELECT member_type FROM tb_member WHERE member_id=#{memberId}")
	public String getMemberUpgrade(int id);
	
	@Update("UPDATE tb_member SET member_type=#{memberType} WHERE member_id=#{memberId}")
	public int MemberUpgrade(MemberModel memberModel);
	
	/**
	 * 补卡
	 *  返回补卡的Model
	 *  @param
	 *  mapper中自定义参数的方法，参数名和sql语句中的一样
	 */
	@Update("UPDATE tb_member SET member_id=#{memberId},member_phone=#{memberPhone},member_note=#{memberNote} WHERE member_id=#{id}")
	public int MemberIssue(@Param("id") Integer id,@Param("memberId") Integer memberId,@Param("memberPhone") String memberPhone,@Param("memberNote") String memberNote);

	/**
	 * 查询member表中有多少条记录
	 */
	@Select("SELECT count(1) FROM tb_member")
	public int getMemberTotal();
	
	/**
	 * 根据ID去查询对应的member信息
	 */
	@Select("SELECT tb_member.*,tb_user.user_password," +
			"tb_card_entity.card_entity_id as card_id," +
			"tb_card_entity.card_entity_state as state "+
			"FROM tb_member,tb_user ,tb_card_entity,tb_member_card " + 
			"WHERE tb_user.user_name=tb_member.member_phone " + 
			"and tb_member_card.member_id=tb_member.member_id " + 
			"and tb_member_card.card_entity_id=tb_card_entity.card_entity_id " + 
			"and tb_member.member_id=#{memberId}")
	public MemberModel getMemberById(@Param("memberId") int id);
	
	/**
	 * 实现分页的查询
	 * 根据传入的pageStart和pageSize通过limit来查询从pageStart开始，查pageSize条数据
	 */
	@Select("SELECT tb_member.*,tb_card_entity.card_entity_id as card_id,tb_stuff.stuff_name as member_stuff,tb_trainer.trainer_name as member_trainer " + 
			"FROM tb_member,tb_stuff,tb_trainer,tb_member_card,tb_card_entity " + 
			"WHERE tb_member.member_stuff_id=tb_stuff.stuff_id " +
			"and tb_member.member_trainer_id=tb_trainer.trainer_id " +
			"and tb_member.member_id=tb_member_card.member_id " +
			"and tb_card_entity.card_entity_id=tb_member_card.card_entity_id " +
			"limit #{pageStart},#{pageSize}")
	public List<MemberModel> getMemberBtween(@Param("pageStart") int pageStart,@Param("pageSize") int pageSize);
	
	
	/**
	 * 会员登录后查询个人信息 2019/10/13
	 * @param id
	 * @return
	 */
	@Select("SELECT tb_member.*, " + 
			"			tb_card_entity.card_entity_create_time as card_create_time," + 
			"			tb_card_entity.card_entity_due_time as card_due_time," +
			"           tb_card_entity.card_entity_state as state " + 
			"			FROM tb_member,tb_user,tb_card_entity,tb_member_card " + 
			"			WHERE tb_member.member_phone=tb_user.user_name " + 
			"			and tb_member.member_id=tb_member_card.member_id " + 
			"			and tb_member_card.card_entity_id=tb_card_entity.card_entity_id " + 
			"			and tb_member.member_id=#{memberId}")
	public MemberModel getUserByUserName(int memberId);
	
	@Select("SELECT tb_member.*, " + 
			"			tb_card_entity.card_entity_create_time as card_create_time," + 
			"			tb_card_entity.card_entity_due_time as card_due_time," +
			"           tb_card_entity.card_entity_state as state, " + 
			"           tb_card_entity.card_entity_id as card_id " + 
			"			FROM tb_member,tb_card_entity,tb_member_card " + 
			"			WHERE tb_member.member_id=tb_member_card.member_id " + 
			"			and tb_member_card.card_entity_id=tb_card_entity.card_entity_id " + 
			"			and tb_member.member_id=#{memberId}")
	public MemberModel getMemberToRenewal(int memberId);
	
	/**
	 * 修改user表密码
	 */
	@Update("UPDATE tb_user SET user_password=#{userPassword} WHERE user_name=#{memberPhone}")
	public void updatePasswordByPhone(MemberModel memberModel);
	
	@Update("UPDATE tb_user SET user_password=#{userPassword} WHERE user_name=#{memberPhone}")
	public void updatePasswordByPhone_2(@Param("userPassword") String userPassword,@Param("memberPhone") String memberPhone);
	
	@Select("SELECT * FROM tb_user,tb_member WHERE member_phone=user_name and member_id=#{memberId}")
	public MemberModel checkPassword(@Param("memberId") Integer memberId);
	/**
	 * 获取到期时间在当前时间之前的数据，也就是过期（禁用）的会员
	 */
	@Select("SELECT tb_member.*,tb_card_entity.card_entity_id as card_id,tb_stuff.stuff_name as member_stuff,tb_trainer.trainer_name as member_trainer " + 
			"			FROM tb_member,tb_stuff,tb_trainer,tb_member_card,tb_card_entity " + 
			"			WHERE tb_member.member_stuff_id=tb_stuff.stuff_id " + 
			"			and tb_member.member_trainer_id=tb_trainer.trainer_id " + 
			"			and tb_member.member_id=tb_member_card.member_id " + 
			"			and tb_card_entity.card_entity_id=tb_member_card.card_entity_id " + 
			"			and tb_card_entity.card_entity_state=\"到期\" " + 
			"			limit #{pageStart},#{pageSize}")
	public List<MemberModel> getOverTime(@Param("currTime") String currTime,@Param("pageStart") int pageStart,@Param("pageSize") int pageSize);
	/**
	 * 获取禁用会员的数量
	 * @param currTime
	 * @return
	 */
	@Select("SELECT count(1) FROM tb_card_entity WHERE tb_card_entity.card_entity_state=\"到期\"")
	public int countOverTime(@Param("currTime") String currTime);
	/**
	 * 获取可用会员的信息
	 * @param currTime
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@Select("SELECT tb_member.*,tb_card_entity.card_entity_id as card_id,tb_stuff.stuff_name as member_stuff,tb_trainer.trainer_name as member_trainer " + 
			"			FROM tb_member,tb_stuff,tb_trainer,tb_member_card,tb_card_entity " + 
			"			WHERE tb_member.member_stuff_id=tb_stuff.stuff_id " + 
			"			and tb_member.member_trainer_id=tb_trainer.trainer_id " + 
			"			and tb_member.member_id=tb_member_card.member_id " + 
			"			and tb_card_entity.card_entity_id=tb_member_card.card_entity_id " + 
			"			and tb_card_entity.card_entity_state=\"可用\" " + 
			"			limit #{pageStart},#{pageSize}")
	public List<MemberModel> getInTime(@Param("currTime") String currTime,@Param("pageStart") int pageStart,@Param("pageSize") int pageSize);
	/**
	 * 获取可用会员的数量
	 * @param currTime
	 * @return
	 */
	@Select("SELECT count(1) FROM tb_card_entity WHERE tb_card_entity.card_entity_state=\"可用\"")
	public int countInTime(@Param("currTime") String currTime);
	/**
	 * 根据卡ID修改会员卡状态为挂失
	 * @param cardId
	 */
	@Update("UPDATE tb_card_entity SET tb_card_entity.card_entity_state='挂失' WHERE tb_card_entity.card_entity_id=#{cardId}")
	public void reportLost(@Param("cardId") int cardId);
	/**
	 * 根据ID获取老卡的信息
	 * @param id
	 * @return
	 */
	@Select("SELECT card_entity_id as id,card_entity_type as type,card_entity_create_time as createTime,card_entity_due_time as dueTime,card_entity_state as state FROM tb_card_entity WHERE card_entity_id=#{id}")
	public CardEntityModel getCardEntityById(@Param("id") int id);
	/**
	 *补卡的时候创建一个新的会员卡对象
	 * @param cardEntityModel
	 */
	@Insert("INSERT INTO tb_card_entity VALUES(#{id},#{type},#{createTime},#{dueTime},#{state})")
	public void IssueCardCreate(CardEntityModel cardEntityModel);
	/**
	 * 修改新创建的卡的创建时间为当前
	 * @param joinTime
	 * @param id
	 */
	@Update("UPDATE tb_card_entity SET card_entity_create_time=#{joinTime} WHERE card_entity_id=#{id}")
	public void UpdateCreateTimeToNow(@Param("joinTime") String joinTime,@Param("id") int id);
	/**
	 * 修改会员办卡中的对应关系
	 * @param newCardId
	 * @param oldCardId
	 */
	@Update("UPDATE tb_member_card SET card_entity_id=#{newCardId} WHERE card_entity_id=#{oldCardId}")
	public void UpdateRelationOfMemberAndCard(@Param("newCardId") int newCardId,@Param("oldCardId") int oldCardId);
	
	/**
	 * 执行会员卡状态为可用状态的续费操作
	 * @param cardDueTime
	 * @param cardId
	 */
	@Update("UPDATE tb_card_entity SET tb_card_entity.card_entity_due_time=#{cardDueTime} WHERE tb_card_entity.card_entity_id=#{cardId}")
	public void DoRenewal_1(@Param("cardDueTime") String cardDueTime,@Param("cardId") Integer cardId);
	/**
	 * 执行会员卡状态为到期状态的续费操作
	 * @param cardDueTime
	 * @param cardCreateTime
	 * @param cardId
	 */
	@Update("UPDATE tb_card_entity " + 
			"SET tb_card_entity.card_entity_due_time=#{cardDueTime}," + 
			"	 tb_card_entity.card_entity_create_time=#{cardCreateTime}," + 
			"	 tb_card_entity.card_entity_state=\"可用\"" + 
			"WHERE tb_card_entity.card_entity_id=#{cardId}")
	public void DoRenewal_2(@Param("cardDueTime") String cardDueTime,@Param("cardCreateTime") String cardCreateTime,@Param("cardId") Integer cardId);
	/**
	 * 根据会员ID获取会员卡ID
	 * @param memberId
	 * @return
	 */
	@Select("SELECT tb_card_entity.card_entity_id " + 
			"FROM tb_card_entity,tb_member_card,tb_member " + 
			"WHERE tb_card_entity.card_entity_id=tb_member_card.card_entity_id " + 
			"AND tb_member.member_id=tb_member_card.member_id " + 
			"AND tb_member.member_id=#{memberId}")
	public int getCardIdByMemberId(@Param("memberId") Integer memberId);
	/**
	 * 根据卡ID修改卡的类型和卡的到期时间
	 * @param type
	 * @param dueTime
	 * @param id
	 */
	@Update("UPDATE tb_card_entity " + 
			"SET tb_card_entity.card_entity_type=#{type},tb_card_entity.card_entity_due_time=#{dueTime} " + 
			"WHERE tb_card_entity.card_entity_id=#{id}")
	public void updateCardEntity(@Param("type") String type,@Param("dueTime") String dueTime,@Param("id") Integer id);
}


