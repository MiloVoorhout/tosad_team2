package Generate.BusinessLayer.GeneratorTypes;

import Generate.BusinessLayer.Attribute.Attribute;
import Generate.BusinessLayer.BusinessRule.BusinessRule;
import Generate.BusinessLayer.RuleTypes.RuleTypesFacade;
import Generate.BusinessLayer.daoImplementatie.DAOFacade;
import Generate.BusinessLayer.ruleObjects.Operator;

import java.util.HashMap;

public class GeneratorTypesFacade {

    //MySQLGenerator functies
    public static String generatorMYSQLInformation(BusinessRule rule, String operation, int ferStatus) throws Exception {

        return MySQLGenerator.generatorMYSQLInformation(rule, operation, ferStatus);
    }

    //OracleGenerator functies
    public static String generatorInformation(BusinessRule rule, String operation, int ferStatus) throws Exception {

        return OracleGenerator.generatorInformation(rule, operation, ferStatus);
    }

    public static String generateTriggerCodeOracle(int id, int ferStatus) throws Exception {

        return OracleGenerator.generateTriggerCode(id, ferStatus);
    }
}
