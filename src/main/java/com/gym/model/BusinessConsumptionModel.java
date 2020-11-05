package com.gym.model;

public class BusinessConsumptionModel {
	private int saleId;
	private String goodsName;
	private String saleTime;
	private float singlePrice;
	private float price;
	private int number;
	private String stuff;
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getStuff() {
		return stuff;
	}
	public void setStuff(String stuff) {
		this.stuff = stuff;
	}
	public float getSinglePrice() {
		return singlePrice;
	}
	public void setSinglePrice(float singlePrice) {
		this.singlePrice = singlePrice;
	}
}
