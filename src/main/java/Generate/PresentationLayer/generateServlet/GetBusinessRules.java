package Generate.PresentationLayer.generateServlet;

import Generate.BusinessLayer.daoImplementatie.DAOFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/getBusinessRules")
public class GetBusinessRules {

    @Path("/getMenuItems")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getMenuItems() throws Exception {

        return DAOFacade.getMenuItems();

    }

    @Path("/getContent")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getContent(@QueryParam("id") int id) throws Exception {

        return DAOFacade.getContent(id);

    }

    @Path("/getValues")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getBusinessRuleValue(@QueryParam("id") int id) throws Exception {

            return DAOFacade.getBusinessRuleValue(id);
    }


    @Path("/information")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getTestService() throws Exception {

        return DAOFacade.getBusinessRuleInfo();
    }

    @Path("/values")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getRuleValues(@QueryParam("id") int ID) throws Exception {

        return DAOFacade.getRuleValues(ID);
    }

    @Path("/subAttribute")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getSubAttributeRule(@QueryParam("subAttributeID") int subAttributeID) throws Exception {

        return DAOFacade.getSubAttributeRule(subAttributeID);
    }
}
