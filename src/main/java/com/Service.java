package com;

import tosad.database.DatabaseConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@Path("/service")
public class Service extends DatabaseConnection {


	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public ArrayList<String> getTestService() throws SQLException {
		Connection conn = getConnection();
		String query  = "SELECT table_name as Table from all_tables where owner = 'VBMG'";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		String tableName;
		int i = 0;

		ArrayList<String> json = new ArrayList<>();

		while (rs.next()) {
			i++;
			tableName = rs.getString("Table");
			json.add(i + ":" + tableName + ",");
		}

		return (json);
	}
}