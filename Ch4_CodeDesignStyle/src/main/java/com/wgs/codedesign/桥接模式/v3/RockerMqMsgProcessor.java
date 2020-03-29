package com.wgs.codedesign.桥接模式.v3;

import com.wgs.codedesign.桥接模式.v3.handler.MsgHandler;

/**
 * @author: wanggenshen
 * @date: 2020/3/29 23:10.
 * @description: XXX
 */
public class RockerMqMsgProcessor extends  MqMsgProcessor {

    public RockerMqMsgProcessor(MsgHandler msgHandler) {
        super(msgHandler);
    }

    @Override
    public void processMqMsg(String topic, String message) {
        msgHandler.processMsg(message);
    }
}
