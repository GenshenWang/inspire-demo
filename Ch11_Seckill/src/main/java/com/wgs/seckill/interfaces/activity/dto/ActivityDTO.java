package com.wgs.seckill.interfaces.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityDTO implements Serializable {

    private static final long serialVersionUID = 459467393337072508L;
    private Long activityId;
    private String activityName;
    private Long startTime;
    private Long endTime;
    private boolean enabled;


}
