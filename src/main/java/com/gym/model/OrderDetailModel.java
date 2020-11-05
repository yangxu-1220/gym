package com.gym.model;

public class OrderDetailModel {
	
	private int goodsId;
	
	private String orderId;
	
	private String goodsName;
	
	private int goodsNumber;
	
	private float goodsPrice;
	
	private float totalPrice;
	
	private int goodsStockNumber;

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(int goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getGoodsStockNumber() {
		return goodsStockNumber;
	}

	public void setGoodsStockNumber(int goodsStockNumber) {
		this.goodsStockNumber = goodsStockNumber;
	}
}
