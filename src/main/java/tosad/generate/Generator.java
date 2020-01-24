package tosad.generate;

import tosad.attribute.Attribute;
import Database.DatabaseFacade;
import tosad.define.BusinessRule;
import tosad.define.Operator;
import tosad.generate.type.CompareRule;
import tosad.generate.type.ListRule;
import tosad.generate.type.RangeRule;
import java.util.HashMap;

public class Generator {

    public String generatorInformation(BusinessRule rule, String operation){
        int typeID = rule.getBusinessRuleTypeID();
        Operator operator = DatabaseFacade.getOperatorInformation(rule.getOperatorID());
        Attribute attribute = DatabaseFacade.getAttributeData(rule.getAttributeID());
        HashMap<Integer, String> values = DatabaseFacade.getValues(rule.getRuleID());
        String triggerCode = "";
        String fullCode = "";

        switch(typeID) {
            case 1:
                triggerCode = RangeRule.triggerCodeRangeRule(operator, attribute, values);
                break;
            case 2:
                if(values.isEmpty()) {
                    Attribute subAttribute = DatabaseFacade.getAttributeData(rule.getSubAttributeID());
                    triggerCode = CompareRule.triggerCodeSubAttribute(operator, attribute, subAttribute);
                } else {
                    triggerCode = CompareRule.triggerCodeLitValue(operator, attribute, values);
                }
                break;
            case 3:
                values = DatabaseFacade.getListValues(rule.getRuleID());
                triggerCode = ListRule.triggerCodeListRule(operator, attribute, values);
                break;
            default:
                System.out.println("Ongeldig nummer");
        }
        fullCode = generateCode(rule, attribute, triggerCode, operation);
        return fullCode;
    }

    public String generateCode(BusinessRule rule, Attribute attribute, String triggerCode, String operation) {
        return String.format("CREATE OR REPLACE TRIGGER %s %n" +
                             "BEFORE %s %n" +
                                "ON %s.%s %n" +
                                "FOR EACH ROW %n" +
                             "BEGIN %n" +
                                ":new.%s %n" +
                             "END;",
                    rule.getName(),
                    operation,
                    attribute.getDatabase(),
                    attribute.getTable(),
                    triggerCode
                );
    }
}