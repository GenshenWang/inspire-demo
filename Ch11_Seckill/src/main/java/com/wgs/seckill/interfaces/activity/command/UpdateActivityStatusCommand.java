package com.wgs.seckill.interfaces.activity.command;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateActivityStatusCommand implements Serializable {

    private static final long serialVersionUID = -5646204061839878192L;

    private Long activityId;
    private Boolean enabled;

}
