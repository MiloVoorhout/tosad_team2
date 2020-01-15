package tosad.productManagement;

public class FailureHandling implements FailureHandlingImpl {

    private String name;
    private String message;
    private int businessRuleID;

    public FailureHandling createFailureHandling(String name, String message, int businessRuleID){

        return new FailureHandling(this.name, this.message, this.businessRuleID);
    }
}
