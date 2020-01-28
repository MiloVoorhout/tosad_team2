package Generate.BusinessLayer.daoImplementatie;

import Generate.BusinessLayer.ruleObjects.Operator;
import Generate.DatabaseLayer.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperatorDAOImpl {

    public static Operator getOperatorInformation(int OperatorID) throws Exception {
        String name = "";
        String symbol = "";
        Operator newOperator;

            Connection conn = DatabaseConnection.getInstance().getConnection();
            String query = "SELECT * FROM TOSAD.OPERATOR WHERE ID = " + OperatorID;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("NAME");
                symbol = rs.getString("SYMBOL");
            }
            newOperator = new Operator(name, symbol);

        return newOperator;
    }
}
