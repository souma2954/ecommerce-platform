package com.ecommerce.orderservice.exception;

public class ProductQuantityNotEnoughException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8771315901423958956L;

	public ProductQuantityNotEnoughException(String pid) {
        super("Quantity is not enough to place order for product ID: " + pid);
    }
}
