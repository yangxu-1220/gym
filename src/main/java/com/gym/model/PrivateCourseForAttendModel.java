package com.gym.model;

public class PrivateCourseForAttendModel {
	private int privateCourseId;
	private String privateCourseName;
	private int trainerId;
	private String trainerName;
	private int memberId;
	private String memberName;
	private String attendPrivateTime;
	private String attendPrivatePlace;
	public int getPrivateCourseId() {
		return privateCourseId;
	}
	public void setPrivateCourseId(int privateCourseId) {
		this.privateCourseId = privateCourseId;
	}
	public String getPrivateCourseName() {
		return privateCourseName;
	}
	public void setPrivateCourseName(String privateCourseName) {
		this.privateCourseName = privateCourseName;
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
	public int getMemberId() {
		return memberId;
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
	public String getAttendPrivateTime() {
		return attendPrivateTime;
	}
	public void setAttendPrivateTime(String attendPrivateTime) {
		this.attendPrivateTime = attendPrivateTime;
	}
	public String getAttendPrivatePlace() {
		return attendPrivatePlace;
	}
	public void setAttendPrivatePlace(String attendPrivatePlace) {
		this.attendPrivatePlace = attendPrivatePlace;
	}
}
