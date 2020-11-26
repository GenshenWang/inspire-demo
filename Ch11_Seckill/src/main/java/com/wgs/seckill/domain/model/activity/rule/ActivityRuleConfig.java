package com.wgs.seckill.domain.model.activity.rule;

import lombok.Data;

@Data
public class ActivityRuleConfig {

    private Long activityId;
    private String configKey;
    private String configValue;
}
