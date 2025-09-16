package com.example.demo.service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @KafkaListener(topics = "orders",groupId = "my-group")
    public void getMessage(String message) {
        System.out.println("Message: " + message);
    }
}
