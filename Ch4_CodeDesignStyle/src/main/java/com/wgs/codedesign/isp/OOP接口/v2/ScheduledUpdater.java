package com.wgs.codedesign.isp.OOP接口.v2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: Created by wanggenshen
 * @date: 2019/12/18 14:32.
 * @description: XXX
 */
public class ScheduledUpdater {
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private long initialDelayInSeconds;
    private long periodInSeconds;
    private ConfigUpdater updater;

    public ScheduledUpdater(long initialDelayInSeconds, long periodInSeconds, ConfigUpdater updater) {
        this.initialDelayInSeconds = initialDelayInSeconds;
        this.periodInSeconds = periodInSeconds;
        this.updater = updater;
    }


    public void run() {
        executorService.scheduleAtFixedRate(() -> {updater.update();},
                this.initialDelayInSeconds, this.periodInSeconds, TimeUnit.SECONDS);
    }




}
