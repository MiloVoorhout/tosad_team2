package com;

import Generate.DatabaseLayer.DatabaseConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/GetTableInfo")
public class GetTableInfo {

    @GET
    @Path("/GetAttributes")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTestService(
            @QueryParam("table") String table
    ) throws SQLException {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query  = "SELECT column_name FROM all_tab_cols WHERE table_name = '"+ table +"'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        int i = 0;

        while (rs.next()) {
            i++;
            obj.put("id", i);
            obj.put("name", rs.getString("column_name"));
            arr.put(obj);
            obj = new JSONObject();
        }

        String result = arr.toString();

        return (result);
    }

    @GET
    @Path("/getAllTables")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTestService() throws SQLException {

        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();

        Connection conn = DatabaseConnection.getInstance().getConnection();
        String query  = "SELECT table_name from all_tables where owner = 'VBMG'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        int i = 0;

        while (rs.next()) {
            i++;
            obj.put("id", i);
            obj.put("name", rs.getString("table_name"));
            arr.put(obj);
            obj = new JSONObject();
        }

        String result = arr.toString();

        return (result);
    }
}