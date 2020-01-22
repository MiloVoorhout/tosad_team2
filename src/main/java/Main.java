import tosad.database.DatabaseConnection;
import tosad.define.BusinessRule;
import tosad.define.BusinessRuleFactory;
import tosad.generate.Generator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends DatabaseConnection {

    public static void main(String[] arg) throws SQLException {

//        Connection conn = getConnection();
//        String query  = "SELECT table_name from all_tables where owner = 'VBMG'";
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery(query);
//        String tableName;
//        int i = 0;
//
//        ArrayList<String> json = new ArrayList<>();
//
//        while (rs.next()) {
//            i++;
//            tableName = rs.getString("table_name");
//            json.add(i + ":" + tableName + ",");
//        }
//
//        System.out.println(json.toString());
        Generator generator = new Generator();

        BusinessRule newBusinessRule = new BusinessRule(1, "test_trigger", 1, 1, 2, 1);
        System.out.println(generator.generatorInformation(newBusinessRule, "INSERT"));
    }

}