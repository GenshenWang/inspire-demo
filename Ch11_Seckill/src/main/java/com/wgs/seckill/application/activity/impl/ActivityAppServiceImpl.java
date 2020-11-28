package com.wgs.seckill.application.activity.impl;

import com.wgs.seckill.application.activity.ActivityAppService;
import com.wgs.seckill.domain.model.activity.Activity;
import com.wgs.seckill.domain.model.activity.ActivityItem;
import com.wgs.seckill.domain.model.activity.ItemSale;
import com.wgs.seckill.domain.model.activity.id.ActivityId;
import com.wgs.seckill.domain.model.activity.id.ActivityItemId;
import com.wgs.seckill.domain.model.activity.repository.ActivityItemRepository;
import com.wgs.seckill.domain.model.activity.repository.ActivityRepository;
import com.wgs.seckill.domain.model.activity.repository.ItemSaleRepository;
import com.wgs.seckill.domain.service.activity.ActivityService;
import com.wgs.seckill.interfaces.activity.assemble.ActivityAssembler;
import com.wgs.seckill.interfaces.activity.command.SaveActivityCommand;
import com.wgs.seckill.interfaces.activity.command.UpdateActivityStatusCommand;
import com.wgs.seckill.interfaces.activity.dto.ActivityDTO;
import com.wgs.seckill.interfaces.activity.dto.ActivityDetailDTO;
import com.wgs.seckill.interfaces.activity.dto.ActivityItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivityAppServiceImpl implements ActivityAppService {

    @Resource
    private ActivityRepository activityRepository;
    @Resource
    private ActivityAssembler activityAssembler;
    @Resource
    private ActivityService activityService;
    @Resource
    private ActivityItemRepository activityItemRepository;
    @Resource
    private ItemSaleRepository itemSaleRepository;

    @Override
    public Long saveActivity(SaveActivityCommand command) {
        // 活动
        Activity activity = activityAssembler.assembleActivity(command);

        // 活动商品
        ActivityId activityId = activity.getActivityId();
        List<ActivityItem> activityItems = activityAssembler.assembleActivityItems(activityId, command);

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
        if (CollectionUtils.isEmpty(activities)) {
            return Collections.emptyList();
        }

        return activities.stream()
                .map(activity -> activityAssembler.assembleActivityDTO(activity))
                .collect(Collectors.toList());
    }

    @Override
    public ActivityDetailDTO queryActivityDetail(Long activityId) {
        Activity activity = activityRepository.findActivity(new ActivityId(activityId));
        if (activity == null) {
            log.error("活动信息不存在, activityId={}", activityId);
            return null;
        }

        // 活动主要信息
        ActivityDetailDTO activityDetailDTO = new ActivityDetailDTO();
        activityDetailDTO.setActivityId(activityId);
        activityDetailDTO.setActivityName(activity.getActivityName());
        activityDetailDTO.setStartTime(activity.getStartTime());
        activityDetailDTO.setEndTime(activity.getEndTime());
        activityDetailDTO.setEnabled(activity.isEnabled());

        // 活动关联商品
        List<ActivityItem> activityItems = activityItemRepository.listActivityItem(new ActivityId(activityId));
        if (CollectionUtils.isEmpty(activityItems)) {
            activityDetailDTO.setItems(Collections.emptyList());
            log.warn("活动暂时未关联商品, activityId={}", activityId);
            return activityDetailDTO;
        }

        // 商品销量
        Map<Long, Integer> activitySaleMap = itemSaleRepository.queryActivitySales(new ActivityId(activityId));
        List<ActivityItemDTO> activityItemDTOS = activityItems.stream()
                .map(activityItem -> {
                    ActivityItemDTO activityItemDTO = activityAssembler.assembleActivityItemDTO(activityItem);
                    activityItemDTO.setSold(activitySaleMap.getOrDefault(activityItem.getActivityId().getActivityId(), 0));
                    return activityItemDTO;
                })
                .collect(Collectors.toList());
        activityDetailDTO.setItems(activityItemDTOS);

        return activityDetailDTO;
    }

    @Override
    public ActivityItemDTO queryActivityItemDetail(Long activityId, Long itemId) {
        // 活动信息
        ActivityId activityIdRequest = new ActivityId(activityId);
        Activity currentActivity = activityRepository.findActivity(activityIdRequest);
        Assert.notNull(currentActivity, "活动信息不存在, activityId: " + activityId);

        // 商品信息
        ActivityItemId itemIdRequest = new ActivityItemId(itemId);
        ActivityItem currentActivityItem = activityItemRepository.findActivityItem(activityIdRequest, itemIdRequest);
        Assert.notNull(currentActivityItem, "活动商品信息不存在, itemId: " + itemId);

        // 商品销量
        ItemSale itemSale = itemSaleRepository.queryItemSales(activityIdRequest, itemIdRequest);

        return ActivityItemDTO.builder()
                .itemId(itemId)
                .itemTitle(currentActivityItem.getTitle())
                .itemSubTitle(currentActivityItem.getSubTitle())
                .price(currentActivityItem.getPrice())
                .activityPrice(currentActivityItem.getActivityPrice())
                .stock(currentActivityItem.getStock())
                .image(currentActivityItem.getImage())
                .quota(currentActivityItem.getQuota())
                .sold(itemSale == null ? 0 : itemSale.getSaleCount())
                .build();
    }
}
