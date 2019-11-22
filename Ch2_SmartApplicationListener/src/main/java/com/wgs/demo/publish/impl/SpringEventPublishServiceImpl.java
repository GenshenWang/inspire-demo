package com.wgs.demo.publish.impl;

import com.wgs.demo.publish.EventPublishService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Service;

/**
 * Created by wanggenshen
 * Date: on 2019/11/22 23:47.
 * Description: Spring实现的事件发布
 */
@Service("springEventPublishService")
public class SpringEventPublishServiceImpl implements EventPublishService<ApplicationEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
