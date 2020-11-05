package com.gym.model;
/**
 * 会员约课实体
 * @author jxn2019/10/17 14:54
 *
 */
public class OrderCourseModel {

	private int courseId;   //数据库课程Id
	private int memberId;    //数据库会员Id
	private int trainerId;    //数据库教练Id
	private String courseName; //自定义从course表得到
	private String courseLength; //自定义从course表得到
	private int courseNumber;  //最大库存==course表容纳人数
	private String courseImage;  //自定义从course表得到
	private String trainerName; //自定义从上课表得到
	private String memberName; //自定义从会员表得到
	private String memberSex;
	private String memberPhone;
	private String memberAddress;
	private String attendCourseTime;  //自定义从上课表得到
	private String attendCoursePalce; //自定义从上课表得到
	private String orderTime;  //数据库预约时间
	private int attendStock;	//课程剩余量
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseLength() {
		return courseLength;
	}
	public void setCourseLength(String courseLength) {
		this.courseLength = courseLength;
	}
	public String getCourseImage() {
		return courseImage;
	}
	public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public int getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}
	public String getAttendCourseTime() {
		return attendCourseTime;
	}
	public void setAttendCourseTime(String attendCourseTime) {
		this.attendCourseTime = attendCourseTime;
	}
	public String getAttendCoursePalce() {
		return attendCoursePalce;
	}
	public void setAttendCoursePalce(String attendCoursePalce) {
		this.attendCoursePalce = attendCoursePalce;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public int getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getAttendStock() {
		return attendStock;
	}
	public void setAttendStock(int attendStock) {
		this.attendStock = attendStock;
	}
	public String getMemberSex() {
		return memberSex;
	}
	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	
}
