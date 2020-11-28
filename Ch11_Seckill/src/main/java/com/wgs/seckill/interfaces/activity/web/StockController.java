package com.wgs.seckill.interfaces.activity.web;

import com.wgs.seckill.application.activity.StockAppService;
import com.wgs.seckill.domain.model.activity.result.StockReduceResult;
import com.wgs.seckill.interfaces.activity.dto.Response;
import com.wgs.seckill.interfaces.activity.dto.ResponseBuilder;
import com.wgs.seckill.interfaces.activity.command.CancelReduceCommand;
import com.wgs.seckill.interfaces.activity.command.ReduceCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/stock")
public class StockController {
    @Resource
    private StockAppService stockAppService;

    @PostMapping("/reduce")
    public Response<StockReduceResult> reduce(@RequestBody ReduceCommand reduceCommand) {
        StockReduceResult reduceResult = stockAppService.reduce(reduceCommand);
        return ResponseBuilder.ok(reduceResult);
    }

    @PostMapping("/cancelReduce")
    public Response<StockReduceResult> cancelReduce(@RequestBody CancelReduceCommand cancelReduceCommand) {
        StockReduceResult reduceResult = stockAppService.cancelReduce(cancelReduceCommand);
        return ResponseBuilder.ok(reduceResult);
    }
}
