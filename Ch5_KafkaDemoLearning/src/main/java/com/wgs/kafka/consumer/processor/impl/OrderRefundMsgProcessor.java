package com.wgs.kafka.consumer.processor.impl;

import com.wgs.kafka.constant.KafkaTopicConstant;
import com.wgs.kafka.consumer.processor.KafkaMsgProcessor;
import com.wgs.kafka.entity.KafkaMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/24 23:42.
 * @description: 订单退款消息处理
 */
@Component("orderRefundMsgProcessor")
@Slf4j
public class OrderRefundMsgProcessor implements KafkaMsgProcessor {
    @Override
    public String getTopic() {
        return KafkaTopicConstant.ORDER_REFUND_TOPIC;
    }

    @Override
    public boolean checkMsg(KafkaMsg kafkaMsg) {
        return true;
    }

    @Override
    public void processMsg(KafkaMsg kafkaMsg) {
        log.info("start to process orderRefund msg, msg:{}", kafkaMsg);

        // 具体处理逻辑
        System.out.println("订单退款啦。。。");

        log.info("end process orderRefund msg");
    }
}
