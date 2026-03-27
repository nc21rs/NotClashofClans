package game_elements.exceptions;

public class NothingToUpgrade extends Exception {
    public NothingToUpgrade() {
        super("Error: There is nothing to upgrade");
    }
}
