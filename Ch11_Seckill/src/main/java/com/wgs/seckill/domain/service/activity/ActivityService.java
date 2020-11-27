package com.wgs.seckill.domain.service.activity;

import com.wgs.seckill.domain.model.activity.Activity;
import com.wgs.seckill.domain.model.activity.ActivityItem;

import java.util.List;

/**
 * 活动配置领域服务
 */
public interface ActivityService {

    /**
     * 保存活动
     *
     * @param activity
     * @param activityItemList
     */
    void saveActivity(Activity activity, List<ActivityItem> activityItemList);

    /**
     * 启用、禁用活动
     *
     * @param activityId
     * @param enabled
     */
    void enableActivity(Long activityId, boolean enabled);
}
