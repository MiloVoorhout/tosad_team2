package Generate.BusinessLayer.RuleTypes;

import Generate.BusinessLayer.Attribute;
import Generate.BusinessLayer.ruleObjects.Operator;

public class ICMP {

    public static String triggerCodeInterEntityCompareRule(Operator operator, Attribute attribute, Attribute subAttribute) {
        String attributeName;
        String operatorSymbol;
        String subAttributeName;

        attributeName = attribute.getName();
        operatorSymbol = operator.getSymbol();
        subAttributeName = subAttribute.getName();

        return String.format("%s %s %s",
                attributeName,
                operatorSymbol,
                subAttributeName);
    }

}
