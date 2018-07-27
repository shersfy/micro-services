package org.shersfy.user.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.scheduling.annotation.Async;

public class KafkaClient2 {
    
    protected Logger LOGGER = LoggerFactory.getLogger(KafkaClient2.class);
    
    private static Map<SubscribeKey, Consumer<?, ?>> consumers = new ConcurrentHashMap<>();
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    private ConsumerFactory<?, ?> consumerFactory;
    
    public List<String> listTopics(){
        SubscribeKey key = getSubscribeKey("list-topics");
        Consumer<?, ?> consumer = consumers.get(key);
        if(consumer == null) {
            consumer = consumerFactory.createConsumer();
            consumers.put(key, consumer);
        }

        Map<String, List<PartitionInfo>> topics = consumer.listTopics();
        List<String> list = new ArrayList<>(topics.keySet());
        list.sort((o1, o2)->o1.compareTo(o2));
        return list;
    }
    
    /**
     * 订阅topic
     * @param topics 逗号分隔
     */
    @Async
    public void subscribe(String topics) {
        
        SubscribeKey key = getSubscribeKey(topics);
        if(key == null) {
            return;
        }
        
        List<String> list = Arrays.asList(key.getTopics().split(",")); 
        Consumer<?, ?> consumer = consumers.get(key);
        if(consumers.containsKey(key)) {
            return;
        }
        
        consumer = consumerFactory.createConsumer();
        consumers.put(key, consumer);
        
        Map<String, List<PartitionInfo>> existTopics = consumer.listTopics();
        list.forEach(topic->{
            if(!existTopics.containsKey(topic)) {
                LOGGER.error("topic '{}' not exist", topic);
                return;
            }
        });
        
        consumer.subscribe(list);
        try {
            while(!key.getClosed().get()) {
                if(!consumers.containsKey(key)) {
                    return;
                }
                ConsumerRecords<?, ?> records = consumer.poll(1000);
                for(ConsumerRecord<?, ?> record :records) {
                    process(record);
                }
            }
        } catch (WakeupException ex) {
            if (!key.getClosed().get()) {
                throw ex;
            }
        } finally {
            consumer.commitSync();
            consumer.close();
            LOGGER.info("closed consumer subscribed topics={}", topics);
        }
    }
    /**
     * 取消订阅
     * @param topics 逗号分隔
     */
    public void unsubscribe(String topics) {
        SubscribeKey key = getSubscribeKey(topics);
        if(key == null || !consumers.containsKey(key)) {
            return;
        }
        
        Consumer<?, ?> consumer = consumers.get(key);
        key.getClosed().set(true);
        consumer.wakeup();
        consumers.remove(key);
    }

    protected void process(ConsumerRecord<?, ?> record) {
        LOGGER.info("topic={}, key={}, value={}", record.topic(), record.key(), record.value());
    }
    
    private SubscribeKey getSubscribeKey(String topics) {
        if(StringUtils.isBlank(topics)) {
            return null;
        }
        topics = topics.replace(" ", "");
        List<String> list = Arrays.asList(topics.split(",")); 
        list.sort((t1, t2) -> t1.compareTo(t2));
        
        String key = StringUtils.join(list, ",");
        for(SubscribeKey skey : consumers.keySet()) {
            if(key.equals(skey.getTopics())) {
                return skey;
            }
        }
        return new SubscribeKey(key);
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
