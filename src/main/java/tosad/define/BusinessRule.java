package tosad.define;

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
public class BusinessRule implements BusinessRulePrototype, Cloneable {
    private int CompareStatus;
    private int OperatorID;
    private int LitValue;
    private int MinValue;
    private int MaxValue;
    private int AttributeID;
    private int SubAttributeID;
    private int BusinessRuleTypeID;

    public BusinessRule(int compareStatus, int operatorID, int litValue, int minValue, int maxValue, int attributeID, int subAttributeID, int businessRuleTypeID) {
        CompareStatus = compareStatus;
        OperatorID = operatorID;
        LitValue = litValue;
        MinValue = minValue;
        MaxValue = maxValue;
        AttributeID = attributeID;
        SubAttributeID = subAttributeID;
        BusinessRuleTypeID = businessRuleTypeID;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getCompareStatus() {
        return CompareStatus;
    }

    public void setCompareStatus(int compareStatus) {
        CompareStatus = compareStatus;
    }

    public int getOperatorID() {
        return OperatorID;
    }

    public void setOperatorID(int operatorID) {
        OperatorID = operatorID;
    }

    public int getLitValue() {
        return LitValue;
    }

    public void setLitValue(int litValue) {
        LitValue = litValue;
    }

    public int getMinValue() {
        return MinValue;
    }

    public void setMinValue(int minValue) {
        MinValue = minValue;
    }

    public int getMaxValue() {
        return MaxValue;
    }

    public void setMaxValue(int maxValue) {
        MaxValue = maxValue;
    }

    public int getAttributeID() {
        return AttributeID;
    }

    public void setAttributeID(int attributeID) {
        AttributeID = attributeID;
    }

    public int getSubAttributeID() {
        return SubAttributeID;
    }

    public void setSubAttributeID(int subAttributeID) {
        SubAttributeID = subAttributeID;
    }

    public int getBusinessRuleTypeID() {
        return BusinessRuleTypeID;
    }

    public void setBusinessRuleTypeID(int businessRuleTypeID) {
        BusinessRuleTypeID = businessRuleTypeID;
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
