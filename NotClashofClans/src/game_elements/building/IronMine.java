package game_elements.building;

import game_elements.ResourceBuilding;
import game_elements.ResourceType;
import game_elements.Resources;

public class IronMine extends ResourceBuilding {
    public IronMine(){
        //assign values for building
        setHealth(25);
        setName("Iron Mine");
        setLevel(1);
        setMaxLevel(10);
        setDestroyed(false);
        setWorkerCapacity(3);
        setShortName('I');

        //Build Cost
        Resources buildCost = new Resources();
        buildCost.setAmount(ResourceType.WOOD,50);
        //Build Upgrade
        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.IRON,25);
        //Resource Production Type and Quantity
        Resources productionOutput = new Resources();
        productionOutput.setAmount(ResourceType.IRON,75);

        setProduction(productionOutput);
        setProductionCost(buildCost);
        setUpgradeCost(upgradeCost);
    }
}
