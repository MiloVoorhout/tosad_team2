package Generate.PresentationLayer.generateServlet;

import Generate.BusinessLayer.daoImplementatie.DAOFacade;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/executeTrigger")
public class ExecuteTrigger {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPackage(
            @QueryParam("code") String triggerCode) throws Exception {

        DAOFacade.executeTrigger(triggerCode);

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        obj.put("status", "true");
        arr.put(obj);

        return (arr.toString());
    }
}
