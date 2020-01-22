package tosad.define;

import java.util.ArrayList;
import java.util.List;

public class BusinessRule implements BusinessRulePrototype, Cloneable {
    private int CompareStatus;
    private int OperatorID;
    private List<String> Value;
    private int AttributeID;
    private int SubAttributeID;
    private int BusinessRuleTypeID;


    public BusinessRule(int compareStatus, int operatorID, int attributeID, int subAttributeID, int businessRuleTypeID) {
        CompareStatus = compareStatus;
        OperatorID = operatorID;
        AttributeID = attributeID;
        SubAttributeID = subAttributeID;
        BusinessRuleTypeID = businessRuleTypeID;
    }

    public BusinessRule(int compareStatus, int operatorID, List<String> value, int attributeID, int subAttributeID, int businessRuleTypeID) {
        CompareStatus = compareStatus;
        OperatorID = operatorID;
        Value = value;
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

    public List<String> getValue() { return Value; }

    public void setValue(List<String> value) { Value = value; }

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
}