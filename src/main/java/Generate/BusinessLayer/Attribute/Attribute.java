package Generate.BusinessLayer.Attribute;

public class Attribute {
    private String Name;
    private String Table;
    private String Database;

    public Attribute(String name, String table, String database) {
        Name = name;
        Table = table;
        Database = database;
    }

    public String getName() { return Name; }

    public void setName(String name) { Name = name; }

    public String getTable() { return Table; }

    public void setTable(String table) { Table = table; }

    public String getDatabase() { return Database; }

    public void setDatabase(String database) { Database = database; }
}
