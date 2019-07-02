package com.coding.sales.entiy;

import java.math.BigDecimal;
import java.util.List;

public class Order {
	private String orderId;
	private List<OrderItem> orderItemList;
	
	public Order(String orderId, List<OrderItem> orderItemList) {
		super();
		this.orderId = orderId;
		this.orderItemList = orderItemList;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = new BigDecimal("0");
		for (OrderItem orderItem:orderItemList) {
			totalPrice=totalPrice.add(orderItem.getProductTotalPrice());
		}
		return totalPrice;
	}
	
	public BigDecimal getDiscountPrice() {
		BigDecimal discountPrice = new BigDecimal("0");
		for (OrderItem orderItem:orderItemList) {
			discountPrice = discountPrice.add(orderItem.getProductDiscountPrice());
		}
		return discountPrice;
	}
	
	public BigDecimal getReceivablePrice() {
		BigDecimal receivablePrice = new BigDecimal("0");
		for (OrderItem orderItem:orderItemList) {
			receivablePrice = receivablePrice.add(orderItem.getProductReceivablePrice());
		}
		return receivablePrice;
	}
}
