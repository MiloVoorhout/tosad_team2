package Generate.PresentationLayer.generateServlet;

import Generate.BusinessLayer.daoImplementatie.DAOFacade;
import org.json.JSONArray;
import org.json.JSONObject;
import Generate.BusinessLayer.BusinessRule.BusinessRule;
import Generate.BusinessLayer.GeneratorTypes.OracleGenerator;
import Generate.BusinessLayer.GeneratorTypes.MySQLGenerator;

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
            OracleGenerator oracleGenerator = new OracleGenerator();
            triggerCode = oracleGenerator.generatorInformation(generateRule, statement, ferStatus);
        } else if (dataBaseType == 2) {
            MySQLGenerator mySQLGenerator = new MySQLGenerator();
            triggerCode = mySQLGenerator.generatorMYSQLInformation(generateRule, statement, ferStatus);
        }
        obj.put("code", triggerCode);
        arr.put(obj);

        return (arr.toString());
    }
}
