package com;

import org.json.JSONArray;
import org.json.JSONObject;
import tosad.database.DatabaseConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/getAllTables")
public class getAttributes extends DatabaseConnection {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getTestService(
            @DefaultValue("1") @QueryParam("table") String table
    ) throws SQLException {

        return (table);
    }
}