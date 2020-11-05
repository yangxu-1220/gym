package com.gym.model;
/**
 * 课程实体对象
 * @author jxn 2019/08/28 
 */
public class CourseModel {

	private int courseId;  //课程ID
	private String courseName;  //课程名
	private String courseLength;  //课程时长
	private String courseContent;  //课程内容
	private String courseImage;   //课程封面
	private int courseNumber;   //最大容纳数量
	/*
	 * get和set方法
	 */
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
	public String getCourseContent() {
		return courseContent;
	}
	public void setCourseContent(String courseContent) {
		this.courseContent = courseContent;
	}
	public int getCourseNumber() {
		return courseNumber;
	}
	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}
	
}
