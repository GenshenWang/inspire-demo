package com.wgs.parrot.规则引擎;

import com.wgs.parrot.规则引擎.dto.RuleItemDTO;
import com.wgs.parrot.规则引擎.dto.SettlementRuleItemDTO;

public class OrderSettlementRule extends AbstractRule {

    @Override
    protected <T> T convert(RuleItemDTO rule) {
        SettlementRuleItemDTO settlementRule = (SettlementRuleItemDTO) rule;
        return (T) settlementRule;
    }

    @Override
    protected <T> boolean executeRule(T rule) {
        SettlementRuleItemDTO settlementRule = (SettlementRuleItemDTO) rule;
        String sourceType = settlementRule.getSourceType();
        Integer serviceType = settlementRule.getServiceType();
        // List<RuleItemBO> ruleSet = queryRuleItemSet(sourceType, serviceType);

        // List<RuleItemBO> ruleSet = replaceRule(ruleSet, settlementRule);

        // identity_no, identity_type, account_type
        // List<AccountBO> accountList = queryValidAccounts(List<AccountQueryParam>  params);
        // valid accountList : status

        return super.executeRule(rule);
    }
}
