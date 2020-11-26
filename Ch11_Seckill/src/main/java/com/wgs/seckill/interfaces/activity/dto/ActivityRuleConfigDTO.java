package com.wgs.seckill.interfaces.activity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 活动准入规则配置
 */
@Data
public class ActivityRuleConfigDTO implements Serializable {

    private static final long serialVersionUID = -8733655667012078742L;

    private String configKey;

    private String configValue;
}
