package com.gym.model;
/**
 * 会员实体对象
 * @author jxn 2019/09/07 
 */
public class MemberModel {
    
	//private int Id;
	private Integer memberId;  //会员编号
	private Integer cardId;	   //会员卡编号
	private String state;     //会员卡状态
	private String memberName;  //会员姓名
	private String memberType;  //会员类型
	private String memberSex;  //会员性别
	private String joinTime;  //加入时间
	private String dueTime;  //到期时间
	private String memberBirthday;  //出生日期
	private String memberPhone;  //联系电话
	private String payment;  //支付方式
	private String idcardType;  //证件类型
	private String idcardNumber;  //证件编号
	private String memberAddress;  //地址
	private String memberStuff;  //会籍顾问
	private String memberTrainer;  //健身教练
	private String memberNeeds;  //健身需求
	private int memberPoint;  //会员积分
	private String memberNote;  //备注
	private String memberImage;  //照片
	private int memberStuffId;
	private int memberTrainerId;
	private String userPassword;
	private String cardCreateTime;
	private String cardDueTime;
//	public int getId() {
//		return Id;
//	}
//	public void setId(int id) {
//		Id = id;
//	}
	//private int pageTotal;
	/*
	 * get和set方法
	 */
	
	public int getMemberId() {
		return memberId;
	}
	public String getCardCreateTime() {
		return cardCreateTime;
	}
	public void setCardCreateTime(String cardCreateTime) {
		this.cardCreateTime = cardCreateTime;
	}
	public String getCardDueTime() {
		return cardDueTime;
	}
	public void setCardDueTime(String cardDueTime) {
		this.cardDueTime = cardDueTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMemberSex() {
		return memberSex;
	}
	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}
	public String getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}
	public String getDueTime() {
		return dueTime;
	}
	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}
	public String getMemberBirthday() {
		return memberBirthday;
	}
	public void setMemberBirthday(String memberBirthday) {
		this.memberBirthday = memberBirthday;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getIdcardType() {
		return idcardType;
	}
	public void setIdcardType(String idcardType) {
		this.idcardType = idcardType;
	}
	public String getIdcardNumber() {
		return idcardNumber;
	}
	public void setIdcardNumber(String idcardNumber) {
		this.idcardNumber = idcardNumber;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberStuff() {
		return memberStuff;
	}
	public void setMemberStuff(String memberStuff) {
		this.memberStuff = memberStuff;
	}
	public String getMemberTrainer() {
		return memberTrainer;
	}
	public void setMemberTrainer(String memberTrainer) {
		this.memberTrainer = memberTrainer;
	}
	public String getMemberNeeds() {
		return memberNeeds;
	}
	public void setMemberNeeds(String memberNeeds) {
		this.memberNeeds = memberNeeds;
	}
	public int getMemberPoint() {
		return memberPoint;
	}
	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}
	public String getMemberNote() {
		return memberNote;
	}
	public void setMemberNote(String memberNote) {
		this.memberNote = memberNote;
	}
	public String getMemberImage() {
		return memberImage;
	}
	public void setMemberImage(String memberImage) {
		this.memberImage = memberImage;
	}
	public int getMemberStuffId() {
		return memberStuffId;
	}
	public void setMemberStuffId(int memberStuffId) {
		this.memberStuffId = memberStuffId;
	}
	public int getMemberTrainerId() {
		return memberTrainerId;
	}
	public void setMemberTrainerId(int memberTrainerId) {
		this.memberTrainerId = memberTrainerId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
