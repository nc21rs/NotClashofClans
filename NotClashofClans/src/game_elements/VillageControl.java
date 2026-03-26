package game_elements;

import java.util.ArrayList;

public class VillageControl {
    VillageModel village;
    VillageView view;
    public Building[][] buildingMap;

    VillageControl(VillageModel village, VillageView view) {
        this.village = village;
        this.view = view;
        buildingMap = new Building[this.village.getMapSize()][this.village.getMapSize()];


    }

    //Getters & Setters
    protected Army getArmy(){return village.getArmy();}
    protected boolean getGuard(){return village.getGuard();}
    protected int getMapSize(){return village.getMapSize();}
    protected ResourceStorage getResources(){return village.getResources();}
    protected ArrayList<Building> getBuildings(){return village.getBuildings();}
    protected ArrayList<BackgroundTask> getBgTasks(){return village.getBgTasks();}
    protected ArrayList<Inhabitant> getInhabitants(){return village.getInhabitants();}

    protected void setArmy(Army army){village.setArmy(army);}
    protected void setIsGuard(boolean setGaurd){village.setIsGuard(setGaurd);}
    protected void setResources (ResourceStorage resources){village.setResources(resources);}
    protected void setBgTasks(ArrayList<BackgroundTask> bgTasks){village.setBgTasks(bgTasks);}

    protected void addBuilding(Building building){village.getBuildings().add(building);}
    protected void removeBuilding(Building building){village.getBuildings().remove(building);}
    protected void addInhabitant(Inhabitant inhabitant){village.getInhabitants().add(inhabitant);}
    protected void removeInhabitant(Inhabitant inhabitant){village.getInhabitants().remove(inhabitant);}
}
