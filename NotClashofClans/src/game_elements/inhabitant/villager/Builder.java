package game_elements.inhabitant.villager;

import game_elements.ResourceType;
import game_elements.Resources;
import game_elements.Worker;

/**
 * This class represents the Builder villager in the game. It extends the Worker
 * class and is responsible for constructing and repairing buildings in the
 * village.
 */
public class Builder extends Worker {
    public Builder(){
        setHealth(70);
        setAttackDamage(5);
        setRange(1);
        setLevel(1);
        setDestroyed(false);
        setIdle(true);
        setMaxLevel(10);

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
