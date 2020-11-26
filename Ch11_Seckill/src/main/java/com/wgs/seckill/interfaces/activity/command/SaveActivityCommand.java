package com.wgs.seckill.interfaces.activity.command;

import com.wgs.seckill.interfaces.activity.dto.ActivityItemDTO;
import com.wgs.seckill.interfaces.activity.dto.ActivityRuleConfigDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SaveActivityCommand implements Serializable {

    private static final long serialVersionUID = 1927725342382450480L;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    private Long startTime;
    private Long endTime;
    private boolean enabled;

    /**
     * 活动商品列表
     */
    private List<ActivityItemDTO> items;

    /**
     * 活动规则。比如城市限制、购买数量限制等
     */
    private List<ActivityRuleConfigDTO> activityRuleConfigs;


}
