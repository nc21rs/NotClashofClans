package game_elements.exceptions;

public class MaxLevel extends Exception {
    public MaxLevel(String target) {
        super("Error: " + target + " at Max Level");
    }
}
