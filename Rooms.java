/**
 * Created by biancaaanoel on 5/7/15.
 */
public class Room {
    private String name;
    private String description;
    //private String reaction;

    private Room[] exits;
    private Item[] items;
    private Item triggerItem;

    public Room(String n, String d, Room[] e, Item[] j, Item t) {
        name = n;
        description = d;
        exits = e;
        items = j;
        triggerItem = t;
    }

    //accessors

    //@param item the triggerItem to set
    public void setTriggerItem(Item item) {
        this.triggerItem = item;
    }


    //@return the reaction YOU DONT HAVE TO USE
    /*public String getReaction() {
        return reaction;
    }*/


    //@param reaction the reaction to set YOU DONT HAVE TO USE
    /*public void setReaction(String reaction) {
        this.reaction = reaction;
    }*/

    public Room(String name, String description) {
    }


    public void setName(String newName){
    }


    // @return the description
    public String getDescription() {
        return description;
    }


    // @param description the description to set
    public void setDescription(String description) {
    }


    //@return the items
    public Item[] getItems() {
        return items;
    }


    // @param items the items to set
    public void setItems(Item[] items) {
    }


    //return name
    public String getName() {
        return name;
    }


    // @return the exits
    public Room[] getExits() {
        return exits;
    }


    public void setExits(Room[] exitsArray){
    }

    /*public void reactToItem(Item i){
        if(i.equals(triggerItem)){
            System.out.println(reaction);
        }
        else{
            System.out.println("Nothing happened.");
        }
    }*/
}
