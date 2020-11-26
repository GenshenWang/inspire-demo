package com.wgs.seckill.application.activity;

import com.wgs.seckill.domain.model.activity.result.StockReduceResult;
import com.wgs.seckill.interfaces.activity.command.CancelReduceCommand;
import com.wgs.seckill.interfaces.activity.command.ReduceCommand;

/**
 * 库存服务
 */
public interface StockAppService {

    StockReduceResult reduce(ReduceCommand command);

    StockReduceResult cancelReduce(CancelReduceCommand command);
}
