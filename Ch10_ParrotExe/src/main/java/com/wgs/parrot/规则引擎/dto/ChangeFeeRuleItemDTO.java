package com.wgs.parrot.规则引擎.dto;

import java.util.Date;

public class ChangeFeeRuleItemDTO extends RuleItemDTO {

    private static final long serialVersionUID = 2593836981877910734L;

    /**
     * 凭证编码
     */
    private String voucherNo;

    /**
     * 原业务流水号
     */
    private String originBizNo;

    /**
     * 新业务流水号
     */
    private String bizNo;

    /**
     * 曹操改费后总金额（单位：分）
     */
    private Integer ccAmount;

    /**
     * 曹操改费后总电费（单位：分）
     */
    private Integer ccElectricAmount;

    /**
     * 曹操改费后总服务费（单位：分）
     */
    private Integer ccServiceAmount;

    /**
     * 运营商改费后协议总金额（单位：分）
     */
    private Integer costAmount;

    /**
     * 运营商改费后协议电费（单位：分）
     */
    private Integer costElectricAmount;

    /**
     * 运营商改费后协议服务费（单位：分）
     */
    private Integer costServiceAmount;

    private Date createTime;
    private Date updateTime;


}
