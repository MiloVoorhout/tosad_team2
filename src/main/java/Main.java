import tosad.define.BusinessRuleFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] arg) throws SQLException{
//        DatabaseFacade.getData(1);
        int val1 = 1;
        int val2 = 2;
        int val3 = 3;
        List<Integer> Data = new ArrayList<Integer>();
        Data.add(val1);
        Data.add(val2);
        Data.add(val3);


        BusinessRuleFactory BRF = new BusinessRuleFactory(1,1, Data, 1,1,1);
        BRF.buildRule();
    }

}