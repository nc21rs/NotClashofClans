package game_elements.inhabitant.army;

import game_elements.ArmyUnit;
import game_elements.ResourceType;
import game_elements.Resources;

public class Catapult extends ArmyUnit {
    public Catapult() {
        setHealth(100);
        setAttackDamage(50);
        setRange(4);
        setLevel(1);
        setDestroyed(false);
        setOnField(false);
        setMaxLevel(10);

        Resources productionCost = new Resources();
        productionCost.setAmount(ResourceType.WOOD, 40);
        productionCost.setAmount(ResourceType.IRON, 30);
        setProductionCost(productionCost);

        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.WOOD, 25);
        upgradeCost.setAmount(ResourceType.IRON, 20);
        setUpgradeCost(upgradeCost);

    }
}
