package Generate.BusinessLayer.RuleTypes;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.BusinessLayer.ruleObjects.Operator;

import java.util.HashMap;

public class RuleTypesFacade {

    // ACMP functies
    public static String triggerCodeLitValueOracle(Operator operator, Attribute attribute, HashMap values) {

        return ACMP.triggerCodeLitValueOracle(operator, attribute, values);
    }

    public static String triggerCodeLitValueMYSQL(Operator operator, Attribute attribute, HashMap values) {

        return ACMP.triggerCodeLitValueMYSQL(operator, attribute, values);
    }

    //ALIS functies
    public static String triggerCodeListRuleOracle(Operator operator, Attribute attribute, HashMap values) {

        return ALIS.triggerCodeListRuleOracle(operator, attribute, values);
    }

    public static String triggerCodeListRuleMYSQL(Operator operator, Attribute attribute, HashMap values) {

        return ALIS.triggerCodeListRuleMYSQL(operator, attribute, values);
    }

    //ARNG functies
    public static String triggerCodeRangeRuleOracle(Operator operator, Attribute attribute, HashMap values) {

        return ARNG.triggerCodeRangeRuleOracle(operator, attribute, values);
    }

    public static String triggerCodeRangeRuleMYSQL(Operator operator, Attribute attribute, HashMap values) {

        return ARNG.triggerCodeRangeRuleMYSQL(operator, attribute, values);
    }

    //EOTH functies
    public static String triggerCodeEntityOtherRuleOracle(HashMap values) {

        return EOTH.triggerCodeEntityOtherRuleOracle(values);
    }

    public static String triggerCodeEntityOtherRuleMYSQL(HashMap values) {

        return EOTH.triggerCodeEntityOtherRuleMYSQL(values);
    }

    //ICMP functies
    public static String triggerCodeInterEntityCompareRuleOracle(Operator operator, Attribute attribute,
                                                           Attribute subAttribute) {

        return ICMP.triggerCodeInterEntityCompareRuleOracle(operator, attribute, subAttribute);
    }

    public static String triggerCodeInterEntityCompareRuleMYSQL(Operator operator, Attribute attribute,
                                                                 Attribute subAttribute) {

        return ICMP.triggerCodeInterEntityCompareRuleMYSQL(operator, attribute, subAttribute);
    }

    //TCMP functies
    public static String triggerCodeSubAttributeOracle(Operator operator, Attribute attribute, Attribute subAttribute) {

        return TCMP.triggerCodeSubAttributeOracle(operator, attribute, subAttribute);
    }

    public static String triggerCodeSubAttributeMYSQL(Operator operator, Attribute attribute, Attribute subAttribute) {

        return TCMP.triggerCodeSubAttributeMYSQL(operator, attribute, subAttribute);
    }
}
