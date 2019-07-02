package com.coding.sales.entiy;

import java.math.BigDecimal;

import com.sun.xml.internal.ws.util.StringUtils;

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
		BigDecimal productTotalPrice = new BigDecimal("0");
		if (!"".equals(product.getIsDiscountProduct()) && !"0".equals(product.getIsDiscountProduct())) {
			String discount = product.getIsDiscountProduct().split("\\|")[1];
			productTotalPrice = (new BigDecimal(1).subtract(new BigDecimal(discount))).multiply(getProductTotalPrice());
		}
		return productTotalPrice;
	}
	
	public BigDecimal getProductReceivablePrice() {
		BigDecimal productTotalPrice = product.getPrice().multiply(BigDecimal.valueOf(amount));
		if (!"".equals(product.getIsDiscountProduct()) && !"0".equals(product.getIsDiscountProduct())) {
			String discount = product.getIsDiscountProduct().split("\\|")[1];
			productTotalPrice = new BigDecimal(discount).multiply(getProductTotalPrice());
		}
		return productTotalPrice;
	}
}
