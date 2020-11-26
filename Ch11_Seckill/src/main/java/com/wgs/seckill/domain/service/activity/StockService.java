package com.wgs.seckill.domain.service.activity;

import com.wgs.seckill.domain.model.activity.StockReduceFlow;
import com.wgs.seckill.domain.model.activity.result.StockReduceResult;

/**
 * 库存扣减领域服务
 */
public interface StockService {


    /**
     * 扣库存
     *
     * @param flow
     * @return
     */
    StockReduceResult reduce(StockReduceFlow flow);

    /**
     * 回库存
     *
     * @param flow
     * @return
     */
    StockReduceResult cancelReduce(StockReduceFlow flow);
}
