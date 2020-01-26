package Generate.DatabaseLayer;

import Generate.BusinessLayer.Attribute;
import Generate.BusinessLayer.ruleObjects.Operator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class GenerateDAOImpl extends Database.DatabaseConnection{

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
