package com.wgs.seckill.domain.model.activity.repository;

import com.wgs.seckill.domain.model.activity.Activity;
import com.wgs.seckill.domain.model.activity.id.ActivityId;

import java.util.List;

/**
 * 活动仓储，用于查活动、保存活动
 */
public interface ActivityRepository {
    /**
     * 查看单个活动
     *
     * @param activityId
     * @return
     */
    Activity findActivity(ActivityId activityId);

    /**
     * 查询活动列表
     *
     * @return
     */
    List<Activity> listActivity();

    /**
     * 保存活动
     *
     * @param activity
     */
    void saveActivity(Activity activity);
}
