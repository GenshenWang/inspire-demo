package com.wgs.seckill.domain.model.activity.rule;

import lombok.Data;

@Data
public abstract class BaseRule implements ActivityRule {

    private Long activityId;

    /**
     * Rule -> String value
     * 每种规则解析都不一样
     * @return
     */
    protected abstract String encodeConfigValue();

    /**
     * String value -> Rule
     * @param value
     */
    protected abstract void decodeConfigValue(String value);

    @Override
    public void decode(ActivityRuleConfig config) {
        if (ruleName().equals(config.getConfigKey())) {
            this.activityId = config.getActivityId();
            decodeConfigValue(config.getConfigValue());
        } else {
            throw new RuntimeException("Rule config decode error");
        }
    }

    @Override
    public ActivityRuleConfig encode() {
        return ActivityRuleConfig.builder()
                .activityId(activityId)
                .configKey(ruleName())
                .configValue(encodeConfigValue())
                .build();
    }
}
