package com.wgs.codedesign.工厂模式.v4;

public class Constant {

    public enum  HandlerTypeEnum {
        ORDER_CREATE(1, "订单创建"),
        PAY_FINISH(2, "支付完成")
        ;


        private int code;
        private String desc;

        HandlerTypeEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }
    }
}
