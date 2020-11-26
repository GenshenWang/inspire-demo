package com.wgs.seckill.domain.model.activity.repository;

import com.wgs.seckill.domain.model.activity.ItemSale;
import com.wgs.seckill.domain.model.activity.id.ActivityId;
import com.wgs.seckill.domain.model.activity.id.ActivityItemId;

import java.util.Map;

/**
 * 商品效率仓储
 */
public interface ItemSaleRepository {

    /**
     * 查询活动所有商品销售量
     *
     * @param activityId
     * @return
     */
    Map<Long, Integer> queryActivitySales(ActivityId activityId);

    /**
     * 查询单个商品销售量
     *
     * @param activityId
     * @param itemId
     * @return
     */
    ItemSale queryItemSales(ActivityId activityId, ActivityItemId itemId);

}
