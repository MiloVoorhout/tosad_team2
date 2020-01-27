package Generate.daoImplementatie;

import Generate.DatabaseLayer.DatabaseConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class ValueDAOImpl {

    public static HashMap<Integer, String> getValues(int id) {
        String value;
        int type;
        HashMap<Integer, String> values = new HashMap<>();

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
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
            Connection conn = DatabaseConnection.getInstance().getConnection();
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

    public static String getRuleValues(int ID) throws Exception{

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query  = "SELECT * FROM TOSAD.VALUE WHERE BUSINESSRULEID = " + ID;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            obj.put("value", rs.getString("VALUE"));
            obj.put("type", rs.getInt("TYPE"));
            arr.put(obj);
            obj = new JSONObject();
        }

        String result = arr.toString();
        return result;
    }
}
