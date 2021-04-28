package com.wgs.parrot.规则引擎.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RuleItemDTO implements Serializable {

    private static final long serialVersionUID = -3255446473380815119L;

    private String sourceType;

    /**
     * 服务类型：结算、支付、改费
     */
    private Integer serviceType;
}
