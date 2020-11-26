package com.wgs.seckill.domain.model.activity;

import com.wgs.seckill.domain.model.activity.id.ActivityId;
import com.wgs.seckill.domain.model.activity.rule.ActivityAccessContext;
import com.wgs.seckill.domain.model.activity.rule.ActivityRule;
import com.wgs.seckill.domain.model.activity.rule.ActivityRuleCheckResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * 秒杀活动
 *
 * （1）活动属性
 * （2）活动动作
 */
@Data
@Slf4j
public class Activity {
    private ActivityId activityId;
    private String activityName;
    private Long startTime;
    private Long endTime;
    private boolean enabled;
    private List<ActivityRule> activityRules;

    /**
     * 活动是否进行中
     *
     * @return
     */
    public boolean onSale(Long nowDate) {
        return enabled && (nowDate >= startTime && nowDate < endTime);
    }

    /**
     * 开启、禁用活动
     */
    public void enableActivity(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 判断是否可参与活动
     *
     * @param context
     * @return
     */
    public ActivityRuleCheckResult canJoin(ActivityAccessContext context) {
        if (CollectionUtils.isEmpty(activityRules)) {
            return ActivityRuleCheckResult.ok();
        }
        if (context == null) {
            return ActivityRuleCheckResult.fail("活动context不能为空");
        }
        for (ActivityRule activityRule : activityRules) {
            if (activityRule == null) {
                continue;
            }
            if (!activityRule.satisfy(context)) {
                log.warn("商品不满足活动，context={}, rule={}", context, activityRule);
                return ActivityRuleCheckResult.fail("未满足活动");
            }
        }

        return ActivityRuleCheckResult.ok();
    }


}
