package com.wgs.codedesign.工厂模式.v4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @Autowired
    private IMsgHandlerManager iMsgHandlerManager;

    @RequestMapping(value = "/test/spring")
    public void handle(@RequestParam("msg")  String msg,
                       @RequestParam("type")  int type) {

        iMsgHandlerManager.doHandle(type, msg);
    }

}
