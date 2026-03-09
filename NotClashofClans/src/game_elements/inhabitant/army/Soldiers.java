package game_elements.inhabitant.army;

import game_elements.ArmyUnit;
import game_elements.ResourceType;
import game_elements.Resources;

/**
 * This class represents the Soldiers unit in the game. It extends the ArmyUnit
 * class.
 */
public class Soldiers extends ArmyUnit {
    public Soldiers() {
        setHealth(100);
        setAttackDamage(20);
        setRange(1);
        setLevel(1);
        setDestroyed(false);
        setOnField(false);
        setMaxLevel(10);

        Resources productionCost = new Resources();
        productionCost.setAmount(ResourceType.FOOD, 20);
        productionCost.setAmount(ResourceType.IRON, 10);
        setProductionCost(productionCost);

        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.FOOD, 15);
        upgradeCost.setAmount(ResourceType.IRON, 15);
        setUpgradeCost(upgradeCost);
    }
}
