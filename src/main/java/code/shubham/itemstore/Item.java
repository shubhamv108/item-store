package code.shubham.itemstore;

public interface Item {
    //getKind() returns the kind (i.e., type) of the code.shubham.interview.endurolabs.Item.
    String getKind();

    // getID() returns a unique UUID for the code.shubham.interview.endurolabs.Item.
    String getID();

    // getName returns the name of the code.shubham.interview.endurolabs.Item. Names are not unique.
    String getName();

    // setID sets the ID of the code.shubham.interview.endurolabs.Item.
    void setID(String id);

    // setName sets the name of the code.shubham.interview.endurolabs.Item.
    void setName(String name);
}
