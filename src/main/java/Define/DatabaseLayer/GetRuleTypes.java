package Define.DatabaseLayer;

import Database.DatabaseConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/GetRuleTypes")
public class GetRuleTypes extends DatabaseConnection {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public static String getTableData() throws SQLException {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        try {
            Connection conn = (Connection) new DatabaseConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM TOSAD.CATEGORY");

            while (rs.next()) {
                obj.put("id", rs.getInt("id"));
                obj.put("type", rs.getString("type"));
                arr.put(obj);
                obj = new JSONObject();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String result = arr.toString();

        return result;
    }
}
