package com.wgs.demo.event;

import com.wgs.demo.model.OrderDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Created by wanggenshen
 * Date: on 2019/11/22 23:43.
 * Description: 订单创建事件
 */
public class OrderCreateEvent extends ApplicationEvent{

    @Getter
    private OrderDTO orderDTO;

    /**
     * 重写构造函数
     *
     * @param source    发生事件的源
     * @param orderDTO  事件对应的信息
     */
    public OrderCreateEvent(Object source, OrderDTO orderDTO) {
        super(source);
        this.orderDTO = orderDTO;
    }
}
