package Generate.BusinessLayer.ruleObjects;

public class Operator {
    private String name;
    private String symbol;

    public Operator(String name, String symbol){
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSymbol() { return symbol; }

}
