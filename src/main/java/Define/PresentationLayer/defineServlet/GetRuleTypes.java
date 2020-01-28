package Define.PresentationLayer.defineServlet;

import Define.BusinessLayer.daoImplementatie.DAOFacade;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/GetRuleTypes")
public class GetRuleTypes {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public static String getTableData() throws Exception {

        return (DAOFacade.getTableData());
    }
}
