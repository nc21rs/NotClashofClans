package game_elements.inhabitant.villager;

import game_elements.ResourceType;
import game_elements.Resources;
import game_elements.Worker;

public class Builder extends Worker {
    public Builder(){
        setHealth(70);
        setAttackDamage(5);
        setRange(1);
        setLevel(1);
        setDestroyed(false);
        setIdle(true);
        setMaxLevel(10);
        setName("Builder");
        setShortName('b');

        Resources productionCost = new Resources();
        productionCost.setAmount(ResourceType.FOOD, 10);
        productionCost.setAmount(ResourceType.WOOD, 10);
        setProductionCost(productionCost);

        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.FOOD, 10);
        upgradeCost.setAmount(ResourceType.WOOD, 5);
        setUpgradeCost(upgradeCost);
    }
}
