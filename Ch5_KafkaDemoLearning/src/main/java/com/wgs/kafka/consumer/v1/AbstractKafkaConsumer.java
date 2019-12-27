package com.wgs.kafka.consumer.v1;

import com.wgs.kafka.consumer.processor.KafkaMsgProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.InitializingBean;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/27 20:09.
 * @description: Kafka消费类
 */
@Slf4j
public abstract class AbstractKafkaConsumer extends Thread implements InitializingBean{

    protected KafkaConsumer consumer;
    private final AtomicBoolean closed = new AtomicBoolean(false);

    @Override
    public void run() {
        try {
            while (!closed.get()) {

                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
                for (ConsumerRecord<String, String> record : records) {
                    process(record);
                }
            }
        } catch (Exception e) {
            log.error("Kafka consumer poll records occur terrible error!, error msg:", e);
            if (!closed.get()) {
                throw e;
            }
        } finally {
            consumer.close();
        }

    }

    protected abstract void process(ConsumerRecord<String, String> record);

    private void shutdown() {
        if (closed.compareAndSet(false, true)) {
            this.interrupt();
            consumer.wakeup();
        }
    }
}
