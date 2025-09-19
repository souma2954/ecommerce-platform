package com.ecommerce.orderservice.exception;

public class OrderNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8771315901423954356L;

	public OrderNotFoundException(String id) {
        super("Order not found with ID: " + id);
    }
}
