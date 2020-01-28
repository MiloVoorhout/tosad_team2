package Generate.BusinessLayer.GeneratorTypes;

import Generate.BusinessLayer.BusinessRule.BusinessRule;

public class GeneratorTypesFacade {

    //MySQLGenerator functies
    public static String generatorMYSQLInformation(BusinessRule rule, String operation, int ferStatus) throws Exception {

        return MySQLGenerator.generatorMYSQLInformation(rule, operation, ferStatus);
    }

    //OracleGenerator functies
    public static String generatorInformation(BusinessRule rule, String operation, int ferStatus) throws Exception {

        return OracleGenerator.generatorInformation(rule, operation, ferStatus);
    }
}
