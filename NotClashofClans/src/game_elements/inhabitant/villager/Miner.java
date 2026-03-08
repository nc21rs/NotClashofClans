package game_elements.inhabitant.villager;

import game_elements.ResourceType;
import game_elements.ResourceVillager;
import game_elements.Resources;

public class Miner extends ResourceVillager {
    public Miner(){
        setHealth(70);
        setAttackDamage(5);
        setRange(1);
        setLevel(1);
        setDestroyed(false);
        setProductionCapacity(20);
        setMaxLevel(10);

        Resources productionCost = new Resources();
        productionCost.setAmount(ResourceType.FOOD, 10);
        setProductionCost(productionCost);

        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.FOOD, 5);
        setUpgradeCost(upgradeCost);
    }
}
