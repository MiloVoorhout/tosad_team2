package Define.PresentationLayer.defineServlet;

import Define.BusinessLayer.daoImplementatie.DAOFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/BusinessRule")
public class ModifyBusinessRule {

    @GET
    @Path("/modify")
    @Produces({MediaType.APPLICATION_JSON})
    public String modifyRule(
            @QueryParam("ruleId") int ruleId,
            @QueryParam("rule_type_select") int rule_type_select,
            @QueryParam("rule_name") String rule_name,
            @QueryParam("tableSelect") String tableSelect,
            @QueryParam("attributeSelect") String attributeSelect,
            @QueryParam("operator") int operator,
            @QueryParam("failureMessage") String failureMessage,
            @QueryParam("minimumValue") int minimumValue,
            @QueryParam("maximumValue") int maximumValue,
            @QueryParam("value") String value,
            @QueryParam("interEntityTable") String interEntityTable,
            @QueryParam("listValues") String listValues
    ) throws Exception {

        return DAOFacade.modifyBusinessRule(ruleId, rule_type_select, rule_name, tableSelect, attributeSelect,
                operator, failureMessage, minimumValue, maximumValue, value, interEntityTable, listValues);
    }
}
