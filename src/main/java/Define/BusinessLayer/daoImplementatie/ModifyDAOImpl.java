package Define.BusinessLayer.daoImplementatie;

import Define.DatabaseLayer.DatabaseFacade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ModifyDAOImpl extends DAOFacade {

    public static String modifyBusinessRule(int ruleId, int rule_type_select, String rule_name, String tableSelect,
                                            String attributeSelect, int operator, String failureMessage,
                                            int minimumValue, int maximumValue, String value, String interEntityTable,
                                            String listValues) throws Exception {

        Connection conn = DatabaseFacade.getInstance().getConnection();
        int attributeId = 0;
        int subAttributeId = 0;
        String databaseName = "VBMG";

        // Get attribute data
        String attributeIds  = "SELECT * FROM TOSAD.BUSINESSRULE where id = " + ruleId;
        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt2.executeQuery(attributeIds);
        while(rs.next()) {
            attributeId = rs.getInt("ATTRIBUTEID");
            subAttributeId = rs.getInt("SUBATTRIBUTEID");
        }

        // Update attribute
        String updateAttribute = "UPDATE TOSAD.ATTRIBUTE SET NAME = ? WHERE ID = " + attributeId;
        PreparedStatement stmt = conn.prepareStatement(updateAttribute);
        stmt.setString(1, attributeSelect);
        stmt.execute();


        System.out.println(operator);

        // Update BusinessRule
        String updateBusinessRule = "UPDATE TOSAD.BUSINESSRULE SET OPERATORID = ?, NAME = ? WHERE ID = " + ruleId;
        stmt = conn.prepareStatement(updateBusinessRule);
        stmt.setInt(1, operator);
        stmt.setString(2, rule_name);
        stmt.execute();

        rule_type_select = 1;

        // If Business Rule = RANGE
        if (rule_type_select == 1) {
            System.out.println("hell yeh!");
            System.out.println(minimumValue);
            System.out.println(maximumValue);
            System.out.println(maximumValue);
            System.out.println(ruleId);
            // Create a min value for Range
            String query_minValue = "INSERT INTO TOSAD.VALUE(VALUE, TYPE, BUSINESSRULEID) VALUES(?, ?, ?)";
            stmt = conn.prepareStatement(query_minValue);
            stmt.setInt(1, minimumValue);
            stmt.setInt(2, 1);
            stmt.setInt(3, ruleId);
            stmt.execute();

            // Create a max value for Range
            String query_maxValue = "INSERT INTO TOSAD.VALUE(VALUE, TYPE, BUSINESSRULEID) VALUES(?, ?, ?)";
            stmt = conn.prepareStatement(query_maxValue);
            stmt.setInt(1, maximumValue);
            stmt.setInt(2, 2);
            stmt.setInt(3, ruleId);
            stmt.execute();
        }

        // If Business Rule = COMPARE
        if (rule_type_select == 2) {

            // Insert a new value
            String query_minValue = "INSERT INTO TOSAD.VALUE(VALUE, TYPE, BUSINESSRULEID) VALUES(?, ?, ?)";
            stmt = conn.prepareStatement(query_minValue);
            stmt.setString(1, value);
            stmt.setInt(2, 3);
            stmt.setInt(3, ruleId);
            stmt.execute();
        }

        // If rule type = LIST
        if (rule_type_select == 3) {

            // Make list of values of all list items
            String[] listItems = listValues.split("\\s*,\\s*");

            // Insert them separately
            for (String item : listItems) {
                String query_minValue = "INSERT INTO TOSAD.VALUE(VALUE, TYPE, BUSINESSRULEID) VALUES(?, ?, ?)";
                stmt = conn.prepareStatement(query_minValue);
                stmt.setString(1, item);
                stmt.setInt(2, 4);
                stmt.setInt(3, ruleId);
                stmt.execute();
            }
        }

//        if (subAttributeId == 0){
//            String delete = "DELETE FROM TOSAD.VALUE WHERE BUSINESSRULEID = " + ruleId;
//            stmt = conn.prepareStatement(delete);
//            stmt.execute();
//        } else if (subAttributeId != 0) {
//
//            if (interEntityTable != null) tableSelect = interEntityTable;
//
//            String updateSubAttribute = "UPDATE TOSAD.ATTRIBUTE SET NAME = ?, TABLENAME = ?, DATABASENAME = ? WHERE ID = " + subAttributeId;
//            stmt = conn.prepareStatement(updateSubAttribute);
//            stmt.setString(1, value);
//            stmt.setString(2, tableSelect);
//            stmt.setString(3, databaseName);
//            stmt.execute();
//        }

        return "true";
    }
}
