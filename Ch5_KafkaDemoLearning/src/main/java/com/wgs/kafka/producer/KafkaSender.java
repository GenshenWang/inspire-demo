package com.wgs.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 *
 */
@Slf4j(topic = "kafka")
@Component
public class KafkaSender implements InitializingBean {

    /**
     * Kafka server服务器地址
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServers;

    /**
     * Producer发送消息失败的时候重试次数
     */
    @Value("${spring.kafka.producer.retries}")
    private int retries;

    /**
     * batch.size是producer批量发送的基本单位，
     * 默认是16384Bytes，即16kB
     */
    @Value("${spring.kafka.producer.batch-size}")
    private int batchSize;

    /**
     * lingger.ms是sender线程在检查batch是否ready时候，
     * 判断有没有过期的参数，默认大小是0ms
     */
    @Value("${spring.kafka.producer.properties.linger.ms}")
    private int linger;

    /**
     * producer可以用来缓存数据的内存大小
     */
    @Value("${spring.kafka.producer.buffer-memory}")
    private int bufferMemory;

    /**
     * 多少ISR副本写入成功才算消息发送成功, all代表全部
     */
    @Value("${spring.kafka.producer.acks}")
    private String acks;



    private KafkaProducer<String, String> producer;


    /**
     * 异步发送kafka消息
     *
     * @param topic
     * @param key
     * @param message
     */
    public void asyncSendMsg(String topic, String key, String message) {
        ProducerRecord producerRecord = new ProducerRecord(topic, key, message);
        producer.send(producerRecord, (recordMetadata, e) -> {
            if (e != null) {
                System.out.println("!!!+++error!!!!");
                log.error("kafka msg send error, topic={}, key={}, message={}, e={}", topic, key, message, e);
                return;
            }

            // send success
            if (recordMetadata != null) {
                System.out.println(">>>>>>>message:" + message);
                log.info("kafka msg send success, topic={}, key={}, partition:{}, offset:{}, timestamp:{}", topic, key, message, recordMetadata.partition(),
                        recordMetadata.offset(), recordMetadata.timestamp());
            } else {
                log.info("kafka msg send success result is null, topic={}, key={}, timestamp:{}", topic, key, message, recordMetadata.timestamp());
            }

        });
    }

    /**
     * 消息同步发送
     *
     * @param topic
     * @param key
     * @param message
     */
    public void sendMsg(String topic, String key, String message) {
        ProducerRecord producerRecord = new ProducerRecord(topic, key, message);
        producer.send(producerRecord);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.ACKS_CONFIG, acks);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        producer = new KafkaProducer<>(props);
    }
}
