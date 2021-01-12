package com.wgs.codedesign.工厂模式.v4;

import com.wgs.codedesign.工厂模式.v4.bean.CreateOrderDTO;

@HandlerType(handlerType = Constant.HandlerTypeEnum.ORDER_CREATE)
public class OrderCreateMsgHandler implements IMsgHandler<CreateOrderDTO> {

    @Override
    public void processMsg(CreateOrderDTO data) {
        System.out.println("处理订单创建消息");
    }
}
