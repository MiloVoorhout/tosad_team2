package Define.BusinessLayer.daoImplementatie;

import Define.DatabaseLayer.DatabaseFacade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class InsertDAOImpl extends DAOFacade {



    public static String createRule(int rule_type_select, String rule_name, String tableSelect, String attributeSelect,
                                    int operator, int validationFailureSeverity, String failureMessage, int minimumValue,
                                    int maximumValue, int compareWith, String value, String interEntityTable,
                                    String listValues) throws Exception {

        Connection conn = DatabaseFacade.getInstance().getConnection();
        int lastIDAttribute = 0;
        int lastIDBusinessRule = 0;
        int lastIDSubAttribute = 0;
        String databaseName = "VBMG";

        // Create attribute
        String query_Attribute = "INSERT INTO TOSAD.ATTRIBUTE(NAME, TABLENAME, DATABASENAME) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query_Attribute);
        stmt.setString(1, attributeSelect);
        stmt.setString(2, tableSelect);
        stmt.setString(3, databaseName);
        stmt.execute();

        // Set the ID from the inserted attribute
        String query_LastIDAttribute  = "SELECT * FROM TOSAD.ATTRIBUTE where rowid=(select max(rowid) from TOSAD.ATTRIBUTE)";
        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt2.executeQuery(query_LastIDAttribute);
        while(rs.next()) {
            lastIDAttribute = rs.getInt("ID");
        }

        // If Rule Type = RANGE
        if (rule_type_select == 1) {

            // Insert new business rule
            String query_BusinessRule = "INSERT INTO TOSAD.BUSINESSRULE(OPERATORID, ATTRIBUTEID, BUSINESSRULETYPEID, NAME, FAILURETYPE, FAILUREMESSAGE) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query_BusinessRule);
            stmt.setInt(1, operator);
            stmt.setInt(2, lastIDAttribute);
            stmt.setInt(3, 1);
            stmt.setString(4, rule_name);
            stmt.setInt(5, validationFailureSeverity);
            stmt.setString(6, failureMessage);
            stmt.execute();

            // Get ID from the inserted Business Rule
            String query_LastIDBusinessRule  = "SELECT * FROM TOSAD.BUSINESSRULE where rowid=(select max(rowid) from TOSAD.BUSINESSRULE)";
            stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query_LastIDBusinessRule);
            while(rs2.next()) {
                lastIDBusinessRule = rs2.getInt("ID");
            }

            // Create a min value for Range
            String query_minValue = "INSERT INTO TOSAD.VALUE(VALUE, TYPE, BUSINESSRULEID) VALUES(?, ?, ?)";
            stmt = conn.prepareStatement(query_minValue);
            stmt.setInt(1, minimumValue);
            stmt.setInt(2, 1);
            stmt.setInt(3, lastIDBusinessRule);
            stmt.execute();

            // Create a max value for Range
            String query_maxValue = "INSERT INTO TOSAD.VALUE(VALUE, TYPE, BUSINESSRULEID) VALUES(?, ?, ?)";
            stmt = conn.prepareStatement(query_maxValue);
            stmt.setInt(1, maximumValue);
            stmt.setInt(2, 2);
            stmt.setInt(3, lastIDBusinessRule);
            stmt.execute();
        }

        // If Business Rule = COMPARE
        if (rule_type_select == 2) {

            if (interEntityTable != null) tableSelect = interEntityTable;

            // Create a new (sub)attribute
            String query_SubAttribute = "INSERT INTO TOSAD.ATTRIBUTE(NAME, TABLENAME, DATABASENAME) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query_SubAttribute);
            stmt.setString(1, value);
            stmt.setString(2, tableSelect);
            stmt.setString(3, databaseName);
            stmt.execute();

            // Get the ID from the inserted (sub)attribute
            String query_LastIDSubAttribute  = "SELECT * FROM TOSAD.ATTRIBUTE where rowid=(select max(rowid) from TOSAD.ATTRIBUTE)";
            stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query_LastIDSubAttribute);
            while(rs2.next()) {
                lastIDSubAttribute = rs2.getInt("ID");
            }

            // Insert a new Business Rule
            String query_BusinessRule = "INSERT INTO TOSAD.BUSINESSRULE(COMPARESTATUS, OPERATORID, ATTRIBUTEID, SUBATTRIBUTEID, BUSINESSRULETYPEID, NAME, FAILURETYPE, FAILUREMESSAGE) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query_BusinessRule);
            stmt.setInt(1, compareWith);
            stmt.setInt(2, operator);
            stmt.setInt(3, lastIDAttribute);
            stmt.setInt(4, lastIDSubAttribute);
            stmt.setInt(5, compareWith);
            stmt.setString(6, rule_name);
            stmt.setInt(7, validationFailureSeverity);
            stmt.setString(8, failureMessage);
            stmt.execute();

            // Get the ID from the inserted Business Rule
            String query_LastIDBusinessRule  = "SELECT * FROM TOSAD.BUSINESSRULE where rowid=(select max(rowid) from TOSAD.BUSINESSRULE)";
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery(query_LastIDBusinessRule);
            while(rs3.next()) {
                lastIDBusinessRule = rs3.getInt("ID");
            }

            // Insert a new value
            String query_minValue = "INSERT INTO TOSAD.VALUE(VALUE, TYPE, BUSINESSRULEID) VALUES(?, ?, ?)";
            stmt = conn.prepareStatement(query_minValue);
            stmt.setString(1, value);
            stmt.setInt(2, 3);
            stmt.setInt(3, lastIDBusinessRule);
            stmt.execute();
        }

        // If rule type = LIST
        if (rule_type_select == 3) {

            // Insert new business rule
            String query_BusinessRule = "INSERT INTO TOSAD.BUSINESSRULE(OPERATORID, ATTRIBUTEID, BUSINESSRULETYPEID, NAME, FAILURETYPE, FAILUREMESSAGE) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query_BusinessRule);
            stmt.setInt(1, operator);
            stmt.setInt(2, lastIDAttribute);
            stmt.setInt(3, 3);
            stmt.setString(4, rule_name);
            stmt.setInt(5, validationFailureSeverity);
            stmt.setString(6, failureMessage);
            stmt.execute();

            // Get ID from the inserted Business Rule
            String query_LastIDBusinessRule  = "SELECT * FROM TOSAD.BUSINESSRULE where rowid=(select max(rowid) from TOSAD.BUSINESSRULE)";
            stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery(query_LastIDBusinessRule);
            while(rs2.next()) {
                lastIDBusinessRule = rs2.getInt("ID");
            }

            // Make list of values of all list items
            String[] listItems = listValues.split("\\s*,\\s*");

            // Insert them separately
            for (String item : listItems) {
                String query_minValue = "INSERT INTO TOSAD.VALUE(VALUE, TYPE, BUSINESSRULEID) VALUES(?, ?, ?)";
                stmt = conn.prepareStatement(query_minValue);
                stmt.setString(1, item);
                stmt.setInt(2, 4);
                stmt.setInt(3, lastIDBusinessRule);
                stmt.execute();
            }
        }
        return "true";
    }
}
