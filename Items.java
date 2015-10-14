/**
 * Created by biancaaanoel on 5/7/15.
 */
public class Item {
    private String name;
    private String description;
    //Provide accessors, mutators and a constructor.

    public Item(String n, String d) {
        name = n;
        description = d;
    }

    public String toString() {
        String info = "";
        info = info + "Name: " + name;
        info = info + "\nDescription: " + description;
        return info;
    }

    //accessors
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    //mutators

}
