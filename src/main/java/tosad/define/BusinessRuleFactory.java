package tosad.define;

import java.util.ArrayList;
import java.util.List;

public class BusinessRuleFactory implements BusinessRuleFactoryInterface {
    private String Name;
    private int CompareStatus;
    private int OperatorID;
    private List<String> Value;
    private int AttributeID;
    private int SubAttributeID;
    private int BusinessRuleTypeID;

    public BusinessRuleFactory(String ruleName, int compareStatus, int operatorID, List<String> value, int attributeID, int subAttributeID, int businessRuleTypeID) {
        this.Name = ruleName;
        this.CompareStatus = compareStatus;
        this.OperatorID = operatorID;
        this.Value = value;
        this.AttributeID = attributeID;
        this.SubAttributeID = subAttributeID;
        this.BusinessRuleTypeID = businessRuleTypeID;
    }

    @Override
    public BusinessRule buildRule() {
        System.out.println("Checkpoint!!!");
        return new BusinessRule(this.Name, this.CompareStatus, this.OperatorID, this.Value, this.AttributeID, this.SubAttributeID, this.BusinessRuleTypeID);
    }
}
