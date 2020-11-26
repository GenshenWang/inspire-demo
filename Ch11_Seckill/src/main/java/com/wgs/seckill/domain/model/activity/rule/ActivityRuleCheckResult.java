package com.wgs.seckill.domain.model.activity.rule;

import lombok.Value;

@Value
public class ActivityRuleCheckResult {
    private boolean pass;
    private String errorMsg;

    public static ActivityRuleCheckResult ok() {
        return new ActivityRuleCheckResult(true, "");
    }

    public static ActivityRuleCheckResult fail(String errorMsg) {
        return new ActivityRuleCheckResult(false, errorMsg);
    }
}
