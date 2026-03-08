package game_elements;

public class VillageHall extends Building {
    /***
     * CRITICAL REQUIREMENT: Player starts with 2 population
     *
     */
    public VillageHall(){
        setHealth(200);
        setName("Village Hall");
        setLevel(1);
        setMaxLevel(10);
        setDestroyed(false);

        //Build Upgrade
        Resources upgradeCost = new Resources();
        upgradeCost.setAmount(ResourceType.IRON,25);
        setUpgradeCost(upgradeCost);
    }


}
