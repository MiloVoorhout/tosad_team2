package Generate.BusinessLayer.daoImplementatie;

import Generate.DatabaseLayer.DatabaseConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BusinessDAOImpl {

    public static String getBusinessRuleInfo() throws Exception{

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query  = "SELECT * FROM TOSAD.BUSINESSRULE";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            obj.put("id", rs.getInt("ID"));
            obj.put("name", rs.getString("NAME"));
            obj.put("comparestatus", rs.getInt("COMPARESTATUS"));
            obj.put("operatorID", rs.getInt("OPERATORID"));
            obj.put("attributeID", rs.getInt("ATTRIBUTEID"));
            obj.put("subAttributeID", rs.getInt("SUBATTRIBUTEID"));
            obj.put("businessRuleTypeID", rs.getInt("BUSINESSRULETYPEID"));
            arr.put(obj);
            obj = new JSONObject();
        }

        String result = arr.toString();
        return result;
    }
}
