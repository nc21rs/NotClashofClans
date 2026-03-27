package game_elements;

import game_elements.building.TownHall;
import game_elements.exceptions.*;
import game_elements.inhabitant.villager.Builder;
import game_elements.inhabitant.villager.Collector;

import java.util.ArrayList;

public class VillageControl {
    VillageModel village;
    VillageView view;
    public Building[][] buildingMap;

    //Java Version Dependency: Java19+ ExecutorService is autocloseable, can be used with try(with resources).
     public VillageControl(VillageModel village, VillageView view) {
         this.village = village;
         this.view = view;
         buildingMap = new Building[this.village.getMapSize()][this.village.getMapSize()];
         setAvailableBuilders(2);    //Player by default start with 2 builders
         setBgTasks(new ArrayList<>());
         setIsGuard(true);
         setArmy(new Army());
         setMaxWorker(2);
         setMaxBuilder(3);
         setMaxBuilding(5);
         setResources(new ResourceStorage());
         setAvailableWorkers(2);
         setBuildings(new ArrayList<>());
         setInhabitants(new ArrayList<>());
         BuildingFactory bf = new BuildingFactory();
         Building townhall = bf.createBuilding("TOWNHALL",this.village.getMapSize()/2,this.village.getMapSize()/2);
         addBuilding(townhall);
         buildingMap[townhall.getPosX()][townhall.getPosY()] = townhall;
    }

    //Getters & Setters
    public Army getArmy(){return village.getArmy();}
    public boolean getGuard(){return village.getGuard();}
    public int getMapSize(){return village.getMapSize();}
    public ResourceStorage getResources(){return village.getResources();}
    public ArrayList<Building> getBuildings(){return village.getBuildings();}
    public ArrayList<BackgroundTask> getBgTasks(){return village.getBgTasks();}
    public ArrayList<Inhabitant> getInhabitants(){return village.getInhabitants();}
    public int getAvailableWorkers(){return village.getNumWorkers();}
    public int getAvailableBuilders(){return village.getNumBuilders();}
    public int getMaxWorker(){return village.getMaxWorker();}
    public int getMaxBuilder(){return village.getMaxBuilder();}
    public int getMaxBuilding(){return village.getMaxBuilding();}
    public int getNumBuilding(){return village.getNumBuilding();}

    protected void setArmy(Army army){village.setArmy(army);}
    protected void setIsGuard(boolean setGaurd){village.setIsGuard(setGaurd);}
    protected void setMaxWorker(int maxWorker){village.setMaxWorker(maxWorker);}
    protected void setMaxBuilder(int maxBuilder){village.setMaxBuilder(maxBuilder);}
    protected void setNumBuilding(int numBuilding){village.setNumBuilding(numBuilding);}
    protected void setMaxBuilding(int maxBuilding){village.setMaxBuilding(maxBuilding);}
    protected void setResources (ResourceStorage resources){village.setResources(resources);}
    protected void setBgTasks(ArrayList<BackgroundTask> bgTasks){village.setBgTasks(bgTasks);}
    protected void setAvailableWorkers(int availableWorkers){village.setNumWorkers(availableWorkers);}
    protected void setAvailableBuilders(int availableBuilders){village.setNumBuilders(availableBuilders);}
    protected void setBuildings (ArrayList<Building> buildings){village.setBuildings(buildings);}
    protected void setInhabitants(ArrayList<Inhabitant> inhabitants){village.setInhabitants(inhabitants);}

    protected void addBuilding(Building building){village.getBuildings().add(building);}
    protected void removeBuilding(Building building){village.getBuildings().remove(building);}
    protected void addInhabitant(Inhabitant inhabitant){village.getInhabitants().add(inhabitant);}
    protected void removeInhabitant(Inhabitant inhabitant){village.getInhabitants().remove(inhabitant);}

    //============================ Helper Methods ====================================================================//
    //Queries that the game engine can request Village to perform.
    /**
     * Function attempts to build provided structure at location
     * @param building structure in question to build
     * @throws NoBuilderAvaliable error should no builders be available
     * @throws NotEnoughResources error should a village lack the resources
     * @throws BuildingInTheWay error should a building is already in the way
     */
    public void placeBuilding(Building building) throws NoBuilderAvaliable, NotEnoughResources, BuildingInTheWay, MaxBuilding {
        if (this.getAvailableBuilders()<=0) {  //check if there is any available builders
            throw new NoBuilderAvaliable();
        } else if(!building.getProductionCost().checkAmount()){ //check if village has the resources to build
            throw new NotEnoughResources();
        } else if(buildingMap[building.getPosX()][building.getPosY()] != null){   //check if there is already a structure built at given location
            throw new BuildingInTheWay(buildingMap[building.getPosX()][building.getPosY()].getName());    //omg im soo smart!
        } else if (this.getNumBuilding()>=this.getMaxBuilding()) {    //at building capacity?
            throw new MaxBuilding();
        } else {
            //subtract resources
            Resources cost = building.getProductionCost();
            subtractCost(cost);

            this.addBuilding(building); //Add building to list
            buildingMap[building.getPosX()][building.getPosY()] = building;
            building.setDestroyed(true);    //when constructed, set destroyed to false.
            this.setNumBuilding(this.getNumBuilding()+1); //increment number of building
            //queue task
            this.getBgTasks().add(new BackgroundTask(building,10,false));
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
        if(this.getAvailableBuilders()<=0) {   //builders available?
            throw new NoBuilderAvaliable();
        } else if (buildingMap[x][y] == null){  //selected tile is empty?
            throw new NothingToUpgrade();
        } else { //otherwise building exists
            Building building = buildingMap[x][y];
            if(building.getLevel() >= building.getMaxLevel()){ //check if max level
                throw new MaxLevel(building.getName());
            } else { //otherwise upgrade!
                Resources cost = building.getUpgradeCost();
                subtractCost(cost);
                //set to upgrading
                building.setDestroyed(true);
                //queue task
                this.getBgTasks().add(new BackgroundTask(building,10,true));
            }
        }
    }

    /**
     * Function attempts to upgrade selected unit
     * @param armyUnit the village's armyUnit type
     * @throws NotEnoughResources error should there not be enough resources
     * @throws MaxLevel error should maxed level
     */
    public void upgradeArmyUnit(ArmyUnit armyUnit) throws NotEnoughResources, MaxLevel {
        if(!armyUnit.getProductionCost().checkAmount()){
            throw new NotEnoughResources();
        } else if(armyUnit.getLevel() >= armyUnit.getMaxLevel()){
            throw new MaxLevel(armyUnit.getName());
        } else {
            //subtract
            Resources cost = armyUnit.getProductionCost();
            subtractCost(cost);
            //queue task
            this.getBgTasks().add(new BackgroundTask<>(armyUnit,10,true));
        }
    }

    /**
     * Function attempts to train new army units
     * @param armyUnit army unit type
     * @throws NotEnoughResources error should there not be enough resources
     */
    public void trainArmy(ArmyUnit armyUnit) throws NotEnoughResources {
        if (!armyUnit.getProductionCost().checkAmount()) {
            throw new NotEnoughResources();
        } else {
            //subtract cost
            Resources cost = armyUnit.getProductionCost();
            subtractCost(cost);
            //queue task
            this.getBgTasks().add(new BackgroundTask(armyUnit,10,false));
        }
    }

    /**
     * Function attempts to train new builders
     * @param builder the reference to the inhabitant
     * @throws NotEnoughResources error should there not be enough resources
     * @throws MaxLevel
     */
    public void trainBuilder(Builder builder) throws NotEnoughResources, MaxBuilder {
        if (!builder.getProductionCost().checkAmount()) {
            throw new NotEnoughResources();
        } else if (this.getAvailableBuilders() >= this.getMaxBuilder()) {
            throw new MaxBuilder();
        } else {
            //subtract costs
            Resources cost = builder.getProductionCost();
            subtractCost(cost);
            //queue task
            this.getBgTasks().add(new BackgroundTask(builder,10,true));
        }
    }

    /**
     * Function attempts to train new Villager Collectors
     * @param collector
     * @throws NotEnoughResources error should there not be enough resources
     * @throws MaxWorker error should limit is reached
     */
    public void trainWorker(Collector collector) throws NotEnoughResources, MaxLevel, MaxWorker {
        if(!collector.getProductionCost().checkAmount()) {
            throw new NotEnoughResources();
        } else{
            Resources cost = collector.getProductionCost();
            subtractCost(cost);
            this.getBgTasks().add(new BackgroundTask(collector,10,true));
        }
    }



    private void subtractCost(Resources cost) {
        this.getResources().subtract(ResourceType.FOOD, cost.getAmount(ResourceType.FOOD));
        this.getResources().subtract(ResourceType.WOOD, cost.getAmount(ResourceType.WOOD));
        this.getResources().subtract(ResourceType.IRON, cost.getAmount(ResourceType.IRON));
        this.getResources().subtract(ResourceType.GOLD,cost.getAmount(ResourceType.GOLD));
    }

    public void updateTasks(){ //todo turn into thread (using ExecutorService, autocloseable [requires Java19+])
        for (BackgroundTask backgroundTask : this.getBgTasks()) {
            backgroundTask.performTask(this.village);
        }
    }
    //----------------------------------------------------------------------------------------------------------------//
    public Building getBuilding(int x, int y) {
        return buildingMap[x][y];
    }

    public void addResources(Resources resources) {
        this.getResources().add(ResourceType.FOOD, resources.getAmount(ResourceType.FOOD));
        this.getResources().add(ResourceType.WOOD, resources.getAmount(ResourceType.WOOD));
        this.getResources().add(ResourceType.IRON, resources.getAmount(ResourceType.IRON));
        this.getResources().add(ResourceType.GOLD, resources.getAmount(ResourceType.GOLD));
    }
    public void subtractResources(Resources resources) {
        this.getResources().subtract(ResourceType.FOOD, resources.getAmount(ResourceType.FOOD));
        this.getResources().subtract(ResourceType.WOOD, resources.getAmount(ResourceType.WOOD));
        this.getResources().subtract(ResourceType.IRON, resources.getAmount(ResourceType.IRON));
        this.getResources().subtract(ResourceType.GOLD, resources.getAmount(ResourceType.GOLD));
    }
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