package com;

import org.json.JSONArray;
import org.json.JSONObject;
import Database.DatabaseConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;

public class CreateBusinessRule extends DatabaseConnection {

    @GET
    @Path("/createNewBusinessRule")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTestService(
            @QueryParam("rule_type_select") Integer rule_type_select,
            @QueryParam("rule_name") String rule_name,
            @QueryParam("tableSelect") String tableSelect,
            @QueryParam("attributeSelect") String attributeSelect,
            @QueryParam("operator") String operator,
            @QueryParam("minimumValue") String minimumValue,
            @QueryParam("maximumValue") String maximumValue,
            @QueryParam("validationFailureSeverity") String validationFailureSeverity,
            @QueryParam("failureMessage") String failureMessage
    ) throws SQLException {

        Connection conn = getConnection();

        if (rule_type_select == 1) {
            // attribute
            String query = "INSERT INTO TOSAD.ATTRIBUTE(NAME, TABLE, DATABASE) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, attributeSelect);
            stmt.setString(2, tableSelect);
            stmt.setString(3, "VBMG");
            stmt.execute();

            // subattribute (attribute table)

            // businessruletype

            // businessRule

            // values

            // failurehandling

        }


        return ("Tables aangemaakt");
    }
}