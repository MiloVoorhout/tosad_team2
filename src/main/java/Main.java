import tosad.database.DatabaseFacade;

import java.sql.*;

public class Main {

    public static void main(String[] arg) throws SQLException{
        DatabaseFacade.getData(1);
    }

}