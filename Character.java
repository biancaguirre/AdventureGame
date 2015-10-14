/**
 * Created by biancaaanoel on 5/7/15.
 */
public class Character {
    private String name;
    private Room currentRoom;
    private int currentIndex = 0;
    private int inventorySize = 10;

    private Item[] inventory = new Item[inventorySize];

    public Character(String name) {
        this.name = name;
    }

    /**
     * If the inventory is full, make a new array twice as large and copy over all the items.
     * otherwise, just set the current slot to the passed in item, and increase the currentIndex
     * @param item
     */
    public void addToInventory(Item item){
        if(currentIndex >= inventory.length){
            //...
        }
        //Now we can safely add to the inventory.
        inventory[currentIndex] = item;
        currentIndex++;
    }

    public Item findItem(String itemName) throws Exception{
        //For each item, check to see if the name matches.
        //If it does: return the Item object.
        //Else:
        System.out.print("You don't have the item: " + itemName);
        //If you dont find the item, throw an Exception
        throw new Exception("Item not found!");
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
    }

    public String getName() {
        return name;
    }

    /**
     * Cool extra: If you want to implement removing an item from the inventory,
     * Find the index of the bad item, and replace that with the next item and repeat until you reach the currentIndex - 1
     */
}
