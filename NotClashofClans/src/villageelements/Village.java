package villageelements;

import players.Player;
import java.util.List;

class ResourceStorage{
    private int[] resources;    //player has
    private int[] capacity;     //player maximum
    public ResourceStorage(){
        resources = new int[ResourceType.values().length];
        capacity = new int[ResourceType.values().length];
    }
    public void add(ResourceType resourceType,int qty){}
    public void sub(ResourceType resourceType,int qty){}

    public int getCapacity(ResourceType resourceType) {
        return capacity[resourceType.getIndex()];
    }
    public int getResources(ResourceType resourceType) {
        return resources[resourceType.getIndex()];
    }
}

public class Village {
    private Player player;
    private ResourceStorage resources;
    private int populationSize;
    private int populationMax;
    private long guardTime;
    List<Building> buildings;
    List<Inhabitant> inhabitants;
    Army army;
    VillageHall villageHall;

    public boolean isGuarded(){
        return true;
    }
    public int getPopulation(){
        return populationSize;
    }
    public Army getArmy(){
        return army;
    }
}
