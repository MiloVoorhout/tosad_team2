package Generate.BusinessLayer.BusinessRule;

import java.util.List;

public class BusinessRuleFactory {
    private int RuleID;
    private String Name;
    private int OperatorID;
    private List<String> Value;
    private int AttributeID;
    private int SubAttributeID;
    private int BusinessRuleTypeID;
    private String FailureMessage;

    public BusinessRuleFactory(int ruleID, String ruleName, int operatorID, int attributeID,
                               int subAttributeID, int businessRuleTypeID, String failureMessage) {
        RuleID = ruleID;
        Name = ruleName;
        OperatorID = operatorID;
        AttributeID = attributeID;
        SubAttributeID = subAttributeID;
        BusinessRuleTypeID = businessRuleTypeID;
        FailureMessage = failureMessage;
    }

    public BusinessRule buildRule() {
        System.out.println("Checkpoint!!!");
        return new BusinessRule(this.RuleID, this.Name, this.OperatorID, this.AttributeID, this.SubAttributeID,
                this.BusinessRuleTypeID, this.FailureMessage);
    }
}
