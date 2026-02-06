/**
 *  This class is the main class of the "World of Zuul" application.
 *  "World of Zuul" is a very simple, text based adventure game.  Users
 *  can walk around some scenery. That's all. It should really be extended
 *  to make it more interesting!
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

import java.util.Random;

public class Game {
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom = null;
    private Room labRoom;
    private Room libraryRoom;
    private Room magicRoomTransporter;
    private final Random random = new Random();

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room outside, theater, pub, lab, office, library, cafeteria, magicRoom;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        library = new Room("inside the quiet campus library");
        cafeteria = new Room("inside the bustling student cafeteria");
        magicRoom = new Room("inside a mysterious magic room");

        labRoom = lab;
        libraryRoom = library;
        magicRoomTransporter = magicRoom;

        // initialise room exits
        /*
         * (north, east, south, west)
        outside.setExits(null, theater, lab, pub);
        theater.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        lab.setExits(outside, office, null, null);
        office.setExits(null, null, null, lab);
        */
        outside.setExit(Direction.EAST, theater);
        outside.setExit(Direction.SOUTH, lab);
        outside.setExit(Direction.DOWN, magicRoom);
        outside.setExit(Direction.WEST, pub);
        outside.setExit(Direction.NORTH, library);

        theater.setExit(Direction.WEST, outside);
        theater.setExit(Direction.NORTH, cafeteria);

        pub.setExit(Direction.EAST, outside);
        pub.setExit(Direction.NORTH, library);

        lab.setExit(Direction.NORTH, outside);
        lab.setExit(Direction.EAST, office);

        office.setExit(Direction.WEST, lab);

        library.setExit(Direction.SOUTH, outside);
        library.setExit(Direction.EAST, cafeteria);
        library.setExit(Direction.WEST, pub);

        cafeteria.setExit(Direction.WEST, library);
        cafeteria.setExit(Direction.SOUTH, theater);

        magicRoom.setExit(Direction.SOUTH, lab);

        currentRoom = outside; // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();

        /*
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        if(currentRoom.northExit != null) {
            System.out.print("north ");
        }
        if(currentRoom.eastExit != null) {
            System.out.print("east ");
        }
        if(currentRoom.southExit != null) {
            System.out.print("south ");
        }
        if(currentRoom.westExit != null) {
            System.out.print("west ");
        }
        System.out.println();
        */

        System.out.printf("" + describeRoom(currentRoom) + "\n");
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("look")) {
            look();
        } else if (commandWord.equals("back")) {
            goBack(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   " + CommandWords.commandList());
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Direction desiredDirection = Direction.fromString(direction);
        if (desiredDirection == null) {
            System.out.println("That isn't a valid direction.");
            return;
        }

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(desiredDirection);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            /*
             * magic room feature: when stepped into the room, you got teleported to random room
             *
             * *** I've manually picked the lab and library 'randomly' as the destination
             * *** if you aim for a proper randomized room, I think creating a `static List<Room>` field in room class,
             * that got update everytime new room has been initialized; then, pull one room from the list, randomly
             * should gives some random room (exclude the magic room itself)
             *
             * P.S. As a bad speaker, I spent 4 days preparing for my public speaking (EC3), and now i don't have enough
             * time to complete this assignment properly.
             */
            if (nextRoom == magicRoomTransporter) {
                System.out.println("You step into the magic room and feel reality warp!");
                nextRoom = random.nextBoolean() ? labRoom : libraryRoom;
            }
            previousRoom = currentRoom;
            currentRoom = nextRoom;

            /*
            System.out.println("You are " + currentRoom.getDescription());
            System.out.print("Exits: ");
            if(currentRoom.northExit != null) {
                System.out.print("north ");
            }
            if(currentRoom.eastExit != null) {
                System.out.print("east ");
            }
            if(currentRoom.southExit != null) {
                System.out.print("south ");
            }
            if(currentRoom.westExit != null) {
                System.out.print("west ");
            }
            System.out.println();
            */

            System.out.printf("" + describeRoom(currentRoom) + "\n");
        }
    }

    private String describeRoom(Room current_room) {
        StringBuilder description = new StringBuilder();

        description.append("You are ").append(current_room.getDescription()).append('\n');
        description.append("Exits: ");

        /*
        if (current_room.northExit != null) {
            description.append("north ");
        }
        if (current_room.eastExit != null) {
            description.append("east ");
        }
        if (current_room.southExit != null) {
            description.append("south ");
        }
        if (current_room.westExit != null) {
            description.append("west ");
        }
        */
        for (Direction direction : current_room.getAvailableDirections()) {
            description.append(direction).append(' ');
        }

        return description.toString();
    }

    private void look() { System.out.printf("Exits: " + describeRoom(currentRoom) + '\n'); }

    /*
     * go back to the previous room
     * *** this implementation only work once, you can't do multiple go backs
     * *** using a deque will allows the program to revert back to all moves, but i don't have much time left
     */
    private void goBack(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Back what?");
            return;
        }

        if (previousRoom == null) {
            System.out.println("You haven't gone anywhere yet.");
            return;
        }

        Room target = previousRoom;
        previousRoom = currentRoom;
        currentRoom = target;

        System.out.printf("%s%n", describeRoom(currentRoom));
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }
}
