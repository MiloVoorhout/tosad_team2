package webservices.resources;

import org.json.JSONObject;


import javax.servlet.http.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/testservlet")
public class TestServlet extends HttpServlet {
    @GET
    @Produces("application/json")
    public String getTest() {
        JSONObject json = new JSONObject().put("Test1", "Hallo ik werk!");
        return json.toString();
    }
}
