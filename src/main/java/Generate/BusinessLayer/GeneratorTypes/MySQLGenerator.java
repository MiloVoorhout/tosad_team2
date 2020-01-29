package Generate.BusinessLayer.GeneratorTypes;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.BusinessLayer.BusinessRule.BusinessRule;
import Generate.BusinessLayer.RuleTypes.RuleTypesFacade;
import Generate.BusinessLayer.daoImplementatie.DAOFacade;
import Generate.BusinessLayer.ruleObjects.Operator;

import java.util.HashMap;

public class MySQLGenerator {

    static String generatorMYSQLInformation(BusinessRule rule, String operation, int ferStatus) throws Exception {
        int typeID = rule.getBusinessRuleTypeID();
        Operator operator = DAOFacade.getOperatorInformation(rule.getOperatorID());
        Attribute attribute = DAOFacade.getAttributeData(rule.getAttributeID());
        Attribute subAttribute = DAOFacade.getAttributeData(rule.getSubAttributeID());
        HashMap<Integer, String> values = DAOFacade.getValues(rule.getRuleID());
        String triggerCode;
        String fullCode;

        switch(typeID) {
            case 1:
                triggerCode = RuleTypesFacade.triggerCodeRangeRuleMYSQL(operator, attribute, values);
                break;
            case 2:
                triggerCode = RuleTypesFacade.triggerCodeLitValueMYSQL(operator, attribute, values);
                break;
            case 3:
                values = DAOFacade.getListValues(rule.getRuleID());
                triggerCode = RuleTypesFacade.triggerCodeListRuleMYSQL(operator, attribute, values);
                break;
            case 4:
                triggerCode = RuleTypesFacade.triggerCodeInterEntityCompareRuleMYSQL(operator, attribute, subAttribute);
                break;
            case 5:
                triggerCode = RuleTypesFacade.triggerCodeSubAttributeMYSQL(operator, attribute, subAttribute);
                break;
            case 6:
                triggerCode = RuleTypesFacade.triggerCodeEntityOtherRuleMYSQL(values);
                break;
            default:
                return null;
        }

        fullCode = generateCodeMYSQL(rule, attribute, triggerCode, operation, ferStatus);
        return fullCode;
    }

    private static String generateCodeMYSQL(BusinessRule rule, Attribute attribute, String triggerCode,
                                            String operation, int ferStatus) {
        int ruleType = rule.getBusinessRuleTypeID();
        String completeTriggerCode;
        String statement = "";
        String trigger = "";
        if (ferStatus == 0) {
            statement = String.format(  "%s %n" +
                            "ON `%s`.%s %n",
                    operation,
                    attribute.getDatabase(),
                    attribute.getTable());
            trigger = triggerCode;
        } else if (ferStatus == 1) {
            operation = operation.substring(0, operation.length() - 12);
            statement = String.format(  "%s %n" +
                            "ON `%s`.%s %n" +
                            "FOR EACH ROW %n",
                    operation,
                    attribute.getDatabase(),
                    attribute.getTable());
            if (ruleType != 6) {
                trigger = "NEW." + triggerCode;
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
                            "signal sqlstate '-20%s' set message_text = '%s'; %n" +
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
}
