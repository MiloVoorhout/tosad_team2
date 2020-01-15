import java.sql.*;

public class Main {

    private static Connection conn = null;
    private static final String DB_DRIV = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_URL = "jdbc:oracle:thin:@//ondora04.hu.nl:8521/EDUC19";
    private static final String DB_USER = "milo";
    private static final String DB_PASS = "milo";

    public static void main(String[] arg) throws SQLException {
        try {
            Class.forName(DB_DRIV).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        System.out.println("Connection made");

        Statement stmt = conn.createStatement();
        String strQuery = "SELECT * FROM TOSAD.CATEGORY WHERE ID = 1";
        ResultSet rs = stmt.executeQuery(strQuery);

        int id;
        String type;
        while (rs.next()) {
            id = rs.getInt("id");
            type = rs.getString("type");
            System.out.println(id + ", " + type);
        }

        rs.close();
        stmt.close();
        conn.close();
    }
}