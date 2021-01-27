package com.wgs.shardingjdbc.dao;

import lombok.Data;

@Data
public class OrderDO {
    private Long id;
    private Long orderId;
    private Long userId;
    private Integer money;

}
