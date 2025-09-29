package com.ecommerce.orderservice.kafka;

import com.ecommerce.commons.dto.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderEvent event) {
        Message<OrderEvent> message = MessageBuilder.
        		withPayload(event).
        		setHeader(KafkaHeaders.TOPIC,"orders").build();
        kafkaTemplate.send(message);
    }
}
