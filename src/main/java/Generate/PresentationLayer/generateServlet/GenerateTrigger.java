package Generate.PresentationLayer.generateServlet;

import Generate.BusinessLayer.GeneratorTypes.GeneratorTypesFacade;
import Generate.BusinessLayer.daoImplementatie.DAOFacade;
import org.json.JSONArray;
import org.json.JSONObject;
import Generate.BusinessLayer.BusinessRule.BusinessRule;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/generate")
public class GenerateTrigger {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getTestService(@QueryParam("id") int id,
                                 @QueryParam("statement") String statement,
                                 @QueryParam("ferStatus") int ferStatus,
                                 @QueryParam("dataBaseType") int dataBaseType) throws Exception {

        BusinessRule generateRule = DAOFacade.getBusinessRuleTrigger(id);

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        String triggerCode = "";

        if(dataBaseType == 1) {
            triggerCode = GeneratorTypesFacade.generatorInformation(generateRule, statement, ferStatus);
        } else if (dataBaseType == 2) {
            triggerCode = GeneratorTypesFacade.generatorMYSQLInformation(generateRule, statement, ferStatus);
        }
        obj.put("code", triggerCode);
        arr.put(obj);

        return (arr.toString());
    }

}
