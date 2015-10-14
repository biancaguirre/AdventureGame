

/*Bianca Aguirre
4/23/15
HW #3
Description: A text based adventure video game about my day to day life. You play the character as me, Bianca Aguirre and go through
the motions of my day from waking up, making breakfast, going to practice and school and then coming home. The challenge is to stay
awake and make it home at the end of the day with out taking the SNOOZE option which is game over.

 */
    import java.util.Scanner;

    public class aTextedBasedAdventure {
        public static Scanner kb = new Scanner(System.in);

        //These will be used to determine how much space is needed in the array.
        static final int NUMBER_OF_ROOMS = 5;
        static final int NUMBER_OF_ATTRIBUTES = 6;

        //Same here
        static final int NUMBER_OF_ITEMS = 3;
        static final int NUMBER_OF_PROPERTIES = 3;

        //Labels to use for our character's array
        static final int NUMBER_OF_CHARACTER_ATTRIBUTES = 2;

        //Our character
        public static String[] character = new String[NUMBER_OF_CHARACTER_ATTRIBUTES];

        //Our map, defined here outside of any method, so we can access it inside all of them.
        public static String[][] rooms = new String[NUMBER_OF_ROOMS][NUMBER_OF_ATTRIBUTES];

        //Items that can be LOOKed at otherwise interacted with.
        public static String[][] items = new String[NUMBER_OF_ITEMS][NUMBER_OF_PROPERTIES];

        //Constants to give us cleaner naming instead of using numbers to index our arrays
        static final int NAME = 0;
        static final int DESCRIPTION = 1;
        static final int EXITS = 2;
        static final int ITEMS = 3;


        //Labels for items
        static final int OATMEAL = 0;
        static final int HOMEWORK = 1;
        static final int SNOOZE = 2;

        //Labels for rooms
        static final int BEDROOM = 0;
        static final int KITCHEN = 1;
        static final int PRACTICE = 2;
        static final int SCHOOL = 3;
        static final int HOME = 4;

        //Labels for our character array
        static final int INVENTORY = 1;

        //For keeping track of our current position on the map.
        static String currentRoom = "bedroom";
        static String currentItem = "oatmeal";

        //ends the game with gameOver
        static boolean gameOver = false;
        static String introduction = "You are going to experience a typical day in the life of Bianca Aguirre - Student " +
                "Athlete. These days are rough, \n" +
                "let see if you can stay awake all day and make it home at the end of the " +
                "day and do what you really want - snooze. \n " +
                "You better LOOK around to see what's next.";

        //First we create the map in the computer memory, then prompt the user for input repeatedly to take in commands.
        public static void main(String[] args) {
            createMap();
            setupItems();

            System.out.println(introduction);
            while (!gameOver) {
                getCommand();
            }
        }


        //Get commands as strings, I prompt the user with a ">"
        private static void getCommand() {
            System.out.print("What would you like to do? Commands: GO, TAKE, LOOK. ");
            System.out.print("> ");
            String command = kb.nextLine();
            //this only happens if they typed at least two words:
            if (command.contains(" ")) {
                String[] input = command.split(" ");
                command = input[0];
                String target = input[1];

                doCommand(command, target);
            } else {
                doCommand(command);
            }
        }

        //set ups the map of all the rooms with their descriptions, exits and items
        private static void createMap() {
            //Bedroom descriptions
            rooms[BEDROOM][NAME] = "Bedroom";
            rooms[BEDROOM][DESCRIPTION] = "You wake up up to your alarm, it is 5:00 am. Time to get up and get ready for " +
                    "your day. Or..... you can just TAKE the SNOOZE route and stay in bed forever.\n";
            rooms[BEDROOM][EXITS] = "KITCHEN";
            rooms[BEDROOM][ITEMS] = "SNOOZE";

            //Kitchen Description
            rooms[KITCHEN][NAME] = "Kitchen";
            rooms[KITCHEN][DESCRIPTION] = "The bright kitchen lights wake you up. I know you're hungry."
                    + "\nHurry, you're going to late! Look's like you have to TAKE the OATMEAL to go, grab your stuff and head out the door before " +
                    "6:30 am to get to PRACTICE on time. \n";
            rooms[KITCHEN][EXITS] = "BEDROOM, PRACTICE";
            rooms[KITCHEN][ITEMS] = "OATMEAL";

            // Practice description
            rooms[PRACTICE][NAME] = "Practice";
            rooms[PRACTICE][DESCRIPTION] = "You've arrived at practice. \n You have a big race this weekend so Coach is planning on Seat Racing " +
                    "to get a set line up for the boats. Look's like your day just got tougher. \n You want a seat in the Novice Eight boat " +
                    " so you've got about four 1000m peices to do and then off to a long day at SCHOOL. Goodluck! \n " ;
            rooms[PRACTICE][EXITS] = "SCHOOL";
            rooms[PRACTICE][ITEMS] = "none";

            // School description
            rooms[SCHOOL][NAME] = "School";
            rooms[SCHOOL][DESCRIPTION] = "Sacramento State is where you spend most of your days. Straight from practice to class with no shower in between, " +
                    "your classmates are bound too look \n at you funny but make sure you remember to write " +
                    "down all your HOMEWORK and don't decide to SNOOZE in your lectures. \n";
            rooms[SCHOOL][EXITS] = "HOME";
            rooms[SCHOOL][ITEMS] = "HOMEWORK, SNOOZE";

            // Home description
            rooms[HOME][NAME] = "Home";
            rooms[HOME][DESCRIPTION] = "It's 5:00 pm already? You've made it though the day! You're finally on your way home to do what you really want - " +
                    "time to SNOOZE. \n";
            rooms[HOME][EXITS] = "none";
            rooms[HOME][ITEMS] = "SNOOZE";
        }

        //set ups the items specified for each room
        public static void setupItems() {

            items = new String[3][2];
            //oatmeal description
            items[OATMEAL][NAME] = "Oatmeal";
            items[OATMEAL][DESCRIPTION] = "Oatmeal is a great choice to fuel your long day ahead! Time for PRACTICE\n";
            //homework description
            items[HOMEWORK][NAME] = "Homework";
            items[HOMEWORK][DESCRIPTION] = "Time to challenge your brain and work towards that degree! Now that you've got all your homework for the day " +
                    "its time to finally go HOME!\n";
            //snooze description
            items[SNOOZE][NAME] = "Snooze";
            items[SNOOZE][DESCRIPTION] = "You let your your exhaustion catch up to you! This sleep is undoubtedly going to be great \n" +
                    "but it GAME OVER for you.\n";

        }

        //If they didnt type a target, pass a blank one.
        private static void doCommand(String command) {
            doCommand(command, "");
        }

        //Trigger the matching function.
        public static void doCommand(String command, String target) {
            command = command.toLowerCase();
            target = target.toLowerCase();

            switch (command) {
                case "look":
                    if (target.length() > 0) {
                        look(target);
                    } else {
                        look();
                    }
                    break;
                case "go":
                    if (target.length() > 0) {
                        move(target);
                    } else {
                        System.out.println("Where to?");
                    }
                    break;
                case "take":
                    if (target.length() > 0) {
                        take(target);
                        // add item inventory
                    } else {
                        System.out.print("Take what?");
                    }
                    break;
                default:
                    System.out.println("Sorry, that isn't a command.");
            }
        }

        //Move our character to a destination
        public static void move(String destination) {
            boolean found = false;
            for (int room = 0; room < rooms.length; room++) {
                if (rooms[room][NAME].equalsIgnoreCase(destination)) {
                    if (getRoomNumber(destination) == getRoomNumber(currentRoom) + 1 || getRoomNumber(destination) == getRoomNumber(currentRoom) - 1) {
                        currentRoom = destination;
                        found = true;
                    }

                }
            }
                if (!found) {
                    System.out.println("Sorry, that room was not found:" + destination);

                } else {
                    System.out.println("You made it to " + currentRoom);
                    System.out.println(rooms[getRoomNumber(currentRoom)][DESCRIPTION]);

                    // setup items for specific room
                    // items array should be the right size
                }


        }

        //Looks through the rooms and returns the index of our destination.
        private static int getRoomNumber(String roomName) {
            int index = -1;
            for (int room = 0; room < rooms.length; room++) {
                if (rooms[room][NAME].equalsIgnoreCase(roomName)) {
                    index = room;

                }
            }
            return index;
        }

        //Looks through the items and returns the index of the item
        private static int getItemNum(String itemNum) {
            int indx = -1;
            for (int itm = 0; itm < items.length; itm++) {
                if(items[itm][NAME].equalsIgnoreCase(itemNum)) {
                    indx = itm;
                }
            }
            return indx;
        }

        //Prints the description of the item you are LOOKing at
        public static void look(String item) {
            for (int itemNum = 0; itemNum < items.length; itemNum++) {
                if (item.equalsIgnoreCase(items[itemNum][NAME])) {
                    System.out.println(items[itemNum][DESCRIPTION]);
                    break;
                }
            }
        }

        //Prints the description of the room, the items list, and the exits list.
        public static void look() {
            int CURRENT_ROOM_INDEX = getRoomNumber(currentRoom);
            System.out.print("You are currently in the " + rooms[CURRENT_ROOM_INDEX][NAME] + ". \n");
            System.out.print(rooms[CURRENT_ROOM_INDEX][DESCRIPTION]);
            System.out.print("Looking around you see  " + rooms[CURRENT_ROOM_INDEX][ITEMS] + ". ");
            System.out.print(rooms[CURRENT_ROOM_INDEX][NAME] + " has exits to " + rooms[CURRENT_ROOM_INDEX][EXITS]+ ". ");
        }

        //Add the item to our inventory, if it is in the room.
        public static void take(String item) {
            boolean fnd = false;
            for (int i = 0; i < items.length; i++) {
                if (items[i][NAME].equalsIgnoreCase(item)) {
                    //add code for add item from inventory
                    currentItem = item;
                    fnd = true;
                }
            }
            //checking results of loop
            if (!fnd) {
                System.out.print("Sorry this item was not found:" + item);
            } else {
                System.out.println("You chose to take " + currentItem);
                System.out.print(items[getItemNum(currentItem)][DESCRIPTION]);

                //when take snooze, game ends
                if (currentItem.equalsIgnoreCase("snooze")) {
                    gameOver = true;
                }
            }

        }

        //Remove the item from our inventory
        //not used
        public static void use(String item) {
            for (int itemNum = 0; itemNum < items.length; itemNum++) {
                if (item.equalsIgnoreCase(items[itemNum][NAME])) {
                    //add code for removing item form inventory array
                }
            }
        }
    }
