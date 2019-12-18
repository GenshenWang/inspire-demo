package com.wgs.codedesign.ocp.v2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by wanggenshen
 * Date: on 2019/12/11 09:42.
 * Description: 告警通知类，支持邮件、短信、微信、手机等多种通知渠道
 */
@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Notification {

    private String notifyMsg;
    private int notifyType;


    /**
     * 发送消息告警
     *
     * @param notifyType    告警类型
     * @param notifyMsg     告警内容
     */
    public void notify(int notifyType, String notifyMsg) {
        log.info("Receive notifyMsg [{}] to push, type:{}", notifyMsg, notifyType);
    }
}
