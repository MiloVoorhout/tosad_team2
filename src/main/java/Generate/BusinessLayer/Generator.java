package Generate.BusinessLayer;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.BusinessLayer.BusinessRule.BusinessRule;
import Generate.BusinessLayer.RuleTypes.ICMP;
import Generate.BusinessLayer.ruleObjects.Operator;
import Generate.BusinessLayer.RuleTypes.ACMP;
import Generate.BusinessLayer.RuleTypes.ALIS;
import Generate.BusinessLayer.RuleTypes.ARNG;
import Generate.BusinessLayer.RuleTypes.TCMP;
import Generate.BusinessLayer.daoImplementatie.AttributeDAOImpl;
import Generate.BusinessLayer.daoImplementatie.OperatorDAOImpl;
import Generate.BusinessLayer.daoImplementatie.ValueDAOImpl;

import java.util.HashMap;

public class Generator {

    public String generatorInformation(BusinessRule rule, String operation, int ferStatus) throws Exception {
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
        fullCode = generateCode(rule, attribute, triggerCode, operation, ferStatus);
        return fullCode;
    }

    public String generateCode(BusinessRule rule, Attribute attribute, String triggerCode, String operation, int ferStatus) {
        String statement = "";
        String trigger = "";
        if (ferStatus == 0) {
            statement = String.format(  "%s %n" +
                                        "ON %s.%s %n",
                                        operation,
                                        attribute.getDatabase(),
                                        attribute.getTable());
            trigger = triggerCode;
        } else if (ferStatus == 1) {
            operation = operation.substring(0, operation.length() - 12);
            statement = String.format(  "%s %n" +
                                        "ON %s.%s %n" +
                                        "FOR EACH ROW %n",
                                        operation,
                                        attribute.getDatabase(),
                                        attribute.getTable());
            trigger = ":new." + triggerCode;
        }

        return String.format("CREATE OR REPLACE TRIGGER %s %n" +
                        "%s" +
                        "BEGIN %n" +
                        "IF %s THEN %n" +
                        "raise_application_error(-20001, '%s') %n" +
                        "END IF; %n" +
                        "END;",
                rule.getName(),
                statement,
                trigger,
                rule.getFailureMessage()
        );
    }
}