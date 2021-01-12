package com.wgs.codedesign.工厂模式.v4;

public enum  HandlerTypeEnum {
    ORDER_CREATE(1, "支付完成"),
    PAY_FINISH(2, "支付完成")
    ;


    private int code;
    private String desc;

    HandlerTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int get22() {
        return 0;
    }

    public int getCode() {
        return code;
    }
}
