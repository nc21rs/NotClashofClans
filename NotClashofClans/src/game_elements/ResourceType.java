package game_elements;

/**
 * Class returns Standardized index for any added resources
 * Indexing is done automatically, simply add Resource to the list of enums.
 */
public enum ResourceType {
    FOOD(0), WOOD(1), IRON(2), GOLD(3), POPULATION(4);

    private int index;
    // constructor (but its special)
    ResourceType(int index) {
        this.index = index;
    }

    public int getIndex() { // static get method
        return index;
    }
}
