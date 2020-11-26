package com.wgs.seckill.domain.model.activity.repository;

import com.wgs.seckill.domain.model.activity.StockReduceFlow;
import com.wgs.seckill.domain.model.activity.id.ActivityId;
import com.wgs.seckill.domain.model.activity.id.OrderId;

/**
 * 库存扣减
 */
public interface StockReduceFlowRepository {

    StockReduceFlow queryStockReduceFlow(ActivityId activityId, OrderId orderId);
}
