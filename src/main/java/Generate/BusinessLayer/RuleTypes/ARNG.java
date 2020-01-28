package Generate.BusinessLayer.RuleTypes;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.BusinessLayer.ruleObjects.Operator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ARNG extends RuleTypesFacade {

    public static String triggerCodeRangeRuleOracle(Operator operator, Attribute attribute, HashMap values) {
        String attributeName;
        String operatorSymbol;
        String maxValue = null;
        String minValue = null;

        attributeName = attribute.getName();
        operatorSymbol = operator.getSymbol();
        Iterator iterator = values.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry value = (Map.Entry) iterator.next();
            if (value.getKey().equals(2)) {
                maxValue = (String) value.getValue();
            } else if (value.getKey().equals(1)) {
                minValue = (String) value.getValue();
            }
        }

        return String.format("%s %s %s AND %s",
                            attributeName,
                            operatorSymbol,
                            minValue,
                            maxValue);
    }

    public static String triggerCodeRangeRuleMYSQL(Operator operator, Attribute attribute, HashMap values) {
        String attributeName;
        String operatorSymbol;
        String maxValue = null;
        String minValue = null;

        attributeName = attribute.getName();
        operatorSymbol = operator.getSymbol();
        Iterator iterator = values.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry value = (Map.Entry) iterator.next();
            if (value.getKey().equals(2)) {
                maxValue = (String) value.getValue();
            } else if (value.getKey().equals(1)) {
                minValue = (String) value.getValue();
            }
        }

        return String.format("%s %s %s AND %s",
                attributeName,
                operatorSymbol,
                minValue,
                maxValue);
    }
}
