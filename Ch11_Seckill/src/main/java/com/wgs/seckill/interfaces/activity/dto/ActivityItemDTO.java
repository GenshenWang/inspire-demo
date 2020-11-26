package com.wgs.seckill.interfaces.activity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityItemDTO implements Serializable {

    private static final long serialVersionUID = -8301772140428262709L;

    private Long itemId;
    private String itemTitle;
    private String itemSubTitle;
    private String image;

    private Double price;
    private Double activityPrice;
    /**
     * 限购数量
     */
    private Integer quota;
    /**
     * 库存
     */
    private Integer stock;

    /**
     * 商品销量
     */
    private Integer sold;

    /**
     * 评论数量
     */
    private Integer comment;

}
