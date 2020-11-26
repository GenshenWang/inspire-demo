package com.wgs.seckill.domain.model.activity;

import com.wgs.seckill.domain.model.activity.id.ActivityId;
import com.wgs.seckill.domain.model.activity.id.UserId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存扣减流水
 * 为什么放在领域层？不了解
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockReduceFlow {

    /**
     * 活动id
     */
    private ActivityId activityId;

    /**
     * 用户id
     */
    private UserId userId;

    private OrderInfo orderInfo;;
}
