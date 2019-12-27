package com.wgs.kafka.consumer.v2.config;

import com.wgs.kafka.consumer.v2.KafkaMessageConsumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class KafkaConsumerConfig {


   @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    /**
     * 是否自动提交
     */
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private boolean enableAutoCommit;

    /**
     * 执行超时时间
     */
    @Value("${spring.kafka.consumer.properties.session.timeout}")
    private String sessionTimeout;

    /**
     * 向zookeeper提交offset的频率，默认：5000
     */
    @Value("${spring.kafka.consumer.properties.auto.commit.interval}")
    private String autoCommitInterval;

    /**
     * 表示自动将偏移重置为最新的偏移量
     */
    @Value("${spring.kafka.consumer.properties.auto.offset.reset}")
    private String autoOffsetReset;

    /**
     * KafkaMessageListenerContainer监听器容器中启动的consumer个数
     */
    @Value("${spring.kafka.consumer.properties.concurrency}")
    private int concurrency;

    /**
     *  consumer连接超时时间
     *  在该时间段内consumer未发送心跳消息认为宕机
     */
    @Value("${spring.kafka.consumer.properties.pollTimeout}")
    private int pollTimeout;


    @Resource
    private KafkaMessageConsumer kafkaMessageConsumer;


    /*
     * The KafkaMessageListenerContainer receives all message from all topics or partitions on a single thread.
     * The ConcurrentMessageListenerContainer delegates to one or more KafkaMessageListenerContainer instances
     *     to provide multi-threaded consumption.
     *
     * @return
     */
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        // 使用ConcurrentKafkaListenerContainerFactory, 指定线程数量(concurrency), 并发消费
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        // 创建多个KafkaListenerContainer实例
        factory.setConcurrency(concurrency);
        factory.getContainerProperties().setPollTimeout(pollTimeout);
        return factory;
    }


    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public Map<String, Object>  consumerConfigs() {
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

        return propsMap;
    }


    /**
     * Start a listener
     *
     * Each KafkaMessageListenerContainer gets one Consumer (and one thread).
     * The thread continually poll()s the consumer, with the specified pollTimeout.
     *
     * @return
     */
    @Bean(initMethod = "doStart")
    public KafkaMessageListenerContainer kafkaMessageListenerContainer() {
        return new KafkaMessageListenerContainer(consumerFactory(), containerProperties());
    }

    @Bean
    public ContainerProperties containerProperties() {
        String[] topics = new String[kafkaMessageConsumer.listTopics().size()];
        kafkaMessageConsumer.listTopics().toArray(topics);
        ContainerProperties containerProperties = new ContainerProperties(topics);
        containerProperties.setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        containerProperties.setMessageListener(kafkaMessageConsumer);
        return containerProperties;
    }


}
