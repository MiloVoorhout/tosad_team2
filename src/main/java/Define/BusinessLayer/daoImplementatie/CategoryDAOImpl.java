package Define.BusinessLayer.daoImplementatie;

import Generate.DatabaseLayer.DatabaseConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoryDAOImpl {

    public static String getTableData() throws Exception{

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseConnection.getInstance().getConnection();

        String query  = "SELECT ID, TYPE FROM TOSAD.CATEGORY";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            obj.put("id", rs.getInt("id"));
            obj.put("type", rs.getString("type"));
            arr.put(obj);
            obj = new JSONObject();
        }

        String result = arr.toString();
        return result;
    }
}
