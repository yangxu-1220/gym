package com.gym.model;
/**
 * 会员卡实体对象
 * @author jxn 2019/08/27 
 */
public class CardModel {

	private int cardId; //会员卡id
	private String cardType;  //会员卡类型
	private int cardTime;  //会员卡可用时间
	private int cardFrequency;  //会员卡有效次数
	private int cardPoint;  //会员卡积分
	private float cardPrice;  //会员卡价格
	/*
	 * 构造空方法
	 */
	public CardModel() {
		
	}
	/*
	 * get和 set方法
	 */
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public int getCardTime() {
		return cardTime;
	}
	public void setCardTime(int cardTime) {
		this.cardTime = cardTime;
	}
	public int getCardFrequency() {
		return cardFrequency;
	}
	public void setCardFrequency(int cardFrequency) {
		this.cardFrequency = cardFrequency;
	}
	public int getCardPoint() {
		return cardPoint;
	}
	public void setCardPoint(int cardPoint) {
		this.cardPoint = cardPoint;
	}
	public float getCardPrice() {
		return cardPrice;
	}
	public void setCardPrice(float cardPrice) {
		this.cardPrice = cardPrice;
	}
	
	
}
