package game_elements;

public class InvalidInhabitantException extends Exception {
    /*
     * Custom exception class to handle invalid inhabitant choices in the game.
     * This exception can be thrown when a user selects an inhabitant type that is not
     * available.
     */
    public InvalidInhabitantException(String message) {
        super(message);
    }
}
