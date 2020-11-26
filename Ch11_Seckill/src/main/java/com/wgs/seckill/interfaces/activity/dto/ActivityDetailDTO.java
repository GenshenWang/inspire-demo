package com.wgs.seckill.interfaces.activity.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ActivityDetailDTO implements Serializable {

    private static final long serialVersionUID = 4663376396313052438L;

    private Long activityId;
    private String activityName;
    private Long startTime;
    private Long endTime;
    private boolean enabled;

    /**
     * 活动商品
     */
    private List<ActivityItemDTO> items;


}
