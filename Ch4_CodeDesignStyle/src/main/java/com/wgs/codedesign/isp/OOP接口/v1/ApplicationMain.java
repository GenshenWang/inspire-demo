package com.wgs.codedesign.isp.OOP接口.v1;

import com.wgs.codedesign.isp.OOP接口.v1.configsource.ConfigSource;
import com.wgs.codedesign.isp.OOP接口.v1.configsource.NacosConfigSource;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 10:30.
 * @description: XXX
 */
public class ApplicationMain {

    // 模拟数据源配置
    private static final ConfigSource configSource = new NacosConfigSource();

    public static final ConfigServiceV1 redisConfig = new RedisConfig(configSource);
    public static final ConfigServiceV1 kafkaConfig = new KafkaConfig(configSource);
    public static final ConfigServiceV1 mysqlConfig = new MysqlConfig(configSource);




    public static void main(String[] args) {
        // 更新只更新Redis和Mysql
        ScheduledUpdater redisScheduledUpdater = new ScheduledUpdater(100, 20, redisConfig);
        redisScheduledUpdater.run();

        ScheduledUpdater mysqlScheduledUpdater = new ScheduledUpdater(200, 30, mysqlConfig);
        mysqlScheduledUpdater.run();

        // 查看只查看Kafka的
        SimpleHttpServer simpleHttpServer = new SimpleHttpServer(8000, "127.0.0.1");
        simpleHttpServer.addConfigView("/kafka", kafkaConfig);
        simpleHttpServer.run();
    }
}
