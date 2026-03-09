package game_elements;

/**
 * This interface represents the upgradeable elements in the game, such as
 * buildings and units.
 */
public interface Upgradeable {

    /**
     * Returns the current level of the upgradeable element.
     * 
     * @return current level
     */
    int getLevel();

    /**
     * Returns the maximum level that the upgradeable element can reach.
     * 
     * @return maximum level
     */
    int getMaxLevel();

    /**
     * Returns the cost required to upgrade the element.
     * 
     * @return upgrade cost
     */
    Resources getUpgradeCost();

    /**
     * Upgrades the element to the next level.
     * Should increase level by 1 and update attributes accordingly.
     */
    void upgrade();
}
