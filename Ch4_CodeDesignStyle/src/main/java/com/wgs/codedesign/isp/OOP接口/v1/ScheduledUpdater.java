package com.wgs.codedesign.isp.OOP接口.v1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 10:20.
 * @description: XXX
 */
public class ScheduledUpdater {
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private long initialDelayInSeconds;
    private long periodInSeconds;

    private ConfigServiceV1 configService;

    public ScheduledUpdater(ConfigServiceV1 configService) {
        this.configService = configService;
    }

    public ScheduledUpdater(long initialDelayInSeconds, long periodInSeconds, ConfigServiceV1 configService) {
        this.initialDelayInSeconds = initialDelayInSeconds;
        this.periodInSeconds = periodInSeconds;
        this.configService = configService;
    }

    public void run() {
        executor.scheduleWithFixedDelay(() -> configService.update(),
                initialDelayInSeconds, periodInSeconds, TimeUnit.SECONDS);
    }

}
