package Database;

public class DatabaseFacade extends DatabaseConnection {

    // Milo FF kijken of je deze gebruikt anders kan de volledige klasse weggegooid worden.

//    public static void getData(int id) throws SQLException {
//        String ruleName = "";
//        int compareStatus = 0;
//        int operatorID = 0;
//        int attributeID = 0;
//        int subAttributeID = 0;
//        int businessRuleTypeID = 0;
//
//        try {
//            Connection conn = getConnection();
//            String query  = "SELECT * FROM TOSAD.BUSINESSRULE WHERE ID = "+id;
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                ruleName = rs.getString("NAME");
//                compareStatus = rs.getInt("COMPARESTATUS");
//                operatorID = rs.getInt("OPERATORID");
//                attributeID = rs.getInt("ATTRIBUTEID");
//                subAttributeID = rs.getInt("SUBATTRIBUTEID");
//                businessRuleTypeID = rs.getInt("BUSINESSRULETYPEID");
//            }
//            BusinessRule newRule = new BusinessRule(ruleName ,compareStatus, operatorID, attributeID, subAttributeID, businessRuleTypeID);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
