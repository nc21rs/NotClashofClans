package game_elements.building;

import game_elements.ResourceBuilding;
import game_elements.ResourceType;
import game_elements.Resources;

public class Farm extends ResourceBuilding {
    private int populationFed;
    public Farm(){
        //assign buildings values
        setHealth(25);
        setName("Crop Farm");
        setLevel(1);
        setMaxLevel(10);
        setDestroyed(false);
        setWorkerCapacity(1);
        setShortName('F');

        //Build Cost
        Resources buildCost = new Resources();
        //Zero cost to build

        //Build Upgrade
        Resources upgradeCost = new Resources();
        //Resource Production Type and Quantity
        Resources productionOutput = new Resources();
        productionOutput.setAmount(ResourceType.FOOD,100);

        setProduction(productionOutput);
        setProductionCost(buildCost);
        setUpgradeCost(upgradeCost);
    }

    protected int getPopulationFed() {
        return populationFed;
    }
}
