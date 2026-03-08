package game_elements;

/**
 * Represents the main building of the village.
 * The Village Hall limtis the progression of the village.
 */
public class VillageHall extends Building {

    /**
     * CRITICAL REQUIREMENT: Player starts with 2 population
     *
     * Create Village Hall with default values.
     */
    public VillageHall() {
        setHealth(200);
        setName("Village Hall");
        setLevel(1);
        setMaxLevel(10);
        setDestroyed(false);

        // Set upgrade cost
        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.IRON, 25);
        setUpgradeCost(upgradeCost);
    }
}
