package Generate.BusinessLayer.RuleTypes;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.BusinessLayer.ruleObjects.Operator;

public class ICMP {

    public static String triggerCodeInterEntityCompareRule(Operator operator, Attribute attribute, Attribute subAttribute) {
        String attributeName;
        String operatorSymbol;
        String subAttributeName;
        String subAttributeTable;

        attributeName = attribute.getName();
        operatorSymbol = operator.getSymbol();
        subAttributeName = subAttribute.getName();
        subAttributeTable = subAttribute.getTable();

        return String.format("%s %s %s.%s",
                attributeName,
                operatorSymbol,
                subAttributeTable,
                subAttributeName);
    }

}
