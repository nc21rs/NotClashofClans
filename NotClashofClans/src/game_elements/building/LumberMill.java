package game_elements.building;

import game_elements.ResourceBuilding;
import game_elements.ResourceType;
import game_elements.Resources;

/**
 * This class represents the Lumber Mill building in the game.
 * It also extends the ResourceBuilding class.
 */
public class LumberMill extends ResourceBuilding {
    public LumberMill() {
        // assign building values
        setHealth(25);
        setName("Lumber Mill");
        setLevel(1);
        setMaxLevel(10);
        setDestroyed(false);
        setMaxWorkers(3);   //max is 3
        setNumWorkers(0);   //starts with 0
        setShortName('L');

        // Build Cost
        Resources buildCost = new Resources();
        buildCost.setAmount(ResourceType.FOOD, 25);
        // Build Upgrade
        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.WOOD, 25);
        // Resource Production Type and Quantity
        Resources productionOutput = new Resources();
        productionOutput.setAmount(ResourceType.WOOD, 75);

        setProduction(productionOutput);
        setProductionCost(buildCost);
        setUpgradeCost(upgradeCost);
    }
}
