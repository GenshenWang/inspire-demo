package com.wgs.demo.listener;

import com.wgs.demo.event.OrderCreateEvent;
import com.wgs.demo.model.OrderDTO;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by wanggenshen
 * Date: on 2019/11/22 23:51.
 * Description: 订单创建监听器
 */
@Component
public class OrderCreateEventListener implements SmartApplicationListener {

    /**
     * 支持的事件类型
     *
     * @param eventClass
     * @return
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventClass) {
        return eventClass == OrderCreateEvent.class;
    }

    /**
     * 支持的事件源所在的类
     *
     * @param sourceType
     * @return
     */
    @Override
    public boolean supportsSourceType(@Nullable Class<?> sourceType) {
        return true;
    }

    /**
     * 定义事件监听器的顺序
     *
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        OrderCreateEvent orderCreateEvent = (OrderCreateEvent) applicationEvent;
        OrderDTO orderDTO = orderCreateEvent.getOrderDTO();

        System.out.println("===  收到订单创建事件  ===" + orderDTO);

        // 业务逻辑

    }
}
