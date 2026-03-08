package game_elements.building;

import game_elements.ResourceBuilding;
import game_elements.ResourceType;
import game_elements.Resources;

public class GoldMine extends ResourceBuilding {
    public GoldMine(){
        //assign values for building
        setHealth(25);
        setName("Gold Mine");
        setLevel(1);
        setMaxLevel(10);
        setDestroyed(false);
        setWorkerCapacity(5);

        //Build Cost
        Resources buildCost = new Resources();
        buildCost.setAmount(ResourceType.WOOD,50);
        buildCost.setAmount(ResourceType.IRON,50);

        //Build Upgrade
        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.WOOD,25);
        upgradeCost.setAmount(ResourceType.IRON,25);

        //Resource Production Type and Quantity
        Resources productionOutput = new Resources();
        productionOutput.setAmount(ResourceType.GOLD,5);

        setProduction(productionOutput);
        setProductionCost(buildCost);
        setUpgradeCost(upgradeCost);
    }
}
