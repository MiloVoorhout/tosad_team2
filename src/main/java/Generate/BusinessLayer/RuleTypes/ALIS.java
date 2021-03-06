package Generate.BusinessLayer.RuleTypes;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.BusinessLayer.ruleObjects.Operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ALIS extends RuleTypesFacade {

    public static String triggerCodeListRuleOracle(Operator operator, Attribute attribute, HashMap values) {
        String attributeName;
        String operatorSymbol;
        ArrayList<String> listValues = new ArrayList<>();

        attributeName = attribute.getName();
        operatorSymbol = operator.getSymbol();
        Iterator iterator = values.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry value = (Map.Entry) iterator.next();
            listValues.add((String) value.getValue());
         }

        StringBuffer finalList = new StringBuffer();
        finalList.append("(");
        for (int i = 0; i < listValues.size(); i++) {
            boolean numeric = true;
            numeric = listValues.get(i).matches("-?\\d+(\\.\\d+)?");

            if(numeric)
                finalList.append(listValues.get(i));
            else
                finalList.append("'" + listValues.get(i) + "'");

            if (i != listValues.size() - 1) {
                finalList.append(", ");
            }
        }
        finalList.append(")");

        return String.format("%s %s %s",
                attributeName,
                operatorSymbol,
                finalList);
    }

    public static String triggerCodeListRuleMYSQL(Operator operator, Attribute attribute, HashMap values) {
        String attributeName;
        String operatorSymbol;
        ArrayList<String> listValues = new ArrayList<>();

        attributeName = attribute.getName();
        operatorSymbol = operator.getSymbol();
        Iterator iterator = values.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry value = (Map.Entry) iterator.next();
            listValues.add((String) value.getValue());
        }

        StringBuffer finalList = new StringBuffer();
        finalList.append("(");
        for (int i = 0; i < listValues.size(); i++) {
            boolean numeric = true;
            numeric = listValues.get(i).matches("-?\\d+(\\.\\d+)?");

            if(numeric)
                finalList.append(listValues.get(i));
            else
                finalList.append("'" + listValues.get(i) + "'");

            if (i != listValues.size() - 1) {
                finalList.append(", ");
            }
        }
        finalList.append(")");

        return String.format("%s %s %s",
                attributeName,
                operatorSymbol,
                finalList);
    }
}
