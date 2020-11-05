package com.gym.model;
/**
 * 根据会员中的教练查询教练信息的实体对象
 * @author jxn 2019/09/08 
 */
public class MTrainerModel {
	
	private int trainerId;
	private String trainerName;
	private String trainerSex;
	private String trainerBirthday;
	private String idCardType;
	private String idCardNumber;
	private String trainerPhone;
	private String jionTime;
	private String trainerNote;
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

	public String getTrainerSex() {
		return trainerSex;
	}

	public void setTrainerSex(String trainerSex) {
		this.trainerSex = trainerSex;
	}

	public String getTrainerBirthday() {
		return trainerBirthday;
	}

	public void setTrainerBirthday(String trainerBirthday) {
		this.trainerBirthday = trainerBirthday;
	}

	public String getIdCardType() {
		return idCardType;
	}

	public void setIdCardType(String idCardType) {
		this.idCardType = idCardType;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getTrainerPhone() {
		return trainerPhone;
	}

	public void setTrainerPhone(String trainerPhone) {
		this.trainerPhone = trainerPhone;
	}

	public String getJionTime() {
		return jionTime;
	}

	public void setJionTime(String jionTime) {
		this.jionTime = jionTime;
	}

	public String getTrainerNote() {
		return trainerNote;
	}

	public void setTrainerNote(String trainerNote) {
		this.trainerNote = trainerNote;
	}

}
