//package Define.BusinessLayer.BusinessRule;
//
//import java.util.List;
//
//public class BusinessRule implements BusinessRulePrototype, Cloneable {
////    private int RuleID;
//    private String Name;
//    private int CompareStatus;
//    private int OperatorID;
//    private List<String> Value;
//    private int AttributeID;
//    private int SubAttributeID;
//    private int BusinessRuleTypeID;
//
////    public BusinessRule(String ruleName, int compareStatus, int operatorID, int attributeID, int subAttributeID, int businessRuleTypeID) {
////        Name = ruleName;
////        CompareStatus = compareStatus;
////        OperatorID = operatorID;
////        AttributeID = attributeID;
////        SubAttributeID = subAttributeID;
////        BusinessRuleTypeID = businessRuleTypeID;
////    }
//
//    public BusinessRule(String ruleName, int compareStatus, int operatorID, List<String> value, int attributeID,
//                        int subAttributeID, int businessRuleTypeID) {
//        Name = ruleName;
//        CompareStatus = compareStatus;
//        OperatorID = operatorID;
//        Value = value;
//        AttributeID = attributeID;
//        SubAttributeID = subAttributeID;
//        BusinessRuleTypeID = businessRuleTypeID;
//    }
//
////    public BusinessRule(int ruleID, String ruleName, int compareStatus, int operatorID, int attributeID, int subAttributeID, int businessRuleTypeID) {
////        RuleID = ruleID;
////        Name = ruleName;
////        CompareStatus = compareStatus;
////        OperatorID = operatorID;
////        AttributeID = attributeID;
////        SubAttributeID = subAttributeID;
////        BusinessRuleTypeID = businessRuleTypeID;
////    }
////
////    public BusinessRule(int ruleID, String ruleName, int compareStatus, int operatorID, int attributeID, int businessRuleTypeID) {
////        RuleID = ruleID;
////        Name = ruleName;
////        CompareStatus = compareStatus;
////        OperatorID = operatorID;
////        AttributeID = attributeID;
////        BusinessRuleTypeID = businessRuleTypeID;
////    }
//
//    @Override
//    public Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
//
////    public int setRuleID(int ruleID) {return RuleID = ruleID;}
//
//    public String getName() {return Name;}
//
//    public void setName(String ruleName) {Name = ruleName;}
//
//    public List<String> getValue() { return Value; }
//
//    public void setValue(List<String> value) { Value = value; }
//
//}