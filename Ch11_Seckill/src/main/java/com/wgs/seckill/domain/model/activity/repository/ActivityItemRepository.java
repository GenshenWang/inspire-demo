package com.wgs.seckill.domain.model.activity.repository;

import com.wgs.seckill.domain.model.activity.ActivityItem;
import com.wgs.seckill.domain.model.activity.id.ActivityId;
import com.wgs.seckill.domain.model.activity.id.ActivityItemId;

import java.util.List;

public interface ActivityItemRepository {
    /**
     * 查询活动单个商品信息
     *
     * @param itemId
     * @return
     */
    ActivityItem findActivityItem(ActivityId activityId, ActivityItemId itemId);

    /**
     * 查询活动关联商品
     *
     * @return
     */
    List<ActivityItem> listActivityItem(ActivityId activityId);

    /**
     * 保存活动关联商品
     *
     * @param activityId
     * @param activityItems
     */
    void saveActivityItem(ActivityId activityId, List<ActivityItem> activityItems);
}
