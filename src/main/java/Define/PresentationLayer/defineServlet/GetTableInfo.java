package Define.PresentationLayer.defineServlet;

import Define.BusinessLayer.daoImplementatie.DAOFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/GetTableInfo")
public class GetTableInfo {

    @GET
    @Path("/GetAttributes")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAttributesFromTable( @QueryParam("table") String table) throws Exception {

        return DAOFacade.getAttributesFromTable(table);
    }

    @GET
    @Path("/getAllTables")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllTableNames() throws Exception {

        return DAOFacade.getAllTableNames();
    }
}