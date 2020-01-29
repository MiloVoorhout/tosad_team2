// 1 = minValue
// 2 = maxValue
// 3 = (compare)literal
// 4 = list

package Define.PresentationLayer.defineServlet;

import Define.BusinessLayer.daoImplementatie.DAOFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/newBusinessRule")
public class CreateBusinessRule {

    @GET
    @Path("/create")
    @Produces({MediaType.APPLICATION_JSON})
    public String createRule(
            @QueryParam("rule_type_select") int rule_type_select,
            @QueryParam("rule_name") String rule_name,
            @QueryParam("tableSelect") String tableSelect,
            @QueryParam("attributeSelect") String attributeSelect,
            @QueryParam("operator") int operator,
            @QueryParam("validationFailureSeverity") int validationFailureSeverity,
            @QueryParam("failureMessage") String failureMessage,
            @QueryParam("minimumValue") int minimumValue,
            @QueryParam("maximumValue") int maximumValue,
            @QueryParam("compareWith") int compareWith,
            @QueryParam("value") String value,
            @QueryParam("interEntityTable") String interEntityTable,
            @QueryParam("listValues") String listValues,
            @QueryParam("otherValue") String otherValue


    ) throws Exception {

        return DAOFacade.createRule(rule_type_select, rule_name, tableSelect, attributeSelect, operator,
                validationFailureSeverity, failureMessage, minimumValue, maximumValue, compareWith, value,
                interEntityTable, listValues, otherValue);
    }
}