package com.wgs.shardingjdbc.strategy;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 自定义分库策略，精确查询
 */
@Service
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm {
    @Override
    public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
        int dataSource = preciseShardingValue.getValue().toString() == null
                ? 0 : Integer.parseInt(preciseShardingValue.getValue().toString());
        return "ds_" + dataSource;
    }
}
