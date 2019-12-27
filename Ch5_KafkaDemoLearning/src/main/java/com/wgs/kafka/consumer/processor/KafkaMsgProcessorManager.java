package com.wgs.kafka.consumer.processor;

import com.wgs.kafka.entity.KafkaMsg;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/25 00:17.
 * @description: XXX
 */
@Component("kafkaMsgProcessorManager")
@Slf4j
public class KafkaMsgProcessorManager {

    @Autowired
    private List<KafkaMsgProcessor> kafkaMsgProcessorList;

    private static volatile Map<String, KafkaMsgProcessor> processorMap = new HashMap<>();
    private Set<String> topics = new HashSet<>();

    @PostConstruct
    public void initProcessors() {
        if (processorMap.size() <= 0) {
            kafkaMsgProcessorList.stream().forEach(processor -> {
                String topic = processor.getTopic();
                processorMap.put(topic, processor);
                topics.add(topic);
            });
        }
    }

    /**
     * 获取处理消息的processor
     * @param topic
     * @return
     */
    public KafkaMsgProcessor getProcessor(String topic) {
        if (processorMap.size() <= 0) {
            return  null;
        }

        return processorMap.get(topic);
    }


    public void process(ConsumerRecord<String, String> record) {
        if (record == null) {
            return;
        }

        String topic = record.topic();
        String key = record.key();
        String value = record.value();

        KafkaMsgProcessor processor = getProcessor(topic);
        if (processor == null) {
            log.error("There is no suitable processor for topic:{}", topic);
            return;
        }

        KafkaMsg kafkaMsg = KafkaMsg.buildKafkaMsg(topic, key, value);
        if (!processor.checkMsg(kafkaMsg)) {
            log.error("KafkaProcessor[{}] check msg:{} failed", processor, kafkaMsg);
            return;
        }

        try {
            processor.processMsg(kafkaMsg);
        } catch (Exception e) {
            log.error("Invoke KafkaProcessor[{}] process msg:{} occur error: ", processor, kafkaMsg, e);
        }


    }

    public Set<String> getTopics() {
        return topics;
    }
}
