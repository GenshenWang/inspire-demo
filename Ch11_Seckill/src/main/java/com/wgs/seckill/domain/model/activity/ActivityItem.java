package com.wgs.seckill.domain.model.activity;

import com.wgs.seckill.domain.model.activity.id.ActivityId;
import com.wgs.seckill.domain.model.activity.id.ActivityItemId;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 活动商品
 *
 * 活动商品实际是跟活动关联的。如果用 Long activityId 表示活动id就没法跟活动id关联起来，
 * 所以用ActivityId表示更合适。
 */
@Data
@Slf4j
public class ActivityItem {

    /**
     * 商品id
     */
    private ActivityItemId itemId;

    /**
     * 与活动关联起来
     * 与 private Long activityId 这样的用法对比
     */
    private ActivityId activityId;

    private String title;
    private String subTitle;
    private String image;
    /**
     * 原价
     */
    private Long price;
    /**
     * 活动价
     */
    private Long activityPrice;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 限购数量
     */
    private Integer quota;



}
