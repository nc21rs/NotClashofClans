package game_elements;

import java.util.ArrayList;

public class VillageModel {
    /*
    Author note: I want all to have all real-time stuff to be handled by an external thread.
        This means that Resources,Buildings,Inhabitants,Army,isGuard variables are shared getResources.
        Whatever changes the GameEngine.java makes to the village, it must always be updated to the threads.

        Threads: Will only add to any getResources. There is little risk of race conditions.
            (substract getResources, before or after collection? Doesn't matter aslong as the costs are met)
            (forseeibly, only the army will get elements removed after an attack?)
            Specially since the player can only perform one task at a time.
     */
    //CONSTRUCTOR start THREAD
    public VillageModel() {}

    //Interactable Variables
    private static final int MAP_SIZE = 25;
    private volatile ArrayList<Building> buildings; //list of all building elements
    private volatile ArrayList <Inhabitant> inhabitants; //list of all inhabitant elements
    private volatile Army army; //object containing army data
    private volatile ResourceStorage resources;   //object containing getResources data
    //Real-time variables
    private volatile boolean isGuard;   //can the base be attacked?
    private volatile ArrayList<BackgroundTask> bgTasks;
    //Real Question, how should a completed upgrade be applied to the village?
    //todo: Have BackgroundTask handle such interaction. (Apply respective changes)
    //Builders and Workers
    private int availableBuilders;
    private int availableWorkers;

    //================================== Manages Village Variables MVC Pattern =======================================//

    //Getters & Setters
    protected Army getArmy(){return army;}
    protected boolean getGuard(){return isGuard;}
    protected int getMapSize(){return MAP_SIZE;}
    protected ResourceStorage getResources(){return resources;}
    protected ArrayList<Building> getBuildings(){return buildings;}
    protected ArrayList<BackgroundTask> getBgTasks(){return bgTasks;}
    protected ArrayList<Inhabitant> getInhabitants(){return inhabitants;}
    protected int getAvailableBuilders(){return availableBuilders;}
    protected int getAvailableWorkers(){return availableWorkers;}

    protected void setArmy(Army army){this.army = army;}
    protected void setIsGuard(boolean setGuard){this.isGuard =setGuard;}
    protected void setResources (ResourceStorage resources){this.resources = resources;}
    protected void setBgTasks(ArrayList<BackgroundTask> bgTasks){this.bgTasks = bgTasks;}
    protected void setAvailableWorkers(int availableWorkers) {this.availableWorkers = availableWorkers;}
    protected void setAvailableBuilders(int availableBuilders) {this.availableBuilders = availableBuilders;}


    protected void addBuilding(Building building){buildings.add(building);}
    protected void removeBuilding(Building building){buildings.remove(building);}
    protected void addInhabitant(Inhabitant inhabitant){inhabitants.add(inhabitant);}
    protected void removeInhabitant(Inhabitant inhabitant){inhabitants.remove(inhabitant);}
    //Background Tasks are handled in real-time requiring different processes
//    protected void addBackgroundTask(BackgroundTask backgroundTask){bgTasks.add(backgroundTask);}
//    protected void removeBackgroundTask(BackgroundTask backgroundTask){bgTasks.remove(backgroundTask);}
}
