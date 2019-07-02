package com.coding.sales.entiy;

import java.math.BigDecimal;

public class Product {

	private String productId;
	private String productName;
	private BigDecimal price;
	private String activityId;
	private String isDiscountProduct;
	
	public Product(String productId, String productName, BigDecimal price,
			String activityId, String isDiscountProduct) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.activityId = activityId;
		this.isDiscountProduct = isDiscountProduct;
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

	public String getIsDiscountProduct() {
		return isDiscountProduct;
	}

	public void setIsDiscountProduct(String isDiscountProduct) {
		this.isDiscountProduct = isDiscountProduct;
	}
	
}
