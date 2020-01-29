package Generate.BusinessLayer.daoImplementatie;

import Generate.DatabaseLayer.DatabaseFacade;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class ValueDAOImpl extends DAOFacade{

    public static HashMap<Integer, String> getValues(int id) throws Exception {
        String value;
        int type;
        HashMap<Integer, String> values = new HashMap<>();

            Connection conn = DatabaseFacade.getInstance().getConnection();
            String query = "SELECT * FROM TOSAD.VALUE WHERE BUSINESSRULEID = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                value = rs.getString("VALUE");
                type = rs.getInt("TYPE");
                values.put(type, value);
            }

        return values;
    }

    public static HashMap<Integer, String> getListValues(int id) throws Exception {
        String value;
        HashMap<Integer, String> values = new HashMap<>();

            Connection conn = DatabaseFacade.getInstance().getConnection();
            String query = "SELECT * FROM TOSAD.VALUE WHERE BUSINESSRULEID = " + id;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                value = rs.getString("VALUE");
                id = rs.getInt("ID");
                values.put(id, value);
            }

        return values;
    }

    public static String getRuleValues(int ID) throws Exception{

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseFacade.getInstance().getConnection();
        String query  = "SELECT * FROM TOSAD.VALUE WHERE BUSINESSRULEID = " + ID;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            obj.put("value", rs.getString("VALUE"));
            obj.put("type", rs.getInt("TYPE"));
            arr.put(obj);
            obj = new JSONObject();
        }

        return arr.toString();
    }

    public static String getBusinessRuleValue(int id) throws Exception {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseFacade.getInstance().getConnection();
        String query  = "SELECT value, type FROM TOSAD.VALUE V WHERE BUSINESSRULEID = "+id;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {

            int type =  rs.getInt("TYPE");
            String typeString = "err";

            if (type == 1) typeString = "Minimum value";
            if (type == 2) typeString = "Maximum value";
            if (type == 3) typeString = "Compare value";
            if (type == 4) typeString = "List value";

            obj.put("value", rs.getString("VALUE"));
            obj.put("type", typeString);
            arr.put(obj);
            obj = new JSONObject();
        }

        return arr.toString();
    }
}
