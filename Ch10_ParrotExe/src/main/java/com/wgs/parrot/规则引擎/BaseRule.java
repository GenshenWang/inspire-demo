package com.wgs.parrot.规则引擎;

import com.wgs.parrot.规则引擎.dto.RuleItemDTO;

public interface BaseRule {

    boolean execute(RuleItemDTO rule);
}
