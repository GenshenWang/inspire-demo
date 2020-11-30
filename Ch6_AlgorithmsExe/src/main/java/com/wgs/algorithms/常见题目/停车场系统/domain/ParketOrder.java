package com.wgs.algorithms.常见题目.停车场系统.domain;

import lombok.Data;

@Data
public class ParketOrder {
    private String carNo;
    private ParketSpace parketSpace;
    private String orderId;
    private long startTime;
    private long endTime;
    private double totalMoney;
    private int status;
}
