package com.wgs.seckill.domain.model.activity.result;

import lombok.Value;

@Value
public class StockReduceResult {
    private boolean success;
    private String errMsg;

    public static StockReduceResult ok() {
        return new StockReduceResult(true, "OK");
    }

    public static StockReduceResult fail(String errorMsg) {
        return new StockReduceResult(false, errorMsg);
    }
}
