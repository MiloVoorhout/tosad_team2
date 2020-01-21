package tosad.define;

public class BusinessRuleFactory implements BusinessRuleFactoryInterface {
    private int CompareStatus;
    private int OperatorID;
    private int LitValue;
    private int MinValue;
    private int MaxValue;
    private int AttributeID;
    private int SubAttributeID;
    private int BusinessRuleTypeID;

    BusinessRuleFactory(int compareStatus, int operatorID, int litValue, int minValue, int maxValue, int attributeID, int subAttributeID, int businessRuleTypeID) {
        this.CompareStatus = compareStatus;
        this.OperatorID = operatorID;
        this.LitValue = litValue;
        this.MinValue = minValue;
        this.MaxValue = maxValue;
        this.AttributeID = attributeID;
        this.SubAttributeID = subAttributeID;
        this.BusinessRuleTypeID = businessRuleTypeID;
    }

    @Override
    public BusinessRule buildRule() {
        return new BusinessRule(this.CompareStatus, this.OperatorID, this.LitValue, this.MinValue, this.MaxValue, this.AttributeID, this.SubAttributeID, this.BusinessRuleTypeID);
    }
}
