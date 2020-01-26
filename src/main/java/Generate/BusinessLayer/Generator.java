package Generate.BusinessLayer;

import Generate.BusinessLayer.ruleObjects.Operator;
import Generate.BusinessLayer.RuleTypes.CompareRule;
import Generate.BusinessLayer.RuleTypes.ListRule;
import Generate.BusinessLayer.RuleTypes.RangeRule;
import Generate.DatabaseLayer.GenerateDAOImpl;

import java.util.HashMap;

public class Generator {

    public String generatorInformation(BusinessRule rule, String operation){
        int typeID = rule.getBusinessRuleTypeID();
        Operator operator = GenerateDAOImpl.getOperatorInformation(rule.getOperatorID());
        Attribute attribute = GenerateDAOImpl.getAttributeData(rule.getAttributeID());
        HashMap<Integer, String> values = GenerateDAOImpl.getValues(rule.getRuleID());
        String triggerCode = "";
        String fullCode = "";

        switch(typeID) {
            case 1:
                triggerCode = RangeRule.triggerCodeRangeRule(operator, attribute, values);
                break;
            case 2:
                if(values.isEmpty()) {
                    Attribute subAttribute = GenerateDAOImpl.getAttributeData(rule.getSubAttributeID());
                    triggerCode = CompareRule.triggerCodeSubAttribute(operator, attribute, subAttribute);
                } else {
                    triggerCode = CompareRule.triggerCodeLitValue(operator, attribute, values);
                }
                break;
            case 3:
                values = GenerateDAOImpl.getListValues(rule.getRuleID());
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