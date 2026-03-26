package game_elements.building;

import game_elements.Building;
import game_elements.ResourceType;
import game_elements.Resources;

/**
 * Represents the main building of the village.
 * The Village Hall limtis the progression of the village.
 */
public class TownHall extends Building {

    /**
     * CRITICAL REQUIREMENT: Player starts with 2 population
     *
     * Create Village Hall with default values.
     */
    public TownHall() {
        setHealth(200);
        setName("Village Hall");
        setLevel(1);
        setMaxLevel(10);
        setDestroyed(false);
        setShortName('H');
        // Set upgrade cost
        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.IRON, 25);
        setUpgradeCost(upgradeCost);
    }
}
