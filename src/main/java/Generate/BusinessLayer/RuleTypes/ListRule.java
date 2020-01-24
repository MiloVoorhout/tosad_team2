package Generate.BusinessLayer.RuleTypes;

import Generate.BusinessLayer.Attribute;
import Define.BusinessLayer.Operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ListRule {

    public static String triggerCodeListRule(Operator operator, Attribute attribute, HashMap values) {
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
