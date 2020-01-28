package Generate.BusinessLayer;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.BusinessLayer.BusinessRule.BusinessRule;
import Generate.BusinessLayer.RuleTypes.RuleTypesFacade;
import Generate.BusinessLayer.daoImplementatie.DAOFacade;
import Generate.BusinessLayer.ruleObjects.Operator;

import java.util.HashMap;

public class Generator {

    public String generatorInformation(BusinessRule rule, String operation, int ferStatus) throws Exception {
        int typeID = rule.getBusinessRuleTypeID();
        Operator operator = DAOFacade.getOperatorInformation(rule.getOperatorID());
        Attribute attribute = DAOFacade.getAttributeData(rule.getAttributeID());
        Attribute subAttribute = DAOFacade.getAttributeData(rule.getSubAttributeID());;
        HashMap<Integer, String> values = DAOFacade.getValues(rule.getRuleID());
        String triggerCode;
        String fullCode;

        switch(typeID) {
            case 1:
                triggerCode = RuleTypesFacade.triggerCodeRangeRule(operator, attribute, values);
                break;
            case 2:
                triggerCode = RuleTypesFacade.triggerCodeLitValue(operator, attribute, values);
                break;
            case 3:
                values = DAOFacade.getListValues(rule.getRuleID());
                triggerCode = RuleTypesFacade.triggerCodeListRule(operator, attribute, values);
                break;
            case 4:
                triggerCode = RuleTypesFacade.triggerCodeInterEntityCompareRule(operator, attribute, subAttribute);
                break;
            case 5:
                triggerCode = RuleTypesFacade.triggerCodeSubAttribute(operator, attribute, subAttribute);
                break;
            case 6:
                triggerCode = RuleTypesFacade.triggerCodeEntityOtherRule(values);
                break;
            default:
                return null;
        }
        fullCode = generateCode(rule, attribute, triggerCode, operation, ferStatus);
        return fullCode;
    }

    public String generateCode(BusinessRule rule, Attribute attribute, String triggerCode, String operation,
                               int ferStatus) {
        int ruleType = rule.getBusinessRuleTypeID();
        String completeTriggerCode;
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
            if (ruleType != 6) {
                trigger = ":new." + triggerCode;
            } else {
                trigger = triggerCode;
            }
        }

        if (ruleType == 6) {
            completeTriggerCode = String.format("CREATE OR REPLACE TRIGGER %s %n" +
                                                "%s" +
                                                "%s" +
                                                "END;",
                    rule.getName(),
                    statement,
                    trigger
            );
        } else {
            completeTriggerCode = String.format("CREATE OR REPLACE TRIGGER %s %n" +
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
        return completeTriggerCode;
    }
}