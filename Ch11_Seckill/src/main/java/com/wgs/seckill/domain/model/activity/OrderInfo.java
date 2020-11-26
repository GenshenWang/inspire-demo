package com.wgs.seckill.domain.model.activity;

import com.wgs.seckill.domain.model.activity.id.ActivityItemId;
import com.wgs.seckill.domain.model.activity.id.OrderId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfo {

    private OrderId orderId;

    /**
     * 订单关联的商品id
     */
    private ActivityItemId itemId;

    /**
     * 订单购买的商品数量
     */
    private Integer quantity;

    /**
     * 订单下单时间
     */
    private Long orderTime;
}
