package code.shubham.itemstore;

public class Animal implements Item {
    private String name;
    private String id;

    @Override
    public String getKind() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setID(String s) {
        this.id = s;
    }

    @Override
    public void setName(String s) {
        this.name = s;
    }
}