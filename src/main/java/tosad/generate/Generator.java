package tosad.generate;

import tosad.attribute.Attribute;
import tosad.define.BusinessRule;
import tosad.generate.type.CompareRule;
import tosad.generate.type.ListRule;
import tosad.generate.type.RangeRule;

public class Generator {

    public void generateCode(BusinessRule rule){

        int typeID = rule.getBusinessRuleTypeID();
        switch(typeID) {
            case 1:
                RangeRule.getSymbol(rule.getBusinessRuleTypeID(), rule.getOperatorID());
                break;
            case 2:
                CompareRule.getSymbol(rule.getBusinessRuleTypeID(), rule.getOperatorID());
                break;
            case 3:
                ListRule.getSymbol(rule.getBusinessRuleTypeID(), rule.getOperatorID());
                break;
            default:
                System.out.println("Ongeldig nummer");
        }

//        System.out.println("CREATE OR REPLACE TRIGGER Testnaam " +
//                "BEFORE INSERT ON " + "Tabelnaam" + "FOR EACH ROW " +
//                "BEGIN " +
//                ":new. " + rule.getAttributeID() + " " + rule.getOperatorID() + " " + rule.getValue() + " AND " + rule.getValue() +
//                "END;");
    }

    public void getAttributeData(){

    }

}