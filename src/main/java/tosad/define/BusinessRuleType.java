package tosad.define;

public class BusinessRuleType {
    private String name;
    private String description;
    private int categoryID;

    public BusinessRuleType(String name, String description, int categoryID) {
        this.name = name;
        this.description = description;
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
