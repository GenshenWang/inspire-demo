package com.wgs.kafka.consumer.v2;

import com.wgs.kafka.consumer.processor.KafkaMsgProcessorManager;
import com.wgs.kafka.thread.NamedThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/24 20:01.
 * @description: 消费模型2: 单个Kafka Consumer实例(接收消息) + 线程池消费消息
 *
 *               MessageListener : 自动提交,对应enable.auto.commit=true, 即消费后会自动更新offset
 *               AcknowledgingMessageListener: 手动提交, 对应enable.auto.commit=false, 即消息消费后不会自动更新offset, 需要手动提交
 */
@Slf4j(topic = "kafka")
@Component
public class KafkaMessageConsumer implements AcknowledgingMessageListener<String, String> {

    @Resource
    private KafkaMsgProcessorManager processorManager;

    private final ExecutorService kafkaPool = new ThreadPoolExecutor(5, 10,
            10, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(1024),
            new NamedThreadFactory("Kafka-Listener"),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @Override
    public void onMessage(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {

        kafkaPool.execute(() -> {
            boolean commit = false;
            try {
                log.info("Receive kafka message, topic={}, key={}, value={}, offset={}, timestamp={}",
                        consumerRecord.topic(), consumerRecord.key(), consumerRecord.value(), consumerRecord.offset(), consumerRecord.timestamp());

                // 消息处理
                processorManager.process(consumerRecord);

                commit = true;
            } catch (Exception e) {
                // 消费异常的时候, 不提交 (可以业务重试)
                log.error("Consume message error, topic={}, e:", consumerRecord.topic(), e);
                commit = false;
            } finally {
                // 消费成功, 手动提交位移
                if (commit) {
                    acknowledgment.acknowledge();
                }

            }

        });
    }


    public Set<String> listTopics() {
        return processorManager.getTopics();
    }
}
