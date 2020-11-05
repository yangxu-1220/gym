package com.gym.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import com.gym.config.SHA;
import com.gym.mapper.CardEntityMapper;
import com.gym.mapper.MemberMapper;
import com.gym.model.CardEntityModel;
import com.gym.model.MemberModel;


@Service
public class MemberService {

	@Autowired
	MemberMapper memberMapper;
	@Autowired
	CardEntityMapper cardEntityMapper;
	
	/**
	  * 查询所有的会员信息
	 */
	public List<MemberModel> getAllMember(){
	 	return memberMapper.getAllMember();
	}
	
	/**
	 * 根据id去查询对应的member信息
	 */
	public MemberModel getMemberById(int id) {
		MemberModel memberModel_B=memberMapper.getMemberById(id);
		return memberModel_B;
	}
	
	/**
	 * 给续费页面拿初值
	 * @param id
	 * @return
	 */
	public MemberModel getMemberToRenewal(int id) {
		MemberModel memberModel_R=memberMapper.getMemberToRenewal(id);
		return memberModel_R;
	}
	
	/**
	  * 将根据id,姓名或电话号码查询会员信息，将信息存为member对象
	 */
    public MemberModel getMemberInfo(String memberA) {
    	MemberModel member=memberMapper.getMemberInfo(memberA);
		return member;		
	}
    
    /**
   	 * 新增会员
   	 */
    public boolean InsertMember(MemberModel memberModel) {
    	SHA sHA=new SHA();
    	int memberId=memberModel.getMemberId();
    	int cardId=memberId;
    	System.out.println("新增未加密密码"+memberModel.getUserPassword());
    	memberModel.setUserPassword(sHA.getResult(memberModel.getMemberPhone()+memberModel.getUserPassword()));
    	System.out.println("新增已加密密码"+memberModel.getUserPassword());
    	//System.out.println(memberModel.getMemberId());
    	//执行会员插入操作
		int count=memberMapper.InsertMember(memberModel);
		memberMapper.updatePasswordByPhone(memberModel);
		//向card_entity表和会员办卡中插入数据
    	CardEntityModel cardEntityModel=cardEntityMapper.getCardEntityById(memberModel.getMemberId());
    	cardEntityModel.setState("可用");
    	cardEntityMapper.insertCardEntity(cardEntityModel);
    	cardEntityMapper.insertMemberHandleCard(memberId, cardId);
		if(count>0)
			return true;
		return false;
	}
    
    /**
   	  * 修改会员
   	 */
	public boolean UpdateMember(MemberModel memberModel) {
		SHA sHA=new SHA();
		MemberModel checkPassword=memberMapper.checkPassword(memberModel.getMemberId());
		System.out.println("数据库查询的密码："+checkPassword.getUserPassword());
		String pass=sHA.getResult(memberModel.getMemberPhone()+memberModel.getUserPassword());
		if(checkPassword.getUserPassword().equals(memberModel.getUserPassword())) {
			memberModel.setUserPassword(checkPassword.getUserPassword());
			System.out.println("进入if"+memberModel.getUserPassword());
		}
		else {
			memberModel.setUserPassword(pass);
			System.out.println("进入else"+memberModel.getUserPassword());
		}
		int count=memberMapper.UpdateMember(memberModel);
		memberMapper.updatePasswordByPhone(memberModel);
		if(count>0)
			return true;
		return false;
		
	}
	
    /**
   	  * 删除会员
   	 */
	public boolean DeleteMemberById(int id) {
		int count=memberMapper.DeleteMemberById(id);
		if(count>0)
			return true;
		return false;
		
	} 
	
	/**
  	  * 会员转卡
  	 */
	public boolean MemberTrans(MemberModel memberModel) {
		SHA sHA=new SHA();
    	System.out.println("新增未加密密码"+memberModel.getUserPassword());
    	memberModel.setUserPassword(sHA.getResult(memberModel.getMemberPhone()+memberModel.getUserPassword()));
    	System.out.println("新增已加密密码"+memberModel.getUserPassword());
		int count=memberMapper.MemberTrans(memberModel);
		memberMapper.updatePasswordByPhone(memberModel);
		if(count>0)
			return true;
		return false;
		
	}
	
    /**
   	  * 会员升级，卡类型监测
   	 */
	public List<String> getMemberUpgrade(int id){	//对比会员卡类型
		List<String> MemberTypeList=new ArrayList<String>();
		String memberType=memberMapper.getMemberUpgrade(id);
		//System.out.println(memberType);
		if("周卡".equals(memberType)) {
			MemberTypeList.add("月卡");
			MemberTypeList.add("季卡");
			MemberTypeList.add("半年卡");
			MemberTypeList.add("年卡");
		}else if("月卡".equals(memberType)) {
			MemberTypeList.add("季卡");
			MemberTypeList.add("半年卡");
			MemberTypeList.add("年卡");
		}else if("季卡".equals(memberType)) {
			MemberTypeList.add("半年卡");
			MemberTypeList.add("年卡");
		}else if("半年卡".equals(memberType)) {
			MemberTypeList.add("年卡");
		}else if("年卡".equals(memberType)) {
			MemberTypeList.add("黑卡");
		}else {
			MemberTypeList=null;
		}
		return MemberTypeList;
	}
	 /**
  	  * 会员升级
  	 */
	public boolean MemberUpgrade(MemberModel memberModel) throws java.text.ParseException {
		String type=memberModel.getMemberType();
		String createTime=memberModel.getJoinTime();
		int id=memberModel.getCardId();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		try {
			date=sdf.parse(createTime);
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		if("月卡".equals(type)) {
			calendar.add(Calendar.MONTH,+1);
		}
		else if("季卡".equals(type)) {
			calendar.add(Calendar.MONTH,+3);
		}
		else if("半年卡".equals(type)) {
			calendar.add(Calendar.MONTH,+6);
		}
		else if("年卡".equals(type)) {
			calendar.add(Calendar.YEAR,+1);
		}
		else if("黑卡".equals(type)) {
			calendar.add(Calendar.DAY_OF_MONTH,+1000);
		}
		String dueTime=sdf.format(calendar.getTime());
		int count=memberMapper.MemberUpgrade(memberModel);
		memberMapper.updateCardEntity(type, dueTime, id);
		if(count>0)
			return true;
		return false;
		
	}
	
//	/**
//  	  * 会员补卡
//  	 */
//   public boolean MemberIssue(Integer id,Integer memberId,String memberPhone,String memberNote,String userPassword) {
//	   SHA sHA=new SHA();
//	   System.out.println("新增未加密密码"+userPassword);
//	   String pass=sHA.getResult(memberPhone+userPassword);
//	   System.out.println("新增已加密密码"+pass);
//	   int count=memberMapper.MemberIssue(id,memberId,memberPhone,memberNote);
//       memberMapper.updatePasswordByPhone_2(pass, memberPhone);
//		if(count>0)
//			return true;
//		return false;
//	}
	public void MemberIssue(Integer oldCardId,Integer newCardId) {
		System.out.println("老卡ID："+oldCardId);
		System.out.println("新卡ID："+newCardId);
		CardEntityModel cardEntityModel=new CardEntityModel();//创建一个会员卡对象
		cardEntityModel=memberMapper.getCardEntityById(oldCardId);//根据老卡ID查询老卡信息
		System.out.println("老卡ID："+cardEntityModel.getId());
		cardEntityModel.setId(newCardId);//把老卡信息和新卡id作为一条新的会员卡信息创建
		cardEntityModel.setState("可用");
		memberMapper.IssueCardCreate(cardEntityModel);//执行创建新卡对象
//		Date currentDate = new Date();//实例化一个时间对象
//		SimpleDateFormat nowTime = new SimpleDateFormat("yyyyMMddHHmmss");//设置简单时间格式
        String createTime = cardEntityModel.getCreateTime();//把时间对象转化为字符串
        memberMapper.UpdateCreateTimeToNow(createTime, newCardId);//修改新增加卡的创建时间为现在
        memberMapper.UpdateRelationOfMemberAndCard(newCardId, oldCardId);//修改会员办卡表中的对应关系
	}
	/**
	 * 动态渲染table和分页
	 */
	public List<MemberModel> getMemberBtween(int pageStart,int pageSize){
		List<MemberModel> memberModel_A=memberMapper.getMemberBtween(pageStart, pageSize);
		return memberModel_A;		
	}
	
	/**
	 * 获取member表中数据的总数
	 */
	public int getMemberTotal() {
		int rowSize=memberMapper.getMemberTotal();
		return rowSize;
	}
	
	/**
	 * 会员登录后查询个人信息 2019/10/13
	 * @param id
	 * @return
	 */
	public MemberModel getUserBy(int memberId) {
		MemberModel user=memberMapper.getUserByUserName(memberId);
		return user;
	}
	//获取禁用会员
	public List<MemberModel> GetOverTime(String currTime,Integer pageStart,Integer pageSize){
		List<MemberModel> overTime=memberMapper.getOverTime(currTime, pageStart, pageSize);
		return overTime;
	}
	//统计禁用会员的数量
	public int CountOverTime(String currTime) {
		int count_overTime=memberMapper.countOverTime(currTime);
		return count_overTime;
	}
	//获取可用会员
	public List<MemberModel> GetInTime(String currTime,Integer pageStart,Integer pageSize){
		List<MemberModel> inTime=memberMapper.getInTime(currTime, pageStart, pageSize);
		return inTime;
	}
	//统计可用会员的数量
	public int CountInTime(String currTime) {
		int count_inTime=memberMapper.countInTime(currTime);
		return count_inTime;
	}
	//挂失卡
	public void ReportLost(Integer cardId) {
		memberMapper.reportLost(cardId);
	}
	//获取老卡信息
	public CardEntityModel GetCardEntityById(Integer id) {
		CardEntityModel cardE=memberMapper.getCardEntityById(id);
		return cardE;
	}
	
	//会员续费
	public void DoRenewal(Integer cardId,Integer number,String timeUnit) throws java.text.ParseException {
		CardEntityModel cardE=memberMapper.getCardEntityById(cardId);//获取老卡对象
		if("可用".equals(cardE.getState())) {
			String cardDueTime=cardE.getDueTime();//取得老卡的时间
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date_1=new Date();
			try {
				date_1=sdf.parse(cardDueTime);//到期时间
			}
			catch(ParseException e){
				e.printStackTrace();
			}
			Calendar calendar=Calendar.getInstance();//得到日历
			calendar.setTime(date_1);
			if("天".equals(timeUnit)) {//根据所选的时间单位算出到期时间
				calendar.add(Calendar.DAY_OF_MONTH,+number);
			}
			else if("月".equals(timeUnit)) {
				calendar.add(Calendar.MONTH,+number);
			}
			else if("年".equals(timeUnit)) {
				calendar.add(Calendar.YEAR,+number);
			}
			String date_1_2=sdf.format(calendar.getTime());
			memberMapper.DoRenewal_1(date_1_2, cardId);
		}
		if("到期".equals(cardE.getState())) {//对于到期的会员卡而言 不仅要修改到期时间，开卡时间需要修改为当前时间
			Date currentDate = new Date();
			SimpleDateFormat nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date_2_1 = nowTime.format(currentDate);
			Calendar calendar=Calendar.getInstance();//得到日历
			calendar.setTime(currentDate);
			if("天".equals(timeUnit)) {//根据所选的时间单位算出到期时间
				calendar.add(Calendar.DAY_OF_MONTH,+number);
			}
			else if("月".equals(timeUnit)) {
				calendar.add(Calendar.MONTH,+number);
			}
			else if("年".equals(timeUnit)) {
				calendar.add(Calendar.YEAR,+number);
			}
			String date_2_2=nowTime.format(calendar.getTime());
			memberMapper.DoRenewal_2(date_2_2, date_2_1, cardId);
		}
	}
	
	//根据会员ID获取会员卡ID
	public int GetCardIdByMemberId(Integer memberId) {
		int cardId=memberMapper.getCardIdByMemberId(memberId);
		return cardId;
	}
	
}
