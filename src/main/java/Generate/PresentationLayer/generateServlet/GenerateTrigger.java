package Generate.PresentationLayer.generateServlet;

import Generate.BusinessLayer.daoImplementatie.DAOFacade;
import org.json.JSONArray;
import org.json.JSONObject;
import Generate.BusinessLayer.BusinessRule.BusinessRule;
import Generate.BusinessLayer.Generator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/generate")
public class GenerateTrigger {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getTestService(@QueryParam("id") int id,
                                 @QueryParam("statement") String statement,
                                 @QueryParam("ferStatus") int ferStatus) throws Exception {

        BusinessRule generateRule = DAOFacade.getBusinessRuleTrigger(id);

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Generator generator = new Generator();
        String triggerCode = generator.generatorInformation(generateRule, statement, ferStatus);
        obj.put("code", triggerCode);
        arr.put(obj);

        return (arr.toString());
    }
}
