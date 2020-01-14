package tosad.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static Connection conn = null;
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC19";
    private static final String DB_USER = "milo";
    private static final String DB_PASS = "milo";

    public DatabaseConnection() throws SQLException {
        try {
            Class.forName(DB_DRIV).newInstance();
        }
        catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

//    public static DatabaseConnection getInstance() throws SQLException {
//        if(DatabaseConnection.conn == null) {
//            DatabaseConnection.conn = new DatabaseConnection();
//        }
//
//        return DatabaseConnection.conn;
//    }
}
