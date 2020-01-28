package Define.BusinessLayer;

public class FailureHandling implements FailureHandlingInterface {

    private String name;
    private String message;
    private int businessRuleID;

    private FailureHandling(String name, String message, int businessRuleID) {
        this.name = name;
        this.message = message;
        this.businessRuleID = businessRuleID;
    }

    @Override
    public FailureHandling createFailureHandling() {
        return new FailureHandling(this.name, this.message, this.businessRuleID);
    }
}
