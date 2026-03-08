package game_elements.inhabitant.villager;

import game_elements.ResourceType;
import game_elements.Resources;
import game_elements.Worker;

public class Farmer extends Worker {
    public Farmer(){
        setHealth(70);
        setAttackDamage(5);
        setRange(1);
        setLevel(1);
        setDestroyed(false);
        setIdle(true);
        setMaxLevel(10);

        Resources productionCost = new Resources();
        productionCost.setAmount(ResourceType.FOOD, 10);
        setProductionCost(productionCost);

        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.FOOD, 5);
        setUpgradeCost(upgradeCost);
    }
}
