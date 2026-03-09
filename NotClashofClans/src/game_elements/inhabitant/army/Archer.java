package game_elements.inhabitant.army;

import game_elements.ArmyUnit;
import game_elements.ResourceType;
import game_elements.Resources;

public class Archer extends ArmyUnit {
    public Archer() {
        setHealth(80);
        setAttackDamage(15);
        setRange(3);
        setLevel(1);
        setDestroyed(false);
        setOnField(false);
        setMaxLevel(10);
        setName("Archer");
        setShortName('a');

        // Set production cost
        Resources productionCost = new Resources();
        productionCost.setAmount(ResourceType.FOOD, 25);
        productionCost.setAmount(ResourceType.IRON, 5);
        setProductionCost(productionCost);

        // Set upgrade cost
        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.FOOD, 20);
        upgradeCost.setAmount(ResourceType.IRON, 10);
        setUpgradeCost(upgradeCost);
    }
}
