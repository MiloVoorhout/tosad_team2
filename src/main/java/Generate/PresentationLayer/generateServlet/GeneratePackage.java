package Generate.PresentationLayer.generateServlet;

import Generate.BusinessLayer.daoImplementatie.DAOFacade;
import org.json.JSONArray;
import org.json.JSONObject;
import Generate.BusinessLayer.BusinessRule.BusinessRule;
import Generate.BusinessLayer.GeneratorTypes.OracleGenerator;
import Generate.BusinessLayer.GeneratorTypes.MySQLGenerator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/generatePackage")
public class GeneratePackage {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPackage(@QueryParam("id") int id) throws Exception {
        return "aardappels";
    }
}
