package org.shersfy.user.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaClient {
    
    protected Logger LOGGER = LoggerFactory.getLogger(KafkaClient.class);
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void subscribeTopics(ConsumerRecord<String, Object> record) {
        Object msg = record.value();
        if(msg instanceof byte[]) {
            LOGGER.info("topic={}, msg={}", record.topic(), new String((byte[])msg));
        } else {
            LOGGER.info("topic={}, msg={}", record.topic(), msg);
        }
    }
    
    public void produceMsg(String topic, String text) {
        kafkaTemplate.send(topic, String.valueOf(System.currentTimeMillis()), text);
        // 消息发送的监听器, 用于回调返回信息
        kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
            @Override
            public void onSuccess(String topic, Integer partition, String key, String value, RecordMetadata recordMetadata) {
                LOGGER.debug("onSuccess, key={}, value={}", key, value);
            }

            @Override
            public void onError(String topic, Integer partition, String key, String value, Exception exception) {
                LOGGER.debug("onError, key={}, value={}", key, value);
            }

            @Override
            public boolean isInterestedInSuccess() {
                return false;
            }
        });
    }

}
