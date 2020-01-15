package tosad.define;

import java.io.IOException;

public class BusinessRuleFacade {
    private BusinessRuleFactory businessRuleFactory;
    private int CompareStatus;
    private int OperatorID;
    private int LitValue;
    private int MinValue;
    private int MaxValue;
    private int AttributeID;
    private int SubAttributeID;
    private int BusinessRuleTypeID;

    public BusinessRuleFacade(int compareStatus, int operatorID, int litValue, int minValue, int maxValue, int attributeID, int subAttributeID, int businessRuleTypeID) {
        this.CompareStatus = compareStatus;
        this.OperatorID = operatorID;
        this.LitValue = litValue;
        this.MinValue = minValue;
        this.MaxValue = maxValue;
        this.AttributeID = attributeID;
        this.SubAttributeID = subAttributeID;
        this.BusinessRuleTypeID = businessRuleTypeID;
        this.businessRuleFactory = new BusinessRuleFactory(this.CompareStatus, this.OperatorID, this.LitValue, this.MinValue, this.MaxValue, this.AttributeID, this.SubAttributeID, this.BusinessRuleTypeID);
    }

    public boolean createBusinessRule() {
        BusinessRule businessRule = businessRuleFactory.buildRule();


        return true;
    }

}
