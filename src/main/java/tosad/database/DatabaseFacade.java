package tosad.database;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseFacade extends DatabaseConnection {

    public static void insertNewRule() {

        ArrayList<String> newRuleList = new ArrayList<String>();

        newRuleList.add("1"); //           0 Rule type (1 = Range, 2 = Compare)
        newRuleList.add("Test");//         1 Name
        newRuleList.add("customer"); //    2 Attribute
        newRuleList.add("1"); //           3 Operator (1 = Between, 2 = NotBetween)
        newRuleList.add("5"); //           4 MinValue
        newRuleList.add("20"); //          5 MaxValue

        try {
            // Create a new Business Rule Type
            Connection conn = getConnection();
            String query = "INSERT INTO TOSAD.BUSINESSRULETYPE(1, NAME, CATEGORYID) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newRuleList.get(1));
            stmt.setInt(2, Integer.parseInt(newRuleList.get(0)));
            stmt.execute();

            // Create a new Business Rule (compare)
            String query2 = "INSERT INTO TOSAD.BUSINESSRULE(1, OPERATORID, MINVALUE, MAXVALUE, ATTRIBUTEID, BUSINESSRULETYPEID) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query2);
            stmt.setInt(1, Integer.parseInt(newRuleList.get(3)));
            stmt.setInt(2, Integer.parseInt(newRuleList.get(4)));
            stmt.setInt(3, Integer.parseInt(newRuleList.get(5)));
            stmt.setString(4, newRuleList.get(2));
            stmt.setString(5, newRuleList.get(1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
