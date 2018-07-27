package org.shersfy.user.controller;


import javax.annotation.Resource;

import org.shersfy.user.kafka.KafkaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    
    @Resource
    private KafkaClient kafkaClient;
    
    @Value("${spring.kafka.template.default-topic}")
    private String topics;
    
    @GetMapping("/topics")
    public Object listTopics() {
        return topics;
    }
    
    @GetMapping("/subscribe")
    public boolean subscribe(String topics) {
        return true;
    }
    
    @GetMapping("/unsubscribe")
    public boolean unsubscribe(String topics) {
        return true;
    }
    
    @GetMapping("/send")
    public boolean send(String topic, String text) {
        kafkaClient.produceMsg(topic, text);
        return true;
    }
}
