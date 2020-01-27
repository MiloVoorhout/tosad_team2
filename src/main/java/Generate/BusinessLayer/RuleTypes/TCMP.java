package Generate.BusinessLayer.RuleTypes;

import Generate.BusinessLayer.Attribute;
import Generate.BusinessLayer.ruleObjects.Operator;

public class TCMP {
    public static String triggerCodeSubAttribute(Operator operator, Attribute attribute, Attribute subAttribute) {
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
