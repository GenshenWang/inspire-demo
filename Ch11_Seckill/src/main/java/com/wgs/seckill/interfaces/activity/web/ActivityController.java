package com.wgs.seckill.interfaces.activity.web;

import com.wgs.seckill.application.activity.ActivityAppService;
import com.wgs.seckill.interfaces.activity.dto.Response;
import com.wgs.seckill.interfaces.activity.dto.ResponseBuilder;
import com.wgs.seckill.interfaces.activity.command.SaveActivityCommand;
import com.wgs.seckill.interfaces.activity.command.UpdateActivityStatusCommand;
import com.wgs.seckill.interfaces.activity.dto.ActivityDTO;
import com.wgs.seckill.interfaces.activity.dto.ActivityDetailDTO;
import com.wgs.seckill.interfaces.activity.dto.ActivityItemDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Resource
    private ActivityAppService activityAppService;

    /**
     * 保存活动
     *
     * @param command
     * @return
     */
    @PostMapping("/save")
    public Response<Long> saveActivity(SaveActivityCommand command) {
        Long activityId = activityAppService.saveActivity(command);
        return ResponseBuilder.ok(activityId);
    }

    /**
     * 启用、禁用活动
     *
     * @param command
     * @return
     */
    @PostMapping("/change")
    public Response<Void> changeActivity(UpdateActivityStatusCommand command) {
        activityAppService.changeActivityStatus(command);
        return ResponseBuilder.ok();
    }


    @GetMapping("/list")
    public Response<List<ActivityDTO>> listActivity() {
        List<ActivityDTO> activityDTOS = activityAppService.listActivity();
        return ResponseBuilder.ok(activityDTOS);
    }

    @GetMapping("/detail")
    public Response<ActivityDetailDTO> activityDetail(@RequestParam("activityId") Long activityId) {
        ActivityDetailDTO activityDetail = activityAppService.queryActivityDetail(activityId);
        return ResponseBuilder.ok(activityDetail);
    }

    @GetMapping("/item/detail")
    public Response<ActivityItemDTO> activityItemDetail(@RequestParam("activityId") Long activityId,
                                                        @RequestParam("activityItemId") Long activityItemId) {
        ActivityItemDTO activityItemDetail = activityAppService.queryActivityItemDetail(activityId, activityItemId);
        return ResponseBuilder.ok(activityItemDetail);
    }

















}
