package com.gym.model;

public class StockOperateModel {

	private String operateStockId;//本次对库存操的ID
	
	private int stuffId;//执行这一次操作的员工ID
	
	private int goodsId;//这次操作所对应的商品ID
	
	private String goodsName;//商品名称
	
	private String stuffName;//员工名称
	
	private String operateStockState;//此次操作的类型
		
	private int operateStockNumber;//此次操作商品的数量
	
	//private int goodsStockNumber;
	
	private float operateStockPrice;//此次操作的总价
	
	private String operateStockTime;//这次操作的时间
	
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getStuffName() {
		return stuffName;
	}

	public void setStuffName(String stuffName) {
		this.stuffName = stuffName;
	}
	
	public int getOperateStockNumber() {
		return operateStockNumber;
	}

	public void setOperateStockNumber(int operateStockNumber) {
		this.operateStockNumber = operateStockNumber;
	}

	public String getOperateStockId() {
		return operateStockId;
	}

	public void setOperateStockId(String operateStockId) {
		this.operateStockId = operateStockId;
	}

	public int getStuffId() {
		return stuffId;
	}

	public void setStuffId(int stuffId) {
		this.stuffId = stuffId;
	}

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getOperateStockState() {
		return operateStockState;
	}

	public void setOperateStockState(String operateStockState) {
		this.operateStockState = operateStockState;
	}
	
	public float getOperateStockPrice() {
		return operateStockPrice;
	}

	public void setOperateStockPrice(float operateStockPrice) {
		this.operateStockPrice = operateStockPrice;
	}

	public String getOperateStockTime() {
		return operateStockTime;
	}

	public void setOperateStockTime(String operateStockTime) {
		this.operateStockTime = operateStockTime;
	}
}
