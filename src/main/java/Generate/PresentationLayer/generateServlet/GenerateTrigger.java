package Generate.PresentationLayer.generateServlet;

import Generate.BusinessLayer.daoImplementatie.BusinessDAOImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import Generate.BusinessLayer.BusinessRule;
import Generate.BusinessLayer.Generator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;

@Path("/generate")
public class GenerateTrigger {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getTestService(@DefaultValue("0") @QueryParam("id") int id) throws Exception {
        BusinessRule generateRule = BusinessDAOImpl.getBusinessRuleTrigger(id);

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Generator generator = new Generator();
        String triggerCode = generator.generatorInformation(generateRule, "INSERT");
        obj.put("code", triggerCode);
        arr.put(obj);

        return (arr.toString());
    }
}
