package game_elements.inhabitant.army;

import game_elements.ArmyUnit;
import game_elements.ResourceType;
import game_elements.Resources;

/**
 * This class represents the Archer unit in the game. It extends the ArmyUnit class.
 */
public class Archer extends ArmyUnit {
    public Archer() {
        setName("Archer");
        setHealth(80);
        setAttackDamage(15);
        setRange(3);
        setLevel(1);
        setDestroyed(false);
        setOnField(false);
        setMaxLevel(10);

        // Set production cost
        Resources productionCost = new Resources();
        productionCost.setAmount(ResourceType.FOOD, 25);
        productionCost.setAmount(ResourceType.WOOD, 5);
        setProductionCost(productionCost);

        // Set upgrade cost
        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.FOOD, 20);
        upgradeCost.setAmount(ResourceType.WOOD, 10);
        setUpgradeCost(upgradeCost);
    }
}
