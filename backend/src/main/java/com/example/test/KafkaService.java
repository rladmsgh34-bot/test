package com.example.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableKafka
class KafkaConfig {
    // Configuration might be needed here if not using application.properties
}

@Service
class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}

@Service
class KafkaConsumerService {
    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void listen(String message) {
        System.out.println("Received Message in group test-group: " + message);
    }
}

@RestController
class KafkaController {
    @Autowired
    private KafkaProducerService producerService;

    @GetMapping("/api/kafka/send")
    public String sendMessageToKafka(@RequestParam("message") String message) {
        producerService.sendMessage("test-topic", message);
        return "Message sent to Kafka: " + message;
    }
}
