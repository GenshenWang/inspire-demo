package com.wgs.seckill.domain.model.activity.rule;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * 活动规则-城市：目前只有城市id限制
 * 规则配置为逗号分隔的城市id数组，如：121,123,134
 */
@Slf4j
public class CityRule extends BaseRule {

    private Set<String> cityIds = Sets.newHashSet();
    private static final String NO_CITY_LIMIT = "-1";


    @Override
    public String ruleName() {
        return "RULE_CITY";
    }

    /**
     * 重写抽象类方法
     * @return
     */
    @Override
    protected String encodeConfigValue() {
        return Joiner.on(",").join(cityIds);
    }

    /**
     * 重写抽象类方法： 将配置信息转为规则实体类
     *
     * @param value
     */
    @Override
    protected void decodeConfigValue(String value) {
        if (value != null && value.length() > 0) {
           this.cityIds = Sets.newHashSet( Splitter.on(".").omitEmptyStrings().split(value));
        }
    }

    @Override
    public boolean satisfy(ActivityAccessContext context) {
        String cityId = context.getCityId();
        if (cityIds.contains(NO_CITY_LIMIT) || cityIds.contains(cityId)) {
            return true;
        }

        log.info("活动城市限制, cityId={}", cityId);
        return false;
    }
}
