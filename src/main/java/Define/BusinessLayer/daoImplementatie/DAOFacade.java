package Define.BusinessLayer.daoImplementatie;

public class DAOFacade {

    // CategoryDAOImpl functies aanroepen
    public static String getTableData() throws Exception {

        return CategoryDAOImpl.getTableData();
    }

    // InsertDAOImpl functies aanroepen
    public static String createRule(int rule_type_select, String rule_name, String tableSelect, String attributeSelect,
                                    int operator, int validationFailureSeverity, String failureMessage, int minimumValue,
                                    int maximumValue, int compareWith, String value, String interEntityTable,
                                    String listValues) throws Exception {

        return InsertDAOImpl.createRule(rule_type_select, rule_name, tableSelect, attributeSelect, operator,
                validationFailureSeverity, failureMessage, minimumValue, maximumValue, compareWith, value,
                interEntityTable, listValues);
    }

    // TableDAOImpl functies aanroepen
    public static String getAttributesFromTable(String table) throws Exception {

        return TableDAOImpl.getAttributesFromTable(table);
    }

    public static String getAllTableNames() throws Exception {

        return TableDAOImpl.getAllTableNames();
    }

}
