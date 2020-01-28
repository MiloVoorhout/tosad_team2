package Generate.BusinessLayer.daoImplementatie;

import Generate.BusinessLayer.BusinessRule.BusinessRule;
import Generate.DatabaseLayer.DatabaseFacade;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BusinessDAOImpl extends DAOFacade {

    public static String getBusinessRuleInfo() throws SQLException{

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseFacade.getInstance().getConnection();
        String query  = "SELECT * FROM TOSAD.BUSINESSRULE";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            obj.put("id", rs.getInt("ID"));
            obj.put("name", rs.getString("NAME"));
            obj.put("comparestatus", rs.getInt("COMPARESTATUS"));
            obj.put("operatorID", rs.getInt("OPERATORID"));
            obj.put("attributeID", rs.getInt("ATTRIBUTEID"));
            obj.put("subAttributeID", rs.getInt("SUBATTRIBUTEID"));
            obj.put("businessRuleTypeID", rs.getInt("BUSINESSRULETYPEID"));
            arr.put(obj);
            obj = new JSONObject();
        }

        return arr.toString();
    }

    public static BusinessRule getBusinessRuleTrigger(int id) throws SQLException {

        Connection conn = DatabaseFacade.getInstance().getConnection();
        String query  = "SELECT * FROM TOSAD.BUSINESSRULE WHERE ID =" + id;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        BusinessRule ruleDefinition = null;

        while (rs.next()) {
            int businessRuleID = rs.getInt("ID");
            String name = rs.getString("NAME");
            int compareStatus = rs.getInt("COMPARESTATUS");
            int operatorID = rs.getInt("OPERATORID");
            int attributeID = rs.getInt("ATTRIBUTEID");
            int subAttributeID = rs.getInt("SUBATTRIBUTEID");
            int businessRuleTypeID = rs.getInt("BUSINESSRULETYPEID");
            int failureType = rs.getInt("FAILURETYPE");
            String failureMessage = rs.getString("FAILUREMESSAGE");
            ruleDefinition = new BusinessRule(businessRuleID, name, compareStatus, operatorID, attributeID, subAttributeID, businessRuleTypeID, failureType, failureMessage);
        }

        return ruleDefinition;
    }

    public static String getMenuItems() throws Exception {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseFacade.getInstance().getConnection();
        String query  = "SELECT ID, NAME FROM TOSAD.BUSINESSRULE";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            obj.put("id", rs.getInt("ID"));
            obj.put("name", rs.getString("NAME"));
            arr.put(obj);
            obj = new JSONObject();
        }

        return arr.toString();
    }

    public static String getContent(int id) throws Exception {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        String failureType = "Informational Warning";

        Connection conn = DatabaseFacade.getInstance().getConnection();
        String query  = "SELECT br.ID, br.name, br.FAILURETYPE, br.FAILUREMESSAGE, o.NAME as OPERATOR, " +
                "A2.NAME AS ATTRIBUTE_NAME, A2.TABLENAME AS ATTRIBUTE_TABLE, B.NAME AS BUSINESSRULETYPE " +
                "FROM TOSAD.BUSINESSRULE br " +
                "INNER JOIN TOSAD.ATTRIBUTE A2 on br.ATTRIBUTEID = A2.ID " +
                "INNER JOIN TOSAD.OPERATOR O on br.OPERATORID = O.ID " +
                "INNER JOIN TOSAD.BUSINESSRULETYPE B on br.BUSINESSRULETYPEID = B.ID " +
                "WHERE br.ID = "+id;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            if (rs.getInt("FAILURETYPE") == 1) failureType = "Error";
            obj.put("id", rs.getInt("ID"));
            obj.put("operator", rs.getString("OPERATOR"));
            obj.put("attributeName", rs.getString("ATTRIBUTE_NAME"));
            obj.put("attributeTable", rs.getString("ATTRIBUTE_TABLE"));
            obj.put("businessRuleType", rs.getString("BUSINESSRULETYPE"));
            obj.put("name", rs.getString("NAME"));
            obj.put("failureType", failureType);
            obj.put("failureMessage", rs.getString("FAILUREMESSAGE"));
            arr.put(obj);
            obj = new JSONObject();

        }
        return arr.toString();
    }
}
