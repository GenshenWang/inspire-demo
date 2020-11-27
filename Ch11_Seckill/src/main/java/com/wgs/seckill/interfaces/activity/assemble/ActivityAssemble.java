package com.wgs.seckill.interfaces.activity.assemble;

import com.google.common.collect.Lists;
import com.wgs.seckill.domain.model.activity.Activity;
import com.wgs.seckill.domain.model.activity.ActivityItem;
import com.wgs.seckill.domain.model.activity.id.ActivityId;
import com.wgs.seckill.domain.model.activity.id.ActivityItemId;
import com.wgs.seckill.domain.model.activity.rule.ActivityRule;
import com.wgs.seckill.domain.model.activity.rule.ActivityRuleConfig;
import com.wgs.seckill.domain.model.activity.rule.ActivityRuleRegister;
import com.wgs.seckill.infrastructure.ActivityIdGenerator;
import com.wgs.seckill.interfaces.activity.command.SaveActivityCommand;
import com.wgs.seckill.interfaces.activity.dto.ActivityItemDTO;
import com.wgs.seckill.interfaces.activity.dto.ActivityRuleConfigDTO;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivityAssemble {
    @Resource
    private ActivityIdGenerator activityIdGenerator;

    public Activity assembleActivity(SaveActivityCommand command) {
        Activity activity = new Activity();

        // 活动ID
        boolean enabled = command.isEnabled();
        Long activityId;
        if (command.getActivityId() == null) {
            activityId = activityIdGenerator.generateActivityId();
            // 活动默认关闭
            enabled = false;
        } else {
            activityId = command.getActivityId();
        }

        activity.setActivityId(new ActivityId(activityId));
        activity.setActivityName(command.getActivityName());
        activity.setStartTime(command.getStartTime());
        activity.setEndTime(command.getEndTime());
        activity.setEnabled(enabled);

        // 活动规则
        activity.setActivityRules(assembleActivityRules(command.getActivityRuleConfigs()));
        return activity;
    }

    public List<ActivityRule> assembleActivityRules(List<ActivityRuleConfigDTO> activityRuleConfigs) {
        if (CollectionUtils.isEmpty(activityRuleConfigs)) {
            return Lists.newArrayList();
        }

        return activityRuleConfigs.stream()
                .map(this::convertActivityRule)
                .collect(Collectors.toList());
    }

    private ActivityRule convertActivityRule(ActivityRuleConfigDTO dtoRule) {
        ActivityRuleConfig ruleConfig = ActivityRuleConfig.builder()
                .configKey(dtoRule.getConfigKey())
                .configValue(dtoRule.getConfigValue())
                .build();
        return ActivityRuleRegister.parseRule(ruleConfig);
    }


    public List<ActivityItem> assembleActivityItems(ActivityId activityId, SaveActivityCommand command) {
        List<ActivityItemDTO> activityItemDTOS = command.getItems();

        return activityItemDTOS.stream().map(itemDto -> {
            return convertActivityItem(activityId, itemDto);
        }).collect(Collectors.toList());
    }

    private ActivityItem convertActivityItem(ActivityId activityId, ActivityItemDTO activityItemDTO) {
        return ActivityItem.builder()
                .activityId(activityId)
                .itemId(new ActivityItemId(activityItemDTO.getItemId()))
                .price(activityItemDTO.getPrice())
                .activityPrice(activityItemDTO.getActivityPrice())
                .image(activityItemDTO.getImage())
                .title(activityItemDTO.getItemTitle())
                .subTitle(activityItemDTO.getItemSubTitle())
                .quota(activityItemDTO.getQuota())
                .stock(activityItemDTO.getStock())
                .build();

    }
}
