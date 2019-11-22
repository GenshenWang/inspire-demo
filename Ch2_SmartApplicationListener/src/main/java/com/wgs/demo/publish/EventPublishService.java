package com.wgs.demo.publish;

/**
 * Created by wanggenshen
 * Date: on 2019/11/22 23:46.
 * Description: 事件发布
 */
public interface EventPublishService<T> {

    void publishEvent(T event);
}
