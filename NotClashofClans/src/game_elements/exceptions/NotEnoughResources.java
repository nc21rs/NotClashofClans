package game_elements.exceptions;

public class NotEnoughResources extends Exception {
    public NotEnoughResources() {
        super("Error: Missing Resources");
    }
}
