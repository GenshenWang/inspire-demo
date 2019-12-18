package com.wgs.codedesign.isp.OOP接口.v2;

import com.wgs.codedesign.isp.OOP接口.v2.configsource.ConfigSource;
import com.wgs.codedesign.isp.OOP接口.v2.configsource.NacosConfigSource;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 14:57.
 * @description: XXX
 */
public class ApplicationMain {

    private static final ConfigSource configSource = new NacosConfigSource();
    private static final KafkaConfig kafkaConfig = new KafkaConfig(configSource);
    private static final MysqlConfig mysqlConfig = new MysqlConfig(configSource);
    private static final RedisConfig redisConfig = new RedisConfig(configSource);



    public static void main(String[] args) {
        // update
        ScheduledUpdater mysqlScheduledUpdater = new ScheduledUpdater(10, 100, mysqlConfig);
        mysqlScheduledUpdater.run();

        ScheduledUpdater redisScheduledUpdater = new ScheduledUpdater(10, 100, redisConfig);
        redisScheduledUpdater.run();


        // view
        SimpleHttpServer simpleHttpServer = new SimpleHttpServer(8000, "127.0.0.1");
        simpleHttpServer.addViewer("/kafka", kafkaConfig);
        simpleHttpServer.addViewer("/redis", redisConfig);

        simpleHttpServer.run();

    }
}
