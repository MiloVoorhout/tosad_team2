package Generate.BusinessLayer.daoImplementatie;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.DatabaseLayer.DatabaseFacade;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AttributeDAOImpl extends DAOFacade {

    public static Attribute getAttributeData(int AttributeID) {
        String name = "";
        String table = "";
        String database = "";
        Attribute newAttribute = null;

        try {
            Connection conn = DatabaseFacade.getInstance().getConnection();
            String query = "SELECT * FROM TOSAD.ATTRIBUTE WHERE ID = " + AttributeID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("NAME");
                table = rs.getString("TABLENAME");
                database = rs.getString("DATABASENAME");
            }
            newAttribute = new Attribute(name, table, database);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newAttribute;
    }

    public static String getSubAttributeRule(int subAttributeID) throws Exception{

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseFacade.getInstance().getConnection();
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

        return arr.toString();
    }
}
