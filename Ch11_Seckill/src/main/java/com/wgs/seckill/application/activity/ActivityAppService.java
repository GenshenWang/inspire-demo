package com.wgs.seckill.application.activity;

import com.wgs.seckill.interfaces.activity.command.SaveActivityCommand;
import com.wgs.seckill.interfaces.activity.command.UpdateActivityStatusCommand;
import com.wgs.seckill.interfaces.activity.dto.ActivityDTO;
import com.wgs.seckill.interfaces.activity.dto.ActivityDetailDTO;
import com.wgs.seckill.interfaces.activity.dto.ActivityItemDTO;

import java.util.List;

/**
 * 活动服务
 */
public interface ActivityAppService {

    /**
     * 配置活动
     * @param command
     * @return
     */
    Long saveActivity(SaveActivityCommand command);

    /**
     * 开启/禁用活动
     *
     * @param command
     */
    void changeActivityStatus(UpdateActivityStatusCommand command);


    /**
     * 查看所有活动
     *
     * @return
     */
    List<ActivityDTO> listActivity();

    /**
     * 活动详情
     *
     * @param activityId
     * @return
     */
    ActivityDetailDTO queryActivityDetail(Long activityId);

    /**
     * 查看活动商品详情
     *
     * @param activityId
     * @param itemId
     * @return
     */
    List<ActivityItemDTO> queryActivityItemDetail(Long activityId, Long itemId);
}
