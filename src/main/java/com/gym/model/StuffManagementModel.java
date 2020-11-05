package com.gym.model;

public class StuffManagementModel {
	private int stuffId;
	private String stuffType;		//员工类型，与tb_user表中的user_perms相对应，需要在Service层中做相应的转换
	private String stuffName;
	private String stuffSex;
	private String stuffBirthday;
	private String idcardType;
	private String idcardNumber;
	private String stuffAddress;
	private String stuffPhone;
	private String joinTime;
	private String stuffNote;
	private String stuffImage;
	private String userPassword;		//用户密码
	public int getStuffId() {
		return stuffId;
	}
	public void setStuffId(int stuffId) {
		this.stuffId = stuffId;
	}
	public String getStuffType() {
		return stuffType;
	}
	public void setStuffType(String stuffType) {
		this.stuffType = stuffType;
	}
	public String getStuffName() {
		return stuffName;
	}
	public void setStuffName(String stuffName) {
		this.stuffName = stuffName;
	}
	public String getStuffSex() {
		return stuffSex;
	}
	public void setStuffSex(String stuffSex) {
		this.stuffSex = stuffSex;
	}
	public String getStuffBirthday() {
		return stuffBirthday;
	}
	public void setStuffBirthday(String stuffBirthday) {
		this.stuffBirthday = stuffBirthday;
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
	public String getStuffAddress() {
		return stuffAddress;
	}
	public void setStuffAddress(String stuffAddress) {
		this.stuffAddress = stuffAddress;
	}
	public String getStuffPhone() {
		return stuffPhone;
	}
	public void setStuffPhone(String stuffPhone) {
		this.stuffPhone = stuffPhone;
	}
	public String getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(String joinTime) {
		this.joinTime = joinTime;
	}
	public String getStuffNote() {
		return stuffNote;
	}
	public void setStuffNote(String stuffNote) {
		this.stuffNote = stuffNote;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getStuffImage() {
		return stuffImage;
	}
	public void setStuffImage(String stuffImage) {
		this.stuffImage = stuffImage;
	}
}
