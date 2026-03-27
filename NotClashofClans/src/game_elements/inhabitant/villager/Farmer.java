package game_elements.inhabitant.villager;

import game_elements.ResourceType;
import game_elements.Resources;
import game_elements.ResourceVillager;

/**
 * This class represents the Farmer villager in the game. It extends the
 * ResourceVillager class and is responsible for producing food getResources for
 * the
 * player's village.
 */
public class Farmer extends ResourceVillager {
    public Farmer() {
        setName("Farmer");
        setHealth(70);
        setAttackDamage(5);
        setRange(1);
        setLevel(1);
        setDestroyed(false);
        setProductionCapacity(10);
        setResourceProduced(ResourceType.FOOD);
        setMaxLevel(10);

        Resources productionCost = new Resources();
        productionCost.setAmount(ResourceType.FOOD, 10);
        setProductionCost(productionCost);

        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.FOOD, 5);
        setUpgradeCost(upgradeCost);
    }
}
