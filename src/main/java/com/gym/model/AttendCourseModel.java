package com.gym.model;
/**
 * 上课表（免费课程）实体对象
 * @author 姬西南2019/10/11/ 14：23
 *
 */
public class AttendCourseModel {
  private int  attendCourseId;//上课的课程Id
  private int trainerId;  //教练Id,外键查询教练信息
  private String trainerName; //教练名称#
  private  String courseName; //课程名#
  private String courseLength;//课程时长#
  private int courseNumber;   //上课人数#
  private String attendCourseTime;//上课时间（修改前的时间）
  private String attendCourseTime2;//修改后的时间
  private String attendCoursePalce;//上课地点 
  private String attendCourseState;//课程状态
  private String courseImage; //上课图片#
  private int attendStock;	  //剩余量
public int getAttendCourseId() {
	return attendCourseId;
}
public void setAttendCourseId(int attendCourseId) {
	this.attendCourseId = attendCourseId;
}
public int getTrainerId() {
	return trainerId;
}
public void setTrainerId(int trainerId) {
	this.trainerId = trainerId;
}
public String getTrainerName() {
	return trainerName;
}
public void setTrainerName(String trainerName) {
	this.trainerName = trainerName;
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
public String getAttendCourseState() {
	return attendCourseState;
}
public void setAttendCourseState(String attendCourseState) {
	this.attendCourseState = attendCourseState;
}
public String getAttendCourseTime2() {
	return attendCourseTime2;
}
public void setAttendCourseTime2(String attendCourseTime2) {
	this.attendCourseTime2 = attendCourseTime2;
}
public String getCourseImage() {
	return courseImage;
}
public void setCourseImage(String courseImage) {
	this.courseImage = courseImage;
}
public int getAttendStock() {
	return attendStock;
}
public void setAttendStock(int attendStock) {
	this.attendStock = attendStock;
}

}
