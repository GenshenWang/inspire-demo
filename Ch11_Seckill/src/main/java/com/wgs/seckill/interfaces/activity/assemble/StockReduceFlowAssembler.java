package com.wgs.seckill.interfaces.activity.assemble;

import com.wgs.seckill.domain.model.activity.OrderInfo;
import com.wgs.seckill.domain.model.activity.StockReduceFlow;
import com.wgs.seckill.domain.model.activity.id.ActivityId;
import com.wgs.seckill.domain.model.activity.id.ActivityItemId;
import com.wgs.seckill.domain.model.activity.id.OrderId;
import com.wgs.seckill.domain.model.activity.id.UserId;
import com.wgs.seckill.domain.model.activity.rule.ActivityAccessContext;
import com.wgs.seckill.interfaces.activity.command.ActivityAccessContextDTO;
import com.wgs.seckill.interfaces.activity.command.ReduceCommand;
import org.springframework.stereotype.Component;

@Component
public class StockReduceFlowAssembler {

    public StockReduceFlow assembleStockReduceFlow(ReduceCommand command) {
        OrderInfo orderInfo = OrderInfo.builder()
                .itemId(new ActivityItemId(command.getItemId()))
                .orderId(new OrderId(command.getOrderId()))
                .orderTime(command.getOrderTime())
                .quantity(command.getQuantity())
                .build();

        return StockReduceFlow.builder()
                .activityId(new ActivityId(command.getActivityId()))
                .orderInfo(orderInfo)
                .userId(new UserId(command.getUserId()))
                .build();
    }

    public ActivityAccessContext assembleActivityAccessContext(ReduceCommand command) {
        ActivityAccessContextDTO commandActivityAccessContext = command.getAccessContext();
        if (commandActivityAccessContext == null) {
            return null;
        }

        return ActivityAccessContext.builder()
                .cityId(commandActivityAccessContext.getCityId())
                .storeId(commandActivityAccessContext.getStoreId())
                .build();
    }
}
