package com.gym.model;

public class OrderCourseChartModel {
	private int courseId;
	private String courseName;
	private int orderedNumber;		//已预约人数
	private int maxOrderedNumber;	//最大预约人数
	private String memberName;
	private String trainerName;
	private String courseTime;
	private String coursePlace;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getOrderedNumber() {
		return orderedNumber;
	}
	public void setOrderedNumber(int orderedNumber) {
		this.orderedNumber = orderedNumber;
	}
	public int getMaxOrderedNumber() {
		return maxOrderedNumber;
	}
	public void setMaxOrderedNumber(int maxOrderedNumber) {
		this.maxOrderedNumber = maxOrderedNumber;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public String getCoursePlace() {
		return coursePlace;
	}
	public void setCoursePlace(String coursePlace) {
		this.coursePlace = coursePlace;
	}
}
