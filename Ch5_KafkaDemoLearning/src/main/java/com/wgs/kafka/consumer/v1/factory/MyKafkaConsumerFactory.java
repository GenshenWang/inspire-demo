package com.wgs.kafka.consumer.v1.factory;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/27 19:55.
 * @description: Kafka Consumer工厂类
 */
public class MyKafkaConsumerFactory {

    private KafkaConsumer consumer = null;

    private MyKafkaConsumerFactory() {}


    private static class SingletonHolder {
        private static final MyKafkaConsumerFactory INSTANCE = new MyKafkaConsumerFactory();
    }


    public static final MyKafkaConsumerFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public KafkaConsumer createConsumer(List<String> topics) {
        consumer = new KafkaConsumer(defaultConsumerConfig());
        subscribeTopics(topics);
        return consumer;

    }


    public KafkaConsumer createConsumer(List<String> topics, Map<String, Object> propsMap) {
        consumer = new KafkaConsumer(propsMap);
        subscribeTopics(topics);
        return consumer;

    }


    private Map<String, Object> defaultConsumerConfig() {
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.1.203:9092");
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "10000");
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-client-demo");
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        return propsMap;
    }

    private void subscribeTopics(List<String> topics) {
        consumer.subscribe(topics);
    }

}
