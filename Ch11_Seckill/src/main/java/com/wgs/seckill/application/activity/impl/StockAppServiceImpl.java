package com.wgs.seckill.application.activity.impl;

import com.wgs.seckill.application.activity.StockAppService;
import com.wgs.seckill.domain.model.activity.Activity;
import com.wgs.seckill.domain.model.activity.StockReduceFlow;
import com.wgs.seckill.domain.model.activity.id.ActivityId;
import com.wgs.seckill.domain.model.activity.id.OrderId;
import com.wgs.seckill.domain.model.activity.repository.ActivityItemRepository;
import com.wgs.seckill.domain.model.activity.repository.ActivityRepository;
import com.wgs.seckill.domain.model.activity.repository.StockReduceFlowRepository;
import com.wgs.seckill.domain.model.activity.result.StockReduceResult;
import com.wgs.seckill.domain.model.activity.rule.ActivityAccessContext;
import com.wgs.seckill.domain.model.activity.rule.ActivityRuleCheckResult;
import com.wgs.seckill.domain.service.activity.StockService;
import com.wgs.seckill.interfaces.activity.assemble.StockReduceFlowAssembler;
import com.wgs.seckill.interfaces.activity.command.CancelReduceCommand;
import com.wgs.seckill.interfaces.activity.command.ReduceCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StockAppServiceImpl implements StockAppService {

    @Resource
    private ActivityRepository activityRepository;
    @Resource
    private StockReduceFlowRepository stockReduceFlowRepository;
    @Resource
    private ActivityItemRepository activityItemRepository;
    @Resource
    private StockService stockService;
    @Resource
    private StockReduceFlowAssembler stockReduceFlowAssembler;

    @Override
    public StockReduceResult reduce(ReduceCommand command) {
        if (command == null) {
            return StockReduceResult.fail("ReduceCommand should not null");
        }

        // 前置校验
        ActivityId activityId = ActivityId.builder().activityId(command.getActivityId()).build();
        Activity activity = activityRepository.findActivity(activityId);
        if (activity == null) {
            return StockReduceResult.fail("Activity does not exist with activityId " + command.getActivityId());
        }

        // 检查活动是否进行中
        if (!activity.onSale(command.getOrderTime())) {
            return StockReduceResult.fail("Activity is not sale with activityId " + command.getActivityId());
        }

        // 检查活动规则是否准入
        ActivityAccessContext accessContext = stockReduceFlowAssembler.assembleActivityAccessContext(command);
        ActivityRuleCheckResult activityRuleCheckResult = activity.canJoin(accessContext);
        if (!activityRuleCheckResult.isPass()) {
            return StockReduceResult.fail("Can not join the activity " + command.getActivityId());
        }

        StockReduceFlow stockReduceFlow = stockReduceFlowAssembler.assembleStockReduceFlow(command);
        return stockService.reduce(stockReduceFlow);
    }

    @Override
    public StockReduceResult cancelReduce(CancelReduceCommand command) {
        if (command == null) {
            return  StockReduceResult.fail("ReduceCommand should not null");
        }

        ActivityId activityId = new ActivityId(command.getActivityId());
        OrderId orderId = new OrderId(command.getOrderId());
        // 获取库存扣减流水，将扣的库存加回去
        StockReduceFlow stockReduceFlow = stockReduceFlowRepository.queryStockReduceFlow(activityId, orderId);
        if (stockReduceFlow != null) {
            return stockService.cancelReduce(stockReduceFlow);
        }

        return StockReduceResult.fail("库存扣减流水不存在");
    }
}
