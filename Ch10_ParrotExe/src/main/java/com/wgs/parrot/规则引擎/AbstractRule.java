package com.wgs.parrot.规则引擎;

import com.wgs.parrot.规则引擎.dto.RuleItemDTO;

public abstract class AbstractRule implements BaseRule {

    @Override
    public boolean execute(RuleItemDTO rule) {
        return executeRule(convert(rule));
    }

    /**
     * 用于扩展。如可以实现ClassA extends RuleItemDTO，此处返回ClassA即可。
     *
     * @param rule
     * @param <T>
     * @return
     */
    protected <T> T convert(RuleItemDTO rule) {
        return (T)rule;
    }

    /**
     * 用于子类重写
     *
     * @param rule
     * @param <T>
     * @return
     */
    protected <T> boolean executeRule(T rule) {
        return true;
    }
}
