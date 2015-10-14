/**
 * Created by biancaaanoel on 5/7/15.
 */
public class MobileCharacter {
    private String name;
    private String description;
    private Room currentRoom;

    public MobileCharacter(String n, String d, Room s) {
        name = n;
        description = d;
        currentRoom = s;

    }

    public void changeRoom(){
        Room[] exits = currentRoom.getExits();
        Random r = new Random();
        //Code here to generate a random number that can be used as the next room index.

        currentRoom = exits[nextRoomIndex];
    }

    public String getDescription() {
        return description;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }


}
