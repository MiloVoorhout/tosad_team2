package Define.DatabaseLayer;

import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleDatabase extends DatabaseConnection {
    public OracleDatabase() throws SQLException {
    }

    public static String getTableData() throws SQLException {
        String databaseText = "";
        try {
            Connection conn = (Connection) new DatabaseConnection();
            Statement stmt = conn.createStatement();

            ResultSet results = stmt.executeQuery("SELECT * FROM TOSAD.CATEGORY WHERE ID = 1");

            while (results.next()) {
                databaseText += results.getString("type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return databaseText;
    }
}
