package com.wgs.seckill.domain.model.activity.rule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivityAccessContext {

    private String cityId;

    /**
     * 商品id
     */
    private String storeId;
}
