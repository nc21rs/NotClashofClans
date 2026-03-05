package villageelements;

import players.Player;
import java.util.List;

class ResourceStorage {
    private int[] resources; // player has
    private int[] capacity; // player maximum

    public ResourceStorage() {
        resources = new int[ResourceType.values().length];
        capacity = new int[ResourceType.values().length];
    }

    // method to add a specific quantity of a resource type to the player's storage
    public void add(ResourceType resourceType, int qty) {
        int index = resourceType.getIndex();
        resources[index] += qty;

        // Ensure that the resource amount does not exceed the capacity
        if (resources[index] > capacity[index]) {
            resources[index] = capacity[index];
        }
    }

    // method to subtract a specific quantity of a resource type from the player's
    // storage
    public void sub(ResourceType resourceType, int qty) {
        int index = resourceType.getIndex();
        resources[index] -= qty;

        // Ensure that the resource amount does not go below zero
        if (resources[index] < 0) {
            resources[index] = 0;
        }
    }

    public int getCapacity(ResourceType resourceType) {
        return capacity[resourceType.getIndex()];
    }

    /*
     * Returns the amount of a specific resource type that the player has
     * 
     * @param resourceType the type of resource to check
     * 
     * @return the amount of the specified resource type
     */
    public int getResource(ResourceType resourceType) {
        return resources[resourceType.getIndex()];
    }
}

public class Village {
    private Player player;
    private ResourceStorage resourceStorage;
    private int populationSize;
    private int populationMax;
    private long guardTime;
    List<Building> buildings;
    List<Inhabitant> inhabitants;
    Army army;
    VillageHall villageHall;

    // method to set the resource storage of the village
    protected void setResourceStorage(ResourceStorage resourceStorage) {
        this.resourceStorage = resourceStorage;
    }

    public long getGuardTime() {
        return guardTime;
    }

    public int getPopulation() {
        return populationSize;
    }

    public int getPopulationMax() {
        return populationMax;
    }

    public Army getArmy() {
        return army;
    }

    // method to get the amount of a specific resource type that the player has
    public int getResourceAmount(ResourceType type) {
        return resourceStorage.getResource(type);
    }

    public int getHallLevel() {
        return villageHall.getLevel();
    }

    public List<Building> getBuildings() {
        return buildings;
    }
}
