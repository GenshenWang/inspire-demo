package com.wgs.demo.listener;

import com.wgs.demo.event.OrderCreateEvent;
import com.wgs.demo.event.OrderPayEvent;
import com.wgs.demo.model.OrderDTO;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wanggenshen
 * Date: on 2019/11/22 23:51.
 * Description: 订单支付监听器
 */
@Component
public class OrderPayEventListener implements SmartApplicationListener {

    /**
     * 支持的事件类型
     *
     * @param eventClass
     * @return
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventClass) {
        return eventClass == OrderPayEvent.class;
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

    @Async
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        OrderPayEvent orderPayEvent = (OrderPayEvent) applicationEvent;
        OrderDTO orderDTO = orderPayEvent.getOrderDTO();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + "===  异步, 收到订单支付事件  ===" + orderDTO);

        // 业务逻辑

    }
}
