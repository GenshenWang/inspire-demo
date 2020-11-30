package com.wgs.algorithms.常见题目.停车场系统.domain;

import lombok.Data;

@Data
public class ParketSpace {
    private int parkNo;
    // 1 - 小车； 2 - 大车
    private int type;
    private boolean lock;
    private int price;
}
