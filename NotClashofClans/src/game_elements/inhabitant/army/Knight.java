package game_elements.inhabitant.army;

import game_elements.ArmyUnit;
import game_elements.ResourceType;
import game_elements.Resources;

/**
 * This class represents the Knight unit in the game. It extends the ArmyUnit
 * class.
 */
public class Knight extends ArmyUnit {
    public Knight() {
        setHealth(150);
        setAttackDamage(30);
        setRange(1);
        setLevel(1);
        setDestroyed(false);
        setOnField(false);
        setMaxLevel(10);

        Resources productionCost = new Resources();
        productionCost.setAmount(ResourceType.FOOD, 30);
        productionCost.setAmount(ResourceType.IRON, 25);
        setProductionCost(productionCost);

        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.FOOD, 20);
        upgradeCost.setAmount(ResourceType.IRON, 20);
        setUpgradeCost(upgradeCost);
    }
}
