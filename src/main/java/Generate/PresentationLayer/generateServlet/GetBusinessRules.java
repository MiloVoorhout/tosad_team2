package Generate.PresentationLayer.generateServlet;

import org.json.JSONArray;
import org.json.JSONObject;
import Database.DatabaseConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/getBusinessRules")
public class GetBusinessRules extends DatabaseConnection {

    @Path("/information")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getTestService() throws SQLException {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = getConnection();
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

        return (result);
    }

    @Path("/values")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRuleValues(@QueryParam("id") int ID) throws SQLException {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = getConnection();
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

        return (result);
    }


}