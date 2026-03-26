package game_elements.inhabitant.villager;

import game_elements.ResourceType;
import game_elements.ResourceVillager;
import game_elements.Resources;

/**
 * This class represents the Collector villager in the game. It extends the
 * ResourceVillager class and is responsible for collecting wood getResources for the
 * player's village.
 */
public class Collector extends ResourceVillager {
    public Collector(){
        setName("Collector");
        setHealth(70);
        setAttackDamage(5);
        setRange(1);
        setLevel(1);
        setDestroyed(false);
        setProductionCapacity(20);
        setResourceProduced(ResourceType.WOOD);
        setMaxLevel(10);

        Resources productionCost = new Resources();
        productionCost.setAmount(ResourceType.FOOD, 15);
        productionCost.setAmount(ResourceType.WOOD, 10);
        setProductionCost(productionCost);

        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.FOOD, 5);
        upgradeCost.setAmount(ResourceType.WOOD, 5);
        setUpgradeCost(upgradeCost);
    }
}
