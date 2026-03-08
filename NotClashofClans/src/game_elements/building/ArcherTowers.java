package game_elements.building;

import game_elements.DefenceBuilding;
import game_elements.ResourceType;
import game_elements.Resources;

public class ArcherTowers extends DefenceBuilding {
    public ArcherTowers(){
        //assign building values
        setHealth(50);
        setDamage(10);
        setRange(3);
        setName("Archer Tower");
        setLevel(1);
        setMaxLevel(10);
        setDestroyed(false);

        Resources buildCost = new Resources();
        buildCost.setAmount(ResourceType.WOOD,50);

        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.WOOD,25);
        upgradeCost.setAmount(ResourceType.IRON,10);

        setProductionCost(buildCost);
        setUpgradeCost(upgradeCost);
    }
}
