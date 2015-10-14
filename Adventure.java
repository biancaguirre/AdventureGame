/**
 * Created by biancaaanoel on 5/7/15.
 */
import java.util.Scanner;

/**
 Template for the game programming assignment
 Jerod Ewert
 5/1/2015

 Story based on The Wreck of the Charles Dexter Ward pt. 1  by  Elizabeth Bear and Sarah Monette
 */
public class Adventure {
    //These will be used to determine how much space is needed in the array.
    static final int NUMBER_OF_ROOMS = 5;

    //Our character
    public static Character character = new Character("Bianca");

    //Our Mobile character
    public static MobileCharacter mob;

    //Our map, defined here outside of any method, so we can access it inside all of them.
    public static Room[] rooms = new Room[NUMBER_OF_ROOMS];

    //Labels for rooms
    static final int BEDROOM  = 0;
    static final int KITCHEN  = 1;
    static final int PRACTICE = 2;
    static final int SCHOOL = 3;
    static final int HOME   = 4;

    static boolean gameOver = false;

    static String introduction = "You are going to experience a typical day in the life of Bianca Aguirre - Student "
            +"Athlete. These days are rough, \n" +
            "let see if you can stay awake all day and make it home at the end of the " +
            "day and do what you really want - snooze. \n" +
            "You better LOOK around to see what's next. ";
    public static Scanner kb = new Scanner(System.in);

    //First we create the map in the computer memory, then prompt the user for input repeatedly to take in commands.
    public static void main(String[] args){
        createMap();

        System.out.println(introduction);

        while(!gameOver){
            mob.changeRoom();
            getCommand();

            if( character.getCurrentRoom().equals(rooms[BEDROOM]) ){
                gameOver = true;
            }
        }
        System.out.println("GAME OVER");
    }


    private static void getCommand() {
        System.out.print("> ");
        String command = kb.nextLine();
        if(command.contains(" ")){
            String target = splitString(command)[1];
            command = splitString(command)[0];

            doCommand(command, target);
        }
        else{
            doCommand(command);
        }
    }


    private static void createMap() {
        //1.Create all the items, using your custom Item object.
        //oatmeal description
        Item oatmeal = new Item("oatmeal", "Oatmeal is a great choice to fuel your long day ahead! Time for PRACTICE \n");
        //snooze description
        Item snooze = new Item("snooze", "You let your your exhaustion catch up to you! This sleep is undoubtedly going to be great\n" +
                "but it GAME OVER for you.\n");
        //homework description
        Item homework = new Item("practice", "Time to challenge your brain and work towards that degree! Now that you've got all your homework for the day" +
                "its time to finally go HOME!\n");


        //2.Create all the rooms, adding the items to them
        rooms[BEDROOM] = new Room("Bedroom", "You wake up up to your alarm, it is 5:00 am. Time to get up and get ready for" +
                "your day. Or..... you can just TAKE the SNOOZE route and stay in bed forever.\n");
        //An empty array because there are no items here.
        //This prevents null pointer problems later when we try to look through the items.
        rooms[BEDROOM].setItems(new Item[0]);

        //KITCHEN Description
        rooms[KITCHEN] = new Room("Kitchen", "The bright kitchen lights wake you up. I know you're hungry." +
                "\nHurry, you're going to late! Look's like you have to TAKE the OATMEAL to go, grab your stuff and head out the door before " +
                "\n6:30 am to get to PRACTICE on time.");
        Item[] roomItems = new Item[1];
        roomItems[0] = oatmeal;
        rooms[KITCHEN].setItems(roomItems);

        //Practice Description
        rooms[PRACTICE] = new Room("Practice", "You've arrived at practice. \n You have a big race this weekend so Coach is planning on Seat Racing" +
                " to get a set line up for the boats. Look's like your day just got tougher. \n You want a seat in the Novice Eight boat" +
                "so you've got about four 1000m peices to do and then off to a long day at SCHOOL. Goodluck! \n");
        //An empty array because there are no items here.
        //This prevents null pointer problems later when we try to look through the items.
        rooms[PRACTICE].setItems(new Item[0]);

        //School Description
        rooms[SCHOOL] = new Room("School", "Sacramento State is where you spend most of your days. Straight from practice to class with no shower in between, " +
                "your classmates are bound too look \n at you funny but make sure you remember to write" +
                "down all your HOMEWORK and don't decide to SNOOZE in your lectures. \n");
        Item[] roomItems = new Item[2];
        roomItems[0] = homework;
        roomItems[1] = snooze;
        rooms[SCHOOL].setItems(roomItems);

        //Home Description
        rooms[HOME] = new Room("Home", "It's 5:00 pm already? You've made it though the day! You're finally on your way home to do what you really want - " +
                "time to SNOOZE. \n");
        Item[] roomItems = new Item[1];
        roomItems[0] = snooze;
        rooms[HOME].setItems(roomItems);


        //3.Connect the rooms.
        //bedroom > kitchen
        Room[] exitList = new Room[1];
        exitList[0] = rooms[KITCHEN];
        rooms[BEDROOM].setExits(exitList);

        //kitchen > practice/bedroom
        Room[] exitList = new Room[2];
        exitList[0] = rooms[PRACTICE];
        exitList[1] = rooms[BEDROOM];
        rooms[KITCHEN].setExits(exitList);

        //practice > school/kitchen
        Room[] exitList = new Room[2];
        exitList[0] = rooms[SCHOOL];
        exitList[1] = rooms[KITCHEN];
        rooms[PRACTICE].setExits(exitList);

        //school > home/practice
        Room[] exitList = new Room[2];
        exitList[0] = rooms[HOME];
        exitList[1] = rooms[PRACTICE];
        rooms[SCHOOL].setExits(exitList);

        //home > school
        Room[] exitList = new Room[1];
        exitList[0] = rooms[PRACTICE];
        rooms[HOME].setExits(exitList);

        //4.Set triggers and reactions if you need them, they are optional.
        //bedroom
        /*rooms[BEDROOM].setTriggerItem(snooze);
        rooms[BEDROOM].setReaction("The machine spits out a suitable garmet made of sharp paper. hehe, looking sharp!");

        //kitchen
        rooms[KITCHEN].setTriggerItem(oatmeal);
        rooms[KITCHEN].setReaction("The machine spits out a suitable garmet made of sharp paper. hehe, looking sharp!");

        //school
        rooms[SCHOOL].setTriggerItem(homework);
        rooms[SCHOOL].setReaction("The machine spits out a suitable garmet made of sharp paper. hehe, looking sharp!");

        rooms[SCHOOL].setTriggerItem(snooze);
        rooms[SCHOOL].setReaction("The machine spits out a suitable garmet made of sharp paper. hehe, looking sharp!");

        //home
        rooms[HOME].setTriggerItem(snooze);
        rooms[HOME].setReaction("The machine spits out a suitable garmet made of sharp paper. hehe, looking sharp!");
        */


        //5.Set your character and mob in a room.
        character.setCurrentRoom(rooms[BEDROOM]);
        mob = new MobileCharacter("Rabbit", "A small white rabbit", rooms[BEDROOM]);

    }


    //Take user input, trigger the doCommand method
    private static void doCommand(String command) {
        doCommand(command, "");
    }

    public static void doCommand(String command, String target){
        command = command.toLowerCase();
        target = target.toLowerCase();

        switch(command){
            case "look":
                if(target.length() > 0){
                    look(target);
                }
                else{
                    look();
                }
                break;
            case "go":
                if(target.length() > 0){
                    move(target);
                }
                else{
                    System.out.println("The go command requires a destination.");
                }
                break;
            case "get":
                if(target.length() > 0){
                    get(target);
                }
                else{
                    System.out.println("GET requires an item to get.");
                }
                break;
            default:
                System.out.println("Sorry, that isnt a command.");
        }
    }

    /*private static void put(String target) {

        System.out.println("Putting the "+target);
        Item i = character.findItem(target);
        if(i != null){
            character.getCurrentRoom().reactToItem(i);
        }
    }*/


    //Move our character to an x and y position
    public static void move(String destination){
        boolean found = false;
        for(int room = 0; room < rooms.length; room++){
            if(rooms[room].getName().equalsIgnoreCase(destination)){
                character.setCurrentRoom(rooms[room]);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("Sorry, that room was not attached to this room:"+destination);
        }
        else{
            look();
        }

    }

    //Prints the description of the item you are LOOKing at
    public static void look(String item){
        Room currentRoom = character.getCurrentRoom();
        Item[] items = currentRoom.getItems();
        String itemName;

        for(int itemNum = 0; itemNum < items.length; itemNum++){
            itemName = items[itemNum].getName();
            if (itemName.equals(item)) {
                System.out.print();

            }
        }
        System.out.println("Couldn't find the item: " + item);
    }


    //Returns the description of the room
    public static void look(){
        Room room = character.getCurrentRoom();
        System.out.println(room.getDescription());

        if(room.getItems().length > 0){
            System.out.println("ITEMS:");
            Item[] items = room.getItems();
            //Print out the list of items by going through the array.

        }

        if(room.getExits().length > 0){
            //Same here.
        }

        if( mob.getCurrentRoom().equals(room) ){
            //What would you like to happen when the other character is in the room with you?
            //Printing their description is sufficient.
        }
    }

    //Add the item to our inventory, if it is in the room.
    public static void get(String item){
        Item[] items = character.getCurrentRoom().getItems();
        //Use character.addToInventory(...) to add the items to the inventory;

    }

    public static String[] splitString(String input) {
        String[] output = new String[2];
        String target = input.substring(input.indexOf(" ") + 1, input.length());
        String command = input.substring(0, input.indexOf(" "));

        output[0] = command;
        output[1] = target;

        return output;
    }
}
