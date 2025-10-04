package com.ecommerce.commons.dto;

public class OrderEvent {
    private String orderId;
    private String userId;
    private String productId;
    private int quantity;
    
	public OrderEvent(String orderId, String userId, String productId, int quantity) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
	}
	
	
	public OrderEvent() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    
}
