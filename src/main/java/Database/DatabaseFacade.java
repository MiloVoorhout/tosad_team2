package Database;

import tosad.attribute.Attribute;
import tosad.define.BusinessRule;
import tosad.define.Operator;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseFacade extends DatabaseConnection {

    public static void insertNewRule() throws SQLException {

        ArrayList<String> newRuleList = new ArrayList<String>();
        Connection conn = getConnection();

        newRuleList.add("1"); //           0 Rule type (1 = Range, 2 = Compare)
        newRuleList.add("Test");//         1 Name
        newRuleList.add("1"); //           2 Attribute
        newRuleList.add("1"); //           3 Operator (1 = Between, 2 = NotBetween)
        newRuleList.add("5"); //           4 MinValue
        newRuleList.add("20"); //          5 MaxValue
        int lastInsertedID = 0;

        try {
            String query  = "SELECT ID FROM ( SELECT a.*, max(ID) OVER () AS max_pk FROM TOSAD.BUSINESSRULETYPE a ) WHERE ID = max_pk";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                lastInsertedID = rs.getInt("ID");
            }
            System.out.println(lastInsertedID);
        } catch (Exception e) {
                e.printStackTrace();
        }

        try {
            // Create a new Business Rule Type
            String query = "INSERT INTO TOSAD.BUSINESSRULETYPE(NAME, CATEGORYID) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newRuleList.get(1));
            stmt.setInt(2, Integer.parseInt(newRuleList.get(0)));
            stmt.execute();

            System.out.println(newRuleList.get(2));

            String query2 = "INSERT INTO TOSAD.BUSINESSRULE(OPERATORID, MINVALUE, MAXVALUE, ATTRIBUTEID, BUSINESSRULETYPEID) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query2);
            stmt.setInt(1, Integer.parseInt(newRuleList.get(3)));
            stmt.setInt(2, Integer.parseInt(newRuleList.get(4)));
            stmt.setInt(3, Integer.parseInt(newRuleList.get(5)));
            stmt.setInt(4, Integer.parseInt(newRuleList.get(2)));
            stmt.setInt(5, (lastInsertedID+1));
            stmt.execute();

            closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getData(int id) throws SQLException {
        String ruleName = "";
        int compareStatus = 0;
        int operatorID = 0;
        int attributeID = 0;
        int subAttributeID = 0;
        int businessRuleTypeID = 0;

        try {
            Connection conn = getConnection();
            String query  = "SELECT * FROM TOSAD.BUSINESSRULE WHERE ID = "+id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ruleName = rs.getString("NAME");
                compareStatus = rs.getInt("COMPARESTATUS");
                operatorID = rs.getInt("OPERATORID");
                attributeID = rs.getInt("ATTRIBUTEID");
                subAttributeID = rs.getInt("SUBATTRIBUTEID");
                businessRuleTypeID = rs.getInt("BUSINESSRULETYPEID");
            }
            BusinessRule newRule = new BusinessRule(ruleName ,compareStatus, operatorID, attributeID, subAttributeID, businessRuleTypeID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Attribute getAttributeData(int AttributeID) {
        String name = "";
        String table = "";
        String database = "";
        Attribute newAttribute = null;

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM TOSAD.ATTRIBUTE WHERE ID = " + AttributeID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("NAME");
                table = rs.getString("TABEL");
                database = rs.getString("DATABASE");
            }
            newAttribute = new Attribute(name, table, database);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newAttribute;
    }

    public static Operator getOperatorInformation(int OperatorID) {
        String name = "";
        String symbol = "";
        Operator newOperator = null;

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM TOSAD.OPERATOR WHERE ID = " + OperatorID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("NAME");
                symbol = rs.getString("SYMBOL");
            }
            newOperator = new Operator(name, symbol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newOperator;
    }

    public static HashMap<Integer, String> getValues(int id) {
        String value;
        int type;
        HashMap<Integer, String> values = new HashMap<>();

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM TOSAD.VALUE WHERE BUSINESSRULEID = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                value = rs.getString("VALUE");
                type = rs.getInt("TYPE");
                values.put(type, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }

    public static HashMap<Integer, String> getListValues(int id) {
        String value;
        int type;
        HashMap<Integer, String> values = new HashMap<>();

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM TOSAD.VALUE WHERE BUSINESSRULEID = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                value = rs.getString("VALUE");
                id = rs.getInt("ID");
                values.put(id, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }
}
