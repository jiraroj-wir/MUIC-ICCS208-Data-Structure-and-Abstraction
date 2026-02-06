/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords {
    // NOTE: I think this one is good already, no need for enum
    // a constant array that holds all valid command words
    // private static final String[] validCommands = {"go", "quit", "help"};
    public enum CommandWord {
        GO("go"),
        QUIT("quit"),
        HELP("help"),
        LOOK("look");

        private final String word;

        CommandWord(String word) { this.word = word; }

        public String getWord() { return word; }
    }
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords() {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word.
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString) {
        for (CommandWord command : CommandWord.values()) {
            if (command.getWord().equals(aString)) {
                return true;
            }
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /*
    public boolean isCommand(String aString) {
        for (int i = 0; i < validCommands.length; i++) {
            if (validCommands[i].equals(aString))
                return true;
        }
        return false;
    }
    */

    public static String commandList() {
        StringBuilder builder = new StringBuilder();

        for (CommandWord command : CommandWord.values()) {
            builder.append(command.getWord()).append(' ');
        }

        return builder.toString().trim();
    }
}
