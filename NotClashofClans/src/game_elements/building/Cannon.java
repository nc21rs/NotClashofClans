package game_elements.building;

import game_elements.DefenceBuilding;
import game_elements.ResourceType;
import game_elements.Resources;

/**
 * This class represents the Cannon building in the game.
 * It also extends the DefenceBuilding class.
 */
public class Cannon extends DefenceBuilding {
    public Cannon() {
        // assign building values
        setHealth(100);
        setDamage(20);
        setRange(1);
        setName("Cannon");
        setLevel(1);
        setMaxLevel(10);
        setDestroyed(false);
        setShortName('C');

        Resources buildCost = new Resources();
        buildCost.setAmount(ResourceType.WOOD, 25);
        buildCost.setAmount(ResourceType.IRON, 25);

        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.WOOD, 15);
        upgradeCost.setAmount(ResourceType.IRON, 10);

        setProductionCost(buildCost);
        setUpgradeCost(upgradeCost);
    }
}
