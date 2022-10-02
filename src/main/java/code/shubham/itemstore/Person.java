package code.shubham.itemstore;

public class Person implements Item {
    private String name;
    private String id;

    public String getKind() {
        return this.getClass().getSimpleName();
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setID(String s) {
        this.id = s;
    }

    public void setName(String s) {
        this.name = s;
    }
}
