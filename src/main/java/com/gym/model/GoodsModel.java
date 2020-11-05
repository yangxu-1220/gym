package com.gym.model;

public class GoodsModel {
		
	public String getOperateStockState() {
		return operateStockState;
	}

	public void setOperateStockState(String operateStockState) {
		this.operateStockState = operateStockState;
	}

	private int goodsId;
	
	private String goodsName;
	
	private String goodsType;
	
	private float goodsPrice;
	
	private String goodsUnit;
	
	private String goodsNote;
	
	private int goodsStockNumber;
	
	private String goodsState;
	
	private String goodsImage;
	
	private int number; //入库和出库时的数量
	
	private String operateStockState;//保存入库和出库的状态

	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getGoodsNote() {
		return goodsNote;
	}

	public void setGoodsNote(String goodsNote) {
		this.goodsNote = goodsNote;
	}

	public int getGoodsStockNumber() {
		return goodsStockNumber;
	}

	public void setGoodsStockNumber(int goodsStockNumber) {
		this.goodsStockNumber = goodsStockNumber;
	}

	public String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
