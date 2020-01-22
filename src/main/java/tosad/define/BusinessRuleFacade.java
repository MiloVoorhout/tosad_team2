package tosad.define;

import java.io.IOException;
import java.util.List;

public class BusinessRuleFacade {
    private BusinessRuleFactory businessRuleFactory;
    private int CompareStatus;
    private int OperatorID;
    private List<String> Value;
    private int AttributeID;
    private int SubAttributeID;
    private int BusinessRuleTypeID;

    public BusinessRuleFacade(int compareStatus, int operatorID, List<String> value, int attributeID, int subAttributeID, int businessRuleTypeID) {
        this.CompareStatus = compareStatus;
        this.OperatorID = operatorID;
        this.Value = value;
        this.AttributeID = attributeID;
        this.SubAttributeID = subAttributeID;
        this.BusinessRuleTypeID = businessRuleTypeID;
        this.businessRuleFactory = new BusinessRuleFactory(this.CompareStatus, this.OperatorID, this.Value, this.AttributeID, this.SubAttributeID, this.BusinessRuleTypeID);
    }

    public boolean createBusinessRule() {
        BusinessRule businessRule = businessRuleFactory.buildRule();

        return true;
    }

}
