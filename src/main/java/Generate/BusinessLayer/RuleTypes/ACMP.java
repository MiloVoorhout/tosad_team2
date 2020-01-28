package Generate.BusinessLayer.RuleTypes;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.BusinessLayer.ruleObjects.Operator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ACMP {
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
