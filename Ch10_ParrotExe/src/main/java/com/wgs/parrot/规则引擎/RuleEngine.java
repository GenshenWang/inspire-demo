package com.wgs.parrot.规则引擎;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RuleEngine implements InitializingBean, ApplicationContextAware {
    private ApplicationContext applicationContext;

    private Map<Integer, BaseRule> ruleMap = new HashMap<>();

    public BaseRule selectRule(Integer ruleType) {
        if (ruleMap == null || ruleMap.isEmpty()) {
            synchronized (ruleMap) {
                if (ruleMap == null || ruleMap.isEmpty()) {
                    init();
                }
            }
        }

        return ruleMap.get(ruleType);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init() {
        OrderSettlementRule orderSettlementRule = applicationContext.getBean(OrderSettlementRule.class);
        ruleMap.put(1, orderSettlementRule);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }
}
