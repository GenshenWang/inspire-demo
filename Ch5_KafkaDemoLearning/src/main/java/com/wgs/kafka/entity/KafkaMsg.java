package com.wgs.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/24 23:35.
 * @description: XXX
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KafkaMsg {
    /**
     * 消息topic
     */
    private String topic;

    /**
     * 消息key
     */
    private String key;

    /**
     * 自定义的消息体
     */
    private String value;

    public static KafkaMsg buildKafkaMsg(String topic, String key, String value) {
        return KafkaMsg.builder()
                .topic(topic)
                .key(key)
                .value(value)
                .build();
    }
}
