package com.example.demo.controller;


import com.example.demo.service.producer.KafkaProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaProducer kafkaService;

    public KafkaController(KafkaProducer kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
            kafkaService.sendMessage(message);
            return ResponseEntity.ok("Message sent successfully");
    }
}
