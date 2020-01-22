package tosad.generate;

import tosad.attribute.Attribute;
import tosad.define.BusinessRule;
import tosad.define.BusinessRuleFacade;
import tosad.generate.type.CompareRule;
import tosad.generate.type.ListRule;
import tosad.generate.type.RangeRule;

public class Generator {

    public void getAttributeData(){

    }
    public void generateCode(BusinessRule rule, Attribute attribute){

        int typeID = rule.getBusinessRuleTypeID();
        switch(typeID) {
            case 1:
                RangeRule.getSymbol(attribute.getName(), attribute.getTabel(), attribute.getDatabase());
                break;
            case 2:
                CompareRule.getSymbol(attribute.getName(), attribute.getTabel(), attribute.getDatabase());
                break;
            case 3:
                ListRule.getSymbol(attribute.getName(), attribute.getTabel(), attribute.getDatabase());
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

}