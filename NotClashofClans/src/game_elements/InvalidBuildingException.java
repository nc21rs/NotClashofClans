package game_elements;

public class InvalidBuildingException extends Exception {

    /*
    * Custom exception class to handle invalid building choices in the game.
    * This exception can be thrown when a user selects a building type that is not
    * available.
    */
    public InvalidBuildingException(String message) {
        super(message);
    }
}