package com.wgs.kafka.producer;

import com.wgs.kafka.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/23 13:58.
 * @description: Kafka消息发送测试
 */
public class KafkaSenderTest extends BaseTest {


    @Autowired
    private KafkaSender kafkaSender;

    private static final String topic = "TestTtTopic003";

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            String msg = "kafka msg from code test, current num : " + i;
            kafkaSender.asyncSendMsg(topic, null, msg);
            //kafkaSender.sendMsg(topic, null, msg);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
