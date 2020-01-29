package Generate.BusinessLayer.GeneratorTypes;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.BusinessLayer.BusinessRule.BusinessRule;
import Generate.BusinessLayer.RuleTypes.RuleTypesFacade;
import Generate.BusinessLayer.daoImplementatie.DAOFacade;
import Generate.BusinessLayer.ruleObjects.Operator;

import java.util.HashMap;

public class OracleGenerator {

    public static String generatorInformation(BusinessRule rule, String operation, int ferStatus) throws Exception {
        int typeID = rule.getBusinessRuleTypeID();
        Operator operator = DAOFacade.getOperatorInformation(rule.getOperatorID());
        Attribute attribute = DAOFacade.getAttributeData(rule.getAttributeID());
        Attribute subAttribute = DAOFacade.getAttributeData(rule.getSubAttributeID());
        HashMap<Integer, String> values = DAOFacade.getValues(rule.getRuleID());
        String triggerCode;
        String fullCode;

        switch(typeID) {
            case 1:
                triggerCode = RuleTypesFacade.triggerCodeRangeRuleOracle(operator, attribute, values);
                break;
            case 2:
                triggerCode = RuleTypesFacade.triggerCodeLitValueOracle(operator, attribute, values);
                break;
            case 3:
                values = DAOFacade.getListValues(rule.getRuleID());
                triggerCode = RuleTypesFacade.triggerCodeListRuleOracle(operator, attribute, values);
                break;
            case 4:
                triggerCode = RuleTypesFacade.triggerCodeInterEntityCompareRuleOracle(operator, attribute, subAttribute);
                break;
            case 5:
                triggerCode = RuleTypesFacade.triggerCodeSubAttributeOracle(operator, attribute, subAttribute);
                break;
            case 6:
                triggerCode = RuleTypesFacade.triggerCodeEntityOtherRuleOracle(values);
                break;
            default:
                return null;
        }
        fullCode = generateCodeOracle(rule, attribute, triggerCode, operation, ferStatus);
        return fullCode;
    }

    private static String generateCodeOracle(BusinessRule rule, Attribute attribute, String triggerCode,
                                             String operation, int ferStatus) {
        int ruleType = rule.getBusinessRuleTypeID();
        String completeTriggerCode;
        String statement = "";
        String trigger = "";
        operation = operation.replace("/", " OR ");
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
                    trigger);
        } else {
            int raiseNumber = 1000 - rule.getRuleID();
            completeTriggerCode = String.format("CREATE OR REPLACE TRIGGER %s %n" +
                                                "%s" +
                                                "BEGIN %n" +
                                                "IF %s THEN %n" +
                                                "raise_application_error(-20%s, '%s'); %n" +
                                                "END IF; %n" +
                                                "END;",
                    rule.getName(),
                    statement,
                    trigger,
                    raiseNumber,
                    rule.getFailureMessage());
        }
        return completeTriggerCode;
    }

    public static String generateTriggerCode(int id) throws Exception {
        BusinessRule rule = DAOFacade.getBusinessRuleTrigger(id);
        Operator operator = DAOFacade.getOperatorInformation(rule.getOperatorID());
        Attribute attribute = DAOFacade.getAttributeData(rule.getAttributeID());
        Attribute subAttribute = DAOFacade.getAttributeData(rule.getSubAttributeID());
        HashMap<Integer, String> values = DAOFacade.getValues(rule.getRuleID());
        int typeID = rule.getBusinessRuleTypeID();
        String triggerCode;

        switch(typeID) {
            case 1:
                triggerCode = RuleTypesFacade.triggerCodeRangeRuleOracle(operator, attribute, values);
                break;
            case 2:
                triggerCode = RuleTypesFacade.triggerCodeLitValueOracle(operator, attribute, values);
                break;
            case 3:
                values = DAOFacade.getListValues(rule.getRuleID());
                triggerCode = RuleTypesFacade.triggerCodeListRuleOracle(operator, attribute, values);
                break;
            case 4:
                triggerCode = RuleTypesFacade.triggerCodeInterEntityCompareRuleOracle(operator, attribute, subAttribute);
                break;
            case 5:
                triggerCode = RuleTypesFacade.triggerCodeSubAttributeOracle(operator, attribute, subAttribute);
                break;
            case 6:
                triggerCode = RuleTypesFacade.triggerCodeEntityOtherRuleOracle(values);
                break;
            default:
                return null;
        }

        return triggerCode;
    }
}