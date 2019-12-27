package com.wgs.kafka.consumer.v1;

import com.wgs.kafka.constant.KafkaTopicConstant;
import com.wgs.kafka.consumer.processor.KafkaMsgProcessorManager;
import com.wgs.kafka.consumer.v1.factory.MyKafkaConsumerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/27 19:47.
 * @description: 用于消费订单支付的消息
 */
@Component
@Slf4j
public class OrderPayConsumer extends AbstractKafkaConsumer {

    private static final String TOPIC = KafkaTopicConstant.ORDER_PAY_TOPIC;

    @Resource
    private KafkaMsgProcessorManager msgProcessorManager;

    @Override
    protected void process(ConsumerRecord<String, String> record) {
        log.info("OrderPayConsumer receive kafka msg.");
        msgProcessorManager.process(record);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        consumer = MyKafkaConsumerFactory.getInstance().createConsumer(Arrays.asList(TOPIC));
        this.start();
    }
}
