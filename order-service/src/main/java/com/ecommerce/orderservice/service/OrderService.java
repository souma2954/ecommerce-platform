package com.ecommerce.orderservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.commons.dto.ProductDto;
import com.ecommerce.orderservice.client.ProductClient;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.exception.ProductQuantityNotEnoughException;
import com.ecommerce.orderservice.repository.OrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    
    public OrderService(OrderRepository orderRepository, ProductClient productClient) {
		super();
		this.orderRepository = orderRepository;
		this.productClient  = productClient;
	}

    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackPlaceOrder")
	public Order placeOrder(Order order) {
		ProductDto productDto = productClient.getProductById(order.getProductId());
		if(order.getQuantity()>productDto.getStock()) {
			throw new ProductQuantityNotEnoughException(order.getProductId());
		}
        order.setStatus("PENDING");  // default status
        return orderRepository.save(order);
    }
    
    public Order fallbackPlaceOrder(Order request, Throwable ex) {
        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setStatus("FAILED");
        order.setTotalPrice(0.0f);

        // Save a failed order OR just return response
        return order;
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByUser(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
