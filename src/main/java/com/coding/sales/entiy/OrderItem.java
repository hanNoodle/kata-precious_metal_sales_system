package com.coding.sales.entiy;

import java.math.BigDecimal;

public class OrderItem {
	
	private Product product;
	private int amount;
	
	public OrderItem(Product product, int amount) {
		super();
		this.product = product;
		this.amount = amount;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public BigDecimal getProductTotalPrice() {
		BigDecimal productTotalPrice = product.getPrice().multiply(BigDecimal.valueOf(amount));
		return productTotalPrice;
	}
	
	public BigDecimal getProductDiscountPrice() {
		return BigDecimal.valueOf(0);
	}
	
	public BigDecimal getProductReceivablePrice() {
		BigDecimal productTotalPrice = product.getPrice().multiply(BigDecimal.valueOf(amount));
		return productTotalPrice;
	}
}
