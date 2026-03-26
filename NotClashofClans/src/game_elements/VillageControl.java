package game_elements;

import java.util.ArrayList;

public class VillageControl {
    VillageModel village;
    VillageView view;
    public Building[][] buildingMap;

    //Java Version Dependency: Java19+ ExecutorService is autocloseable, can be used with try(with resources).
    VillageControl(VillageModel village, VillageView view) {
        this.village = village;
        this.view = view;
        buildingMap = new Building[this.village.getMapSize()][this.village.getMapSize()];
        setAvailableBuilders(4);    //Player gets only 4 maximum builders


    }

    //Getters & Setters
    protected Army getArmy(){return village.getArmy();}
    protected boolean getGuard(){return village.getGuard();}
    protected int getMapSize(){return village.getMapSize();}
    protected ResourceStorage getResources(){return village.getResources();}
    protected ArrayList<Building> getBuildings(){return village.getBuildings();}
    protected ArrayList<BackgroundTask> getBgTasks(){return village.getBgTasks();}
    protected ArrayList<Inhabitant> getInhabitants(){return village.getInhabitants();}
    protected int getAvailableWorkers(){return village.getAvailableWorkers();}
    protected int getAvailableBuilders(){return village.getAvailableBuilders();}

    protected void setArmy(Army army){village.setArmy(army);}
    protected void setIsGuard(boolean setGaurd){village.setIsGuard(setGaurd);}
    protected void setResources (ResourceStorage resources){village.setResources(resources);}
    protected void setBgTasks(ArrayList<BackgroundTask> bgTasks){village.setBgTasks(bgTasks);}
    protected void setAvailableWorkers(int availableWorkers){village.setAvailableWorkers(availableWorkers);}
    protected void setAvailableBuilders(int availableBuilders){village.setAvailableBuilders(availableBuilders);}

    protected void addBuilding(Building building){village.getBuildings().add(building);}
    protected void removeBuilding(Building building){village.getBuildings().remove(building);}
    protected void addInhabitant(Inhabitant inhabitant){village.getInhabitants().add(inhabitant);}
    protected void removeInhabitant(Inhabitant inhabitant){village.getInhabitants().remove(inhabitant);}


    //============================ Helper Methods ====================================================================//

    //Requests (Game Engine will make queries to Village to do stuff)

    /**
     * Function attempts to build provided structure at location
     * @param building structure in question to build
     * @param x coordinate
     * @param y coordinate
     * @throws NoBuilderAvaliable error should no builders be available
     * @throws NotEnoughResources error should a village lack the resources
     * @throws BuildingInTheWay error should a building is already in the way
     */
    public void makeBuilding(Building building, int x, int y) throws NoBuilderAvaliable, NotEnoughResources, BuildingInTheWay {
        if (village.getAvailableBuilders()<=0) {  //check if there is any available builders
            throw new NoBuilderAvaliable();
        } else if(!building.getProductionCost().checkAmount()){ //check if village has the resources to build
            throw new NotEnoughResources();
        } else if(buildingMap[x][y] != null){   //check if there is already a structure built at given location
            throw new BuildingInTheWay(buildingMap[x][y].getName());    //omg im soo smart!
        } else {
            //subtract resources
            Resources cost = building.getProductionCost();
            village.getResources().subtract(ResourceType.FOOD, cost.getAmount(ResourceType.FOOD));
            village.getResources().subtract(ResourceType.WOOD, cost.getAmount(ResourceType.WOOD));
            village.getResources().subtract(ResourceType.IRON, cost.getAmount(ResourceType.IRON));
            village.getResources().subtract(ResourceType.GOLD,cost.getAmount(ResourceType.GOLD));
            //add building
            village.addBuilding(building);
            building.setDestroyed(true);    //when constructed, set destroyed to false.
            //queue task
            village.getBgTasks().add(new BackgroundTask(building,10));
        }
    }

    /**
     * Function attempts upgrades selected location
     * @param x coordinate
     * @param y coordinate
     * @throws NoBuilderAvaliable error should no builders be available
     * @throws NothingToUpgrade error should selected tile is empty
     * @throws MaxLevel error should the building already maxed out
     */
    public void upgradeBuilding(int x, int y) throws NoBuilderAvaliable, NothingToUpgrade, MaxLevel {
        if(village.getAvailableBuilders()<=0) {
            throw new NoBuilderAvaliable();
        } else if (buildingMap[x][y] == null){
            throw new NothingToUpgrade();
        } else { //build exists
            Building building = buildingMap[x][y];
            if(building.getLevel() >= building.getMaxLevel()){ //check if max level
                throw new MaxLevel(building.getName());
            } else { //otherwise upgrade!
                Resources cost = building.getUpgradeCost();
                village.getResources().subtract(ResourceType.FOOD, cost.getAmount(ResourceType.FOOD));
                village.getResources().subtract(ResourceType.WOOD, cost.getAmount(ResourceType.WOOD));
                village.getResources().subtract(ResourceType.IRON, cost.getAmount(ResourceType.IRON));
                village.getResources().subtract(ResourceType.GOLD,cost.getAmount(ResourceType.GOLD));
                //set to upgrading
                building.setDestroyed(true);
                //queue task
                village.getBgTasks().add(new BackgroundTask(building,10));
            }
        }
    }


    public void upgradeInhabitants(ArmyUnit armyUnit) throws NotEnoughResources, MaxLevel {
        if(!armyUnit.getProductionCost().checkAmount()){
            throw new NotEnoughResources();
        } else if(armyUnit.getLevel() >= armyUnit.getMaxLevel()){
            throw new MaxLevel(armyUnit.getName());
        } else {
            //subtract
            Resources cost = armyUnit.getProductionCost();
            village.getResources().subtract(ResourceType.FOOD, cost.getAmount(ResourceType.FOOD));
            village.getResources().subtract(ResourceType.WOOD, cost.getAmount(ResourceType.WOOD));
            village.getResources().subtract(ResourceType.IRON, cost.getAmount(ResourceType.IRON));
            village.getResources().subtract(ResourceType.GOLD,cost.getAmount(ResourceType.GOLD));
            //queue task
            village.getBgTasks().add(new BackgroundTask<>(armyUnit,10));
        }
    }
    public void trainArmy(ArmyUnit armyUnit) throws NotEnoughResources {
        if (!armyUnit.getProductionCost().checkAmount()) {
            throw new NotEnoughResources();
        } else {
            //subtract cost
            Resources cost = armyUnit.getProductionCost();
            village.getResources().subtract(ResourceType.FOOD, cost.getAmount(ResourceType.FOOD));
            village.getResources().subtract(ResourceType.WOOD, cost.getAmount(ResourceType.WOOD));
            village.getResources().subtract(ResourceType.IRON, cost.getAmount(ResourceType.IRON));
            village.getResources().subtract(ResourceType.GOLD,cost.getAmount(ResourceType.GOLD));
            //add unit to army
            village.getArmy().addUnit(armyUnit);
        }
    }
    //todo implement
    public void trainWorkers(){}


}



//================================ Custom Exceptions =================================================================//
class NotEnoughResources extends Exception {
    public NotEnoughResources() {super("Error: Missing Resources");}
}
class NoBuilderAvaliable extends Exception {
    public NoBuilderAvaliable() {super("Error: No Builder Available");}
}
class BuildingInTheWay extends Exception {
    public BuildingInTheWay(String buildingName) {super("Error: Cannot build here, "+buildingName+ " is in the way");}
}
class NothingToUpgrade extends Exception {
    public NothingToUpgrade() {super("Error: There is nothing to upgrade");}
}
class MaxLevel extends Exception {
    public MaxLevel(String target) {super("Error: "+target+" at Max Level");}
}
//================================== Threading =======================================================================//
//class TaskThread extends Thread {
//    @Override
//    public void run() {
//        while (true) {
//
//        }
//    }
//}