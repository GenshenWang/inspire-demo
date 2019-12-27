package com.wgs.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@Slf4j
public class Ch5KafkaDemoLearningApplication {

    @Bean
    public CountDownLatch closeLatch() {
        return new CountDownLatch(1);
    }

    /**
     * 防止启动失败spring boot 吞掉异常无法快速定位问题，直接打印到控制台
     */
    @Bean
    public ApplicationListener<ApplicationFailedEvent> failedEventApplicationListener() {
        return event -> {
            //日志输出到控制台
            log.error("Application startup failed,cause by ", event.getException());
        };
    }

    public static void main(String[] args) throws InterruptedException {
        SpringApplication application = new SpringApplication(Ch5KafkaDemoLearningApplication.class);
        ApplicationContext ctx = application.run(args);
        System.out.println("Server startup done.");
        CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
        Runtime.getRuntime().addShutdownHook(new Thread(closeLatch::countDown));
        closeLatch.await();
    }

}
