package com.wgs.codedesign.工厂模式.v4;

@HandlerType(handlerType = Constant.HandlerTypeEnum.PAY_FINISH)
public class PayFinishMsgHandler implements IMsgHandler<String> {

    @Override
    public void processMsg(String data) {
        System.out.println("处理支付订单消息：" + data);
    }
}
