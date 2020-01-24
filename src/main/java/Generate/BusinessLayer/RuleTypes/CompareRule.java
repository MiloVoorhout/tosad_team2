package Generate.BusinessLayer.RuleTypes;

import Generate.BusinessLayer.Attribute;
import Define.BusinessLayer.Operator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CompareRule {

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

    public static String triggerCodeLitValue(Operator operator, Attribute attribute, HashMap values) {
        String attributeName;
        String operatorSymbol;
        String litVal = null;

        attributeName = attribute.getName();
        operatorSymbol = operator.getSymbol();
        Iterator iterator = values.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry value = (Map.Entry) iterator.next();
            litVal = (String) value.getValue();
        }

        return String.format("%s %s %s",
                attributeName,
                operatorSymbol,
                litVal);
    }
}
