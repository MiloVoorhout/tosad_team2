package tosad.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static Connection conn;
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC19";
    private static final String DB_USER = "milo";
    private static final String DB_PASS = "milo";

    protected static Connection getConnection() throws SQLException {
        try {
            Class.forName(DB_DRIV);
        }
        catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        if (conn != null) {
            return conn;
        } else {
            return null;
        }
    }

    public void closeConnection() throws SQLException{
        conn.close();
    }
}
