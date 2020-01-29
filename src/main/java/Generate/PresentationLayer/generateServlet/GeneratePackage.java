package Generate.PresentationLayer.generateServlet;

import Generate.BusinessLayer.GeneratorTypes.GeneratorTypesFacade;
import Generate.BusinessLayer.daoImplementatie.DAOFacade;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/generatePackage")
public class GeneratePackage {
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getPackage(
            @QueryParam("name") String packageName,
            @QueryParam("tableName") String tableName,
            @QueryParam("packageMethodSelect") String packageMethodSelect,
            @QueryParam("ferStatus") int ferStatus) throws Exception {
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        ArrayList<Integer> allRuleIDS = DAOFacade.getTableRules(tableName);
        ArrayList<String> allTriggerStatements = new ArrayList<>();
        for (int i : allRuleIDS) {
            allTriggerStatements.add(GeneratorTypesFacade.generateTriggerCodeOracle(i, ferStatus));
        }

        String bigTrigger = "";
        packageMethodSelect = packageMethodSelect.replace("/", " OR ");
        if (ferStatus == 1) {
            bigTrigger = String.format("CREATE OR REPLACE TRIGGER %s %n" +
                            "%s %n" +
                            "ON %s %n" +
                            "FOR EACH ROW %n" +
                            "BEGIN %n",
                    packageName,
                    packageMethodSelect,
                    tableName);
        } else {
            bigTrigger = String.format("CREATE OR REPLACE TRIGGER %s %n" +
                            "%s %n" +
                            "ON %s %n" +
                            "BEGIN %n",
                    packageName,
                    packageMethodSelect,
                    tableName);
        }

        // Fill the big trigger
        for (int i = 0; i < allTriggerStatements.size(); i++) {
            String triggerStatement = allTriggerStatements.get(i);
            if(i == 0) {
                bigTrigger += String.format("IF %s",
                                            triggerStatement);
            } else if (i == allTriggerStatements.size() - 1) {
                bigTrigger += String.format("ELSIF %s" +
                                            "END IF; %n",
                                            triggerStatement);
            } else {
                bigTrigger += String.format("ELSIF %s",
                                            triggerStatement);
            }
        }
        bigTrigger += "END; ";

        obj.put("code", bigTrigger);
        arr.put(obj);

        return (arr.toString());
    }
}
