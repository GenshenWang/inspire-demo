package com.wgs.seckill.domain.model.activity.rule;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActivityRuleRegister {

    private static Map<String, Class<? extends ActivityRule>> ruleClassMap = new ConcurrentHashMap<>();

    static {

    }

    private void registerRule(ActivityRule activityRule) {
        // map.put(k,v)，k存在时set 新v，并返回旧v
       if (ruleClassMap.containsKey(activityRule.ruleName())) {
           throw new IllegalArgumentException("活动规则名称已存在");
       }

        ruleClassMap.put(activityRule.ruleName(), activityRule.getClass());
    }

    public static ActivityRule parseRule(ActivityRuleConfig activityRuleConfig) {
        String configKey = activityRuleConfig.getConfigKey();
        try {
            Class<? extends ActivityRule> ruleClass = ruleClassMap.get(configKey);
            ActivityRule activityRule = ruleClass.newInstance();

            activityRule.decode(activityRuleConfig);
            return activityRule;
        } catch (Exception e) {
            throw new RuntimeException("活动规则不存在");
        }
    }


}
