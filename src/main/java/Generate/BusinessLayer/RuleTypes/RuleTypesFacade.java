package Generate.BusinessLayer.RuleTypes;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.BusinessLayer.ruleObjects.Operator;

import java.util.HashMap;

public class RuleTypesFacade {

    // ACMP functies
    public static String triggerCodeLitValue(Operator operator, Attribute attribute, HashMap values) {

        return ACMP.triggerCodeLitValue(operator, attribute, values);
    }

    //ALIS functies
    public static String triggerCodeListRule(Operator operator, Attribute attribute, HashMap values) {

        return ALIS.triggerCodeListRule(operator, attribute, values);
    }

    //ARNG functies
    public static String triggerCodeRangeRule(Operator operator, Attribute attribute, HashMap values) {

        return ARNG.triggerCodeRangeRule(operator, attribute, values);
    }

    //EOTH functies
    public static String triggerCodeEntityOtherRule(HashMap values) {

        return EOTH.triggerCodeEntityOtherRule(values);
    }

    //ICMP functies
    public static String triggerCodeInterEntityCompareRule(Operator operator, Attribute attribute,
                                                           Attribute subAttribute) {

        return ICMP.triggerCodeInterEntityCompareRule(operator, attribute, subAttribute);
    }

    //TCMP functies
    public static String triggerCodeSubAttribute(Operator operator, Attribute attribute, Attribute subAttribute) {

        return TCMP.triggerCodeSubAttribute(operator, attribute, subAttribute);
    }
}
