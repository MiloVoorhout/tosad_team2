package Generate.BusinessLayer;

import Generate.BusinessLayer.RuleTypes.ICMP;
import Generate.BusinessLayer.ruleObjects.Operator;
import Generate.BusinessLayer.RuleTypes.ACMP;
import Generate.BusinessLayer.RuleTypes.ALIS;
import Generate.BusinessLayer.RuleTypes.ARNG;
import Generate.BusinessLayer.RuleTypes.TCMP;
import Generate.daoImplementatie.AttributeDAOImpl;
import Generate.daoImplementatie.OperatorDAOImpl;
import Generate.daoImplementatie.ValueDAOImpl;

import java.util.HashMap;

public class Generator {

    public String generatorInformation(BusinessRule rule, String operation){
        int typeID = rule.getBusinessRuleTypeID();
        Operator operator = OperatorDAOImpl.getOperatorInformation(rule.getOperatorID());
        Attribute attribute = AttributeDAOImpl.getAttributeData(rule.getAttributeID());
        Attribute subAttribute = AttributeDAOImpl.getAttributeData(rule.getSubAttributeID());;
        HashMap<Integer, String> values = ValueDAOImpl.getValues(rule.getRuleID());
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
                values = ValueDAOImpl.getListValues(rule.getRuleID());
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
                                "IF :new.%s THEN %n" +
                                    "raise_application_error(-20001, '%s' %n" +
                                "END IF" +
                             "END;",
                    rule.getName(),
                    operation,
                    attribute.getDatabase(),
                    attribute.getTable(),
                    triggerCode,
                    rule.getFailureMessage()
                );
    }
}