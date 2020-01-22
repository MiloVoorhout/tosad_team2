package tosad.define;

import java.util.ArrayList;
import java.util.List;

public class BusinessRuleFactory implements BusinessRuleFactoryInterface {
    private int CompareStatus;
    private int OperatorID;
    private List<String> Value;
    private int AttributeID;
    private int SubAttributeID;
    private int BusinessRuleTypeID;

    public BusinessRuleFactory(int compareStatus, int operatorID, List<String> value, int attributeID, int subAttributeID, int businessRuleTypeID) {
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
        return new BusinessRule(this.CompareStatus, this.OperatorID, this.Value, this.AttributeID, this.SubAttributeID, this.BusinessRuleTypeID);
    }
}
