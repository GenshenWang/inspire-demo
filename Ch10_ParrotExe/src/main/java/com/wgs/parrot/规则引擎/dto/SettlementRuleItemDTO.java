package com.wgs.parrot.规则引擎.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SettlementRuleItemDTO extends RuleItemDTO {

    private static final long serialVersionUID = -5373487643993078634L;

    /**
     * 凭证编码
     */
    private String voucherNo;

    /**
     * 业务流水号
     */
    private String bizNo;

    /**
     * 订单开始时间
     */
    private Date beginTime;

    /**
     * 订单结束时间
     */
    private Date endTime;

    /**
     * 充电量
     */
    private BigDecimal totalCharging;

    /**
     * 总金额（单位：分）
     */
    private Integer totalAmount;

    /**
     * 总电费（单位：分）
     */
    private Integer totalElectricAmount;

    /**
     * 总服务费（单位：分）
     */
    private Integer totalServiceAmount;

    /**
     * 协议总金额（单位：分）
     */
    private Integer costAmount;

    /**
     * 协议电费（单位：分）
     */
    private Integer costElectricAmount;

    /**
     * 协议服务费（单位：分）
     */
    private Integer costServiceAmount;

    /**
     * 站点编号
     */
    private String stationCode;

    /**
     * 站点所在城市编码，如310000，330100
     */
    private String districtCode;

    /**
     * 桩编号
     */
    private String pileNo;

    /**
     * 枪编号
     */
    private String gunNo;

    private Date createTime;
    private Date updateTime;
}
