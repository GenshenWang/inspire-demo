package com.wgs.seckill.domain.model.activity;

import com.wgs.seckill.domain.model.activity.id.ActivityItemId;
import lombok.Value;

@Value
public class ItemSale {
    private ActivityItemId itemId;
    private Integer saleCount;
}
