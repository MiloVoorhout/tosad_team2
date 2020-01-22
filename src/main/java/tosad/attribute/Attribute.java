package tosad.attribute;

public class Attribute {

    private String Name;
    private String Tabel;
    private String Database;

    public Attribute(String name, String tabel, String database) {
        Name = name;
        Tabel = tabel;
        Database = database;
    }

    public String getName() { return Name; }

    public void setName(String name) { Name = name; }

    public String getTabel() { return Tabel; }

    public void setTabel(String tabel) { Tabel = tabel; }

    public String getDatabase() { return Database; }

    public void setDatabase(String database) { Database = database; }
}