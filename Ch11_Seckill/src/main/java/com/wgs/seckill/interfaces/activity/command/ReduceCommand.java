package com.wgs.seckill.interfaces.activity.command;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReduceCommand implements Serializable {

    private static final long serialVersionUID = -6251425257292100031L;

    private Long activityId;
    private Long itemId;
    private Long userId;
    private Long orderId;
    private Long orderTime;
    private Integer quantity;
    private ActivityAccessContextDTO accessContext;

}
