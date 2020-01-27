package Generate.BusinessLayer;

import Generate.BusinessLayer.RuleTypes.ICMP;
import Generate.BusinessLayer.ruleObjects.Operator;
import Generate.BusinessLayer.RuleTypes.ACMP;
import Generate.BusinessLayer.RuleTypes.ALIS;
import Generate.BusinessLayer.RuleTypes.ARNG;
import Generate.BusinessLayer.RuleTypes.TCMP;

import java.util.HashMap;

public class Generator {

    public String generatorInformation(BusinessRule rule, String operation){
        int typeID = rule.getBusinessRuleTypeID();
        Operator operator = GenerateDAOImpl.getOperatorInformation(rule.getOperatorID());
        Attribute attribute = GenerateDAOImpl.getAttributeData(rule.getAttributeID());
        Attribute subAttribute = GenerateDAOImpl.getAttributeData(rule.getSubAttributeID());;
        HashMap<Integer, String> values = GenerateDAOImpl.getValues(rule.getRuleID());
        String triggerCode = "";
        String fullCode = "";

        switch(typeID) {
            case 1:
                triggerCode = ARNG.triggerCodeRangeRule(operator, attribute, values);
                break;
            case 2:
                triggerCode = ACMP.triggerCodeLitValue(operator, attribute, values);
                break;
            case 3:
                values = GenerateDAOImpl.getListValues(rule.getRuleID());
                triggerCode = ALIS.triggerCodeListRule(operator, attribute, values);
                break;
            case 4:
                triggerCode = ICMP.triggerCodeInterEntityCompareRule(operator, attribute, subAttribute);
                break;
            case 5:
                triggerCode = TCMP.triggerCodeSubAttribute(operator, attribute, subAttribute);
                break;
            default:
                return null;
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