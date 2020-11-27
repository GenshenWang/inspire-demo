package com.wgs.seckill.domain.model.activity.rule;

public interface ActivityRule {
    /**
     * 规则名称
     *
     * @return
     */
    String ruleName();

    /**
     * 解析config，转换成rule
     *
     * @param config
     */
    void decode(ActivityRuleConfig config);

    /**
     * 将当前rule转成ActivityRuleConfig
     *
     * @return
     */
    ActivityRuleConfig encode();

    /**
     * 判断是否满足活动
     *
     * @param context
     * @return
     */
    boolean satisfy(ActivityAccessContext context);
}
