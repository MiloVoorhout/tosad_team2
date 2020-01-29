package Generate.BusinessLayer.daoImplementatie;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.BusinessLayer.BusinessRule.BusinessRule;
import Generate.BusinessLayer.ruleObjects.Operator;
import Generate.DatabaseLayer.DatabaseFacade;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DAOFacade {

    // AttributeDAOImpl functies
    public static Attribute getAttributeData(int AttributeID) {

        return AttributeDAOImpl.getAttributeData(AttributeID);
    }

    public static String getSubAttributeRule(int subAttributeID) throws Exception{

        return AttributeDAOImpl.getSubAttributeRule(subAttributeID);
    }


    // BusinessDAOImpl functies
    public static String getBusinessRuleInfo() throws SQLException {

        return BusinessDAOImpl.getBusinessRuleInfo();
    }

    public static BusinessRule getBusinessRuleTrigger(int id) throws SQLException {

        return BusinessDAOImpl.getBusinessRuleTrigger(id);
    }

    public static String getMenuItems() throws Exception {

        return BusinessDAOImpl.getMenuItems();
    }

    public static String getContent(int id) throws Exception {

        return BusinessDAOImpl.getContent(id);
    }

    public static ArrayList<Integer> getTableRules(String tableName) throws Exception {

        return BusinessDAOImpl.getTableRules(tableName);
    }

    public static String executeTrigger(String triggerCode) throws SQLException {

        return BusinessDAOImpl.executeTrigger(triggerCode);
    }


    // OperatorDAOImpl functies
    public static Operator getOperatorInformation(int OperatorID) throws Exception {

        return OperatorDAOImpl.getOperatorInformation(OperatorID);
    }


    // ValueDAOImpl functies
    public static HashMap<Integer, String> getValues(int id) throws Exception {

        return ValueDAOImpl.getValues(id);
    }

    public static HashMap<Integer, String> getListValues(int id) throws Exception {

        return ValueDAOImpl.getListValues(id);
    }

    public static String getRuleValues(int ID) throws Exception{

        return ValueDAOImpl.getRuleValues(ID);
    }

    public static String getBusinessRuleValue(int id) throws Exception {

        return ValueDAOImpl.getBusinessRuleValue(id);
    }
}
