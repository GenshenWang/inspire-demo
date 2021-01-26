package com.wgs.shardingjdbc.dao;

import lombok.Data;

@Data
public class OrderDO {
    private long id;
    private long orderId;
    private long useId;
    private int money;

}
