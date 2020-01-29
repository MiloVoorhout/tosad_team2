package Generate.PresentationLayer.generateServlet;

import Generate.BusinessLayer.GeneratorTypes.GeneratorTypesFacade;
import Generate.BusinessLayer.daoImplementatie.DAOFacade;
import org.json.JSONArray;
import org.json.JSONObject;
import Generate.BusinessLayer.BusinessRule.BusinessRule;
import Generate.BusinessLayer.GeneratorTypes.OracleGenerator;
import Generate.BusinessLayer.GeneratorTypes.MySQLGenerator;

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
        ArrayList<Integer> allRuleIDS = DAOFacade.getTableRules(tableName);
        ArrayList<String> allTriggerStatements = new ArrayList<>();
        for (int i : allRuleIDS) {
            allTriggerStatements.add(GeneratorTypesFacade.generateTriggerCodeOracle(i));
        }

        String ferTekst = "";
        String attributeTekst = "";
        if (ferStatus == 1) {
            ferTekst = "FOR EACH ROW %n";
            attributeTekst = ":new.";
        }

        // Fill the big trigger
        packageMethodSelect = packageMethodSelect.replace("/", " OR ");
        String bigTrigger = String.format("CREATE OR REPLACE TRIGGER %s %n" +
                                            "%s %n" +
                                            "ON %s %n" +
                                            "%s" +
                                            "BEGIN %n",
                                            packageName,
                                            packageMethodSelect,
                                            tableName,
                                            ferTekst);

        for (int i = 0; i < allTriggerStatements.size(); i++) {
            String triggerStatement = allTriggerStatements.get(i);
            if(i == 0) {
                bigTrigger += String.format("if %s%s then %n" +
                                            "error is a %n",
                                            attributeTekst,
                                            triggerStatement);
            } else if (i == allTriggerStatements.size() - 1) {
                bigTrigger += String.format("elsif %s%s then %n" +
                                            "error is b %n" +
                                            "end if; %n",
                                            attributeTekst,
                                            triggerStatement);
            } else {
                bigTrigger += String.format("elsif %s%s then %n" +
                                            "error is c %n",
                                            attributeTekst,
                                            triggerStatement);
            }
        }

        bigTrigger += "END; ";
        System.out.println(bigTrigger);
//        System.out.println(DAOFacade.getTableRules(tableName));
//        String[] allRuleIDs = packageValues.split(",");
//        for (String s : allRuleIDs) {
//            System.out.println(s);
//        }

        return "aardappels";
    }
}
