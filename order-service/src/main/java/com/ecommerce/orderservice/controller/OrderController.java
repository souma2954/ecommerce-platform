package com.ecommerce.orderservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.exception.OrderNotFoundException;
import com.ecommerce.orderservice.security.JwtUtil;
import com.ecommerce.orderservice.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final JwtUtil jwtUtil;
    
    // Place a new order
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String username = jwtUtil.extractUsername(token);
        order.setUserId(username);
        Order saved = orderService.placeOrder(order);

        return ResponseEntity.ok(saved);
    }
    
    public OrderController(OrderService orderService, JwtUtil jwtUtil) {
	super();
	this.orderService = orderService;
	this.jwtUtil = jwtUtil;
    }

 
    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(()->new OrderNotFoundException(id));
    }

    // Get all orders for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable String userId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(userId));
    }
}
