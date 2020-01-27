package Generate.PresentationLayer.generateServlet;

import Generate.daoImplementatie.AttributeDAOImpl;
import Generate.DatabaseLayer.DatabaseConnection;
import Generate.daoImplementatie.BusinessDAOImpl;
import Generate.daoImplementatie.ValueDAOImpl;
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
