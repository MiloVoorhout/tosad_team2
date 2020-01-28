package Define.DatabaseLayer;

import java.sql.SQLException;

public class DatabaseFacade {

    // DatabaseConnection functie
    public static DatabaseConnection getInstance() throws SQLException {

        return DatabaseConnection.getInstance();
    }
}
