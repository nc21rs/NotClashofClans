package game_user_interface;

/* 
* Custom exception class to handle invalid menu choices in the user interface.
* This exception can be thrown when a user selects an option that is not available in the manu.
*/
public class InvalidMenuChoiceException extends Exception {
    public InvalidMenuChoiceException(String message) {
        super(message);
    }
}