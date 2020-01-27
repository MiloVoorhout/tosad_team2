package Generate.PresentationLayer.generateServlet;

import Generate.BusinessLayer.daoImplementatie.AttributeDAOImpl;
import Generate.BusinessLayer.daoImplementatie.BusinessDAOImpl;
import Generate.BusinessLayer.daoImplementatie.ValueDAOImpl;
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

    @Path("/getMenuItems")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMenuItems() throws SQLException {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query  = "SELECT ID, NAME FROM TOSAD.BUSINESSRULE";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            obj.put("id", rs.getInt("ID"));
            obj.put("name", rs.getString("NAME"));
            arr.put(obj);
            obj = new JSONObject();
        }

        return (arr.toString());

    }

    @Path("/getContent")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getContent(@QueryParam("id") int id) throws SQLException {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        String failureType = "Informational Warning";

        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query  = "SELECT br.ID, br.name, br.FAILURETYPE, br.FAILUREMESSAGE, o.NAME as OPERATOR, " +
                "A2.NAME AS ATTRIBUTE_NAME, A2.TABLENAME AS ATTRIBUTE_TABLE, B.NAME AS BUSINESSRULETYPE " +
                "FROM TOSAD.BUSINESSRULE br " +
                "INNER JOIN TOSAD.ATTRIBUTE A2 on br.ATTRIBUTEID = A2.ID " +
                "INNER JOIN TOSAD.OPERATOR O on br.OPERATORID = O.ID " +
                "INNER JOIN TOSAD.BUSINESSRULETYPE B on br.BUSINESSRULETYPEID = B.ID " +
                "WHERE br.ID = "+id;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            if (rs.getInt("FAILURETYPE") == 1) failureType = "Error";
            obj.put("id", rs.getInt("ID"));
            obj.put("operator", rs.getString("OPERATOR"));
            obj.put("attributeName", rs.getString("ATTRIBUTE_NAME"));
            obj.put("attributeTable", rs.getString("ATTRIBUTE_TABLE"));
            obj.put("businessRuleType", rs.getString("BUSINESSRULETYPE"));
            obj.put("name", rs.getString("NAME"));
            obj.put("failureType", failureType);
            obj.put("failureMessage", rs.getString("FAILUREMESSAGE"));
            arr.put(obj);
            obj = new JSONObject();
        }

        return (arr.toString());

    }

    @Path("/getValues")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getValues(@QueryParam("id") int id) throws SQLException {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query  = "SELECT v.id, v.value, b.name AS BUSINESSRULETYPEID, A2.NAME AS ATTRIBUTENAME, A2.TABLENAME, A2.DATABASENAME FROM TOSAD.VALUE V INNER JOIN TOSAD.BUSINESSRULETYPE B ON V.TYPE = B.ID INNER JOIN TOSAD.ATTRIBUTE A2 on V.BUSINESSRULEID = A2.ID WHERE BUSINESSRULEID = "+id;
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

        return (arr.toString());
    }


    @Path("/information")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getTestService() throws Exception {

        return (BusinessDAOImpl.getBusinessRuleInfo());
    }

    @Path("/values")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRuleValues(@QueryParam("id") int ID) throws Exception {

        return (ValueDAOImpl.getRuleValues(ID));
    }

    @Path("/subAttribute")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getSubAttributeRule(@QueryParam("subAttributeID") int subAttributeID) throws Exception {

        return (AttributeDAOImpl.getSubAttributeRule(subAttributeID));
    }
}
