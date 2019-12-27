package com.wgs.kafka.consumer.processor;

import com.wgs.kafka.entity.KafkaMsg;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/24 20:51.
 * @description: 处理各种topic的processor
 */
public interface KafkaMsgProcessor {

    /**
     * 获取消息topic类型
     *
     * @return
     */
    String getTopic();

    /**
     * 消息校验
     *
     * @param kafkaMsg
     * @return
     */
    boolean checkMsg(KafkaMsg kafkaMsg);

    /**
     * 处理Kafka消息逻辑
     *
     * @param kafkaMsg
     */
    void processMsg(KafkaMsg kafkaMsg);



}
