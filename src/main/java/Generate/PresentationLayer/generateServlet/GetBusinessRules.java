package Generate.PresentationLayer.generateServlet;

import Generate.DatabaseLayer.DatabaseConnection;
import org.json.JSONArray;
import org.json.JSONObject;

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
public class GetBusinessRules {

    @Path("/information")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getTestService() throws SQLException {

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

        return (result);
    }

    @Path("/values")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRuleValues(@QueryParam("id") int ID) throws SQLException {

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

        return (result);
    }

    @Path("/subAttribute")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getSubAttributeRul(@QueryParam("subAttributeID") int subAttributeID) throws SQLException {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query  = "SELECT * FROM TOSAD.ATTRIBUTE WHERE ID = " + subAttributeID;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            obj.put("name", rs.getString("NAME"));
            obj.put("tablename", rs.getString("TABLENAME"));
            obj.put("databasename", rs.getString("DATABASENAME"));
            arr.put(obj);
            obj = new JSONObject();
        }

        String result = arr.toString();

        return (result);
    }


}
