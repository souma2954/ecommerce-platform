package com.ecommerce.productservice.kafka;

import com.ecommerce.commons.dto.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(topics = "orders", groupId = "product-service-group")
    public void consume(OrderEvent event) {
        System.out.println("📥 Received Order event: " + event);
        // TODO: decrease stock or update product availability
    }
}
