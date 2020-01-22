package com;

import org.json.JSONArray;
import org.json.JSONObject;
import tosad.database.DatabaseConnection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Path("/getAllTables")
public class Service extends DatabaseConnection {

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public String getTestService() throws SQLException {

		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();

		Connection conn = getConnection();
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