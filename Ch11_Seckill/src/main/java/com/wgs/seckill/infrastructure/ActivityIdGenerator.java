package com.wgs.seckill.infrastructure;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ActivityIdGenerator {

    public Long generateActivityId() {
        return UUID.randomUUID().timestamp();
    }
}
