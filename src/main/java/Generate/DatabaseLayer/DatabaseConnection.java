package Generate.DatabaseLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection extends DatabaseFacade {
    private static Generate.DatabaseLayer.DatabaseConnection instance;
    private Connection connection;
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC19";
    private static final String DB_USER = "stefan";
    private static final String DB_PASS = "stefan";

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName(DB_DRIV);
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Generate.DatabaseLayer.DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new Generate.DatabaseLayer.DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new Generate.DatabaseLayer.DatabaseConnection();
        }

        return instance;
    }
}
