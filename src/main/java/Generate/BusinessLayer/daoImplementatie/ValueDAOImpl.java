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
        String query  = "SELECT v.id, v.value, b.name AS BUSINESSRULETYPEID, A2.NAME AS ATTRIBUTENAME, A2.TABLENAME," +
                " A2.DATABASENAME FROM TOSAD.VALUE V INNER JOIN TOSAD.BUSINESSRULETYPE B ON V.TYPE = B.ID" +
                " INNER JOIN TOSAD.ATTRIBUTE A2 on V.BUSINESSRULEID = A2.ID WHERE BUSINESSRULEID = "+id;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            obj.put("id", rs.getInt("ID"));
            obj.put("value", rs.getString("VALUE"));
            obj.put("businessRuleTypeID", rs.getString("BUSINESSRULETYPEID"));
            obj.put("attributeName", rs.getString("ATTRIBUTENAME"));
            obj.put("tableName", rs.getString("TABLENAME"));
            obj.put("databaseName", rs.getString("DATABASENAME"));
            arr.put(obj);
            obj = new JSONObject();
        }

        return arr.toString();
    }
}
