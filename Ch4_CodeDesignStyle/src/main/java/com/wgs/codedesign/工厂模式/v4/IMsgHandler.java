package com.wgs.codedesign.工厂模式.v4;

public interface IMsgHandler<T> {

    void processMsg(T data);
}
