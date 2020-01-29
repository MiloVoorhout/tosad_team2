package Generate.BusinessLayer.BusinessRule;

import java.util.List;

public class BusinessRule {
    private int RuleID;
    private String Name;
    private int OperatorID;
    private int AttributeID;
    private int SubAttributeID;
    private int BusinessRuleTypeID;
    private String FailureMessage;

    public BusinessRule(int ruleID, String ruleName, int operatorID, int attributeID,
                        int subAttributeID, int businessRuleTypeID, String failureMessage) {
        RuleID = ruleID;
        Name = ruleName;
        OperatorID = operatorID;
        AttributeID = attributeID;
        SubAttributeID = subAttributeID;
        BusinessRuleTypeID = businessRuleTypeID;
        FailureMessage = failureMessage;
    }

    public int getRuleID() {return RuleID;}

    public String getName() {return Name;}

    public void setName(String ruleName) {Name = ruleName;}

    public int getOperatorID() { return OperatorID; }

    public int getAttributeID() { return AttributeID; }

    public int getSubAttributeID() {
        return SubAttributeID;
    }

    public int getBusinessRuleTypeID() {return BusinessRuleTypeID; }

    public String getFailureMessage() { return FailureMessage; }

}