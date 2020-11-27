package com.wgs.seckill.application.activity.impl;

import com.wgs.seckill.application.activity.ActivityAppService;
import com.wgs.seckill.domain.model.activity.Activity;
import com.wgs.seckill.domain.model.activity.ActivityItem;
import com.wgs.seckill.domain.model.activity.id.ActivityId;
import com.wgs.seckill.domain.model.activity.id.ActivityItemId;
import com.wgs.seckill.domain.model.activity.repository.ActivityItemRepository;
import com.wgs.seckill.domain.model.activity.repository.ActivityRepository;
import com.wgs.seckill.domain.service.activity.ActivityService;
import com.wgs.seckill.interfaces.activity.assemble.ActivityAssemble;
import com.wgs.seckill.interfaces.activity.command.SaveActivityCommand;
import com.wgs.seckill.interfaces.activity.command.UpdateActivityStatusCommand;
import com.wgs.seckill.interfaces.activity.dto.ActivityDTO;
import com.wgs.seckill.interfaces.activity.dto.ActivityDetailDTO;
import com.wgs.seckill.interfaces.activity.dto.ActivityItemDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityAppServiceImpl implements ActivityAppService {

    @Resource
    private ActivityRepository activityRepository;
    @Resource
    private ActivityAssemble activityAssemble;
    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityItemRepository activityItemRepository;

    @Override
    public Long saveActivity(SaveActivityCommand command) {
        // 活动
        Activity activity = activityAssemble.assembleActivity(command);

        // 活动商品
        ActivityId activityId = activity.getActivityId();
        List<ActivityItem> activityItems = activityAssemble.assembleActivityItems(activityId, command);

        // 保存
        activityService.saveActivity(activity, activityItems);
        return activityId.getActivityId();
    }

    @Override
    public void changeActivityStatus(UpdateActivityStatusCommand command) {
        activityService.enableActivity(command.getActivityId(), command.getEnabled());
    }

    @Override
    public List<ActivityDTO> listActivity() {
        List<Activity> activities = activityRepository.listActivity();
        return null;
    }

    @Override
    public ActivityDetailDTO queryActivityDetail(Long activityId) {
        activityRepository.findActivity(new ActivityId(activityId));
        return null;
    }

    @Override
    public List<ActivityItemDTO> queryActivityItemDetail(Long activityId, Long itemId) {
        activityItemRepository.findActivityItem(new ActivityId(activityId), new ActivityItemId(itemId));
        return null;
    }
}
