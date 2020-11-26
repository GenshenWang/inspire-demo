package com.wgs.seckill.interfaces.activity.command;

import lombok.Data;

import java.io.Serializable;

@Data
public class CancelReduceCommand implements Serializable {

    private static final long serialVersionUID = 6995494886182938202L;

    private Long orderId;
    private Long activityId;
}
