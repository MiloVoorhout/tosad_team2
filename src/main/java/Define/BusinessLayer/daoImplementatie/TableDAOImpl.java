package Define.BusinessLayer.daoImplementatie;

import Generate.DatabaseLayer.DatabaseConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TableDAOImpl {

    public static String getAttributesFromTable(String table) throws Exception {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query  = "SELECT column_name FROM all_tab_cols WHERE table_name = '"+ table +"'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        int i = 0;

        while (rs.next()) {
            i++;
            obj.put("id", i);
            obj.put("name", rs.getString("column_name"));
            arr.put(obj);
            obj = new JSONObject();
        }

        String result = arr.toString();
        return result;
    }

    public static String getAllTableNames() throws Exception {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query  = "SELECT table_name from all_tables where owner = 'VBMG'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        int i = 0;

        while (rs.next()) {
            i++;
            obj.put("id", i);
            obj.put("name", rs.getString("table_name"));
            arr.put(obj);
            obj = new JSONObject();
        }

        String result = arr.toString();
        return result;
    }
}
