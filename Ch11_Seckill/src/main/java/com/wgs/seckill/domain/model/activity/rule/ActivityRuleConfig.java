package com.wgs.seckill.domain.model.activity.rule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRuleConfig {

    private Long activityId;
    private String configKey;
    private String configValue;
}
