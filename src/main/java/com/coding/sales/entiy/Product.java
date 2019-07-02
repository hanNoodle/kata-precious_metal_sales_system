package com.coding.sales.entiy;

import java.math.BigDecimal;

public class Product {

	private String productId;
	private String productName;
	private BigDecimal price;
	private String activityId;
	
	public Product(String productId, String productName, BigDecimal price) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
}
