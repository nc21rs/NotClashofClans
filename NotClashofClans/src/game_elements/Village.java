package game_elements;

import game_elements.building.Cannon;
import game_elements.building.Farm;
import game_player_database.PlayerDataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ResourceStorage {
    private int[] resources; // player has
    private int[] capacity; // player maximum

    public ResourceStorage() {
        resources = new int[ResourceType.values().length];
        capacity = new int[ResourceType.values().length];

        // initialize all resources to 0 and capacity to 100 for now
        for (int i = 0; i < resources.length; i++) {
            resources[i] = 0;
            capacity[i] = 100;
        }
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

    /**
     * Returns the amount of a specific resource type that the player has
     * 
     * @param resourceType the type of resource to check
     * 
     * @return the amount of the specified resource type
     */
    public int getResource(ResourceType resourceType) {
        return resources[resourceType.getIndex()];
    }

    private void setCapacity() {
    }

}

public class Village {
    // Map variables
    final static int MAP_SIZE = 11; // keep it small for now

    // attacking function are to cross-reference between maps.
    public Building[][] mapBuild;
    public Inhabitant[][] mapHabit; // both attacking troops and defending inhabitants share this plane

    private PlayerDataBase player;
    private ResourceStorage resourceStorage;
    private int populationSize;
    private int populationMax;
    private long guardTime;
    private List<Building> buildings;
    private List<Inhabitant> inhabitants;
    private Army army;
    private VillageHall villageHall;

    public Village() {
        // Map
        mapBuild = new Building[MAP_SIZE][MAP_SIZE];
        mapHabit = new Inhabitant[MAP_SIZE][MAP_SIZE];

        // initialize variables
        resourceStorage = new ResourceStorage();
        populationSize = 2;
        populationMax = 100;
        guardTime = 0;
        buildings = new ArrayList<>();
        inhabitants = new ArrayList<>();
        army = new Army();
        //adding village hall
        villageHall = new VillageHall();
        villageHall.setPosX(getMapSize()/2);
        villageHall.setPosY(getMapSize()/2);
        //adding cannon
        Cannon cannon = new Cannon();
        cannon.setPosX((getMapSize()/2)+1);
        cannon.setPosY((getMapSize()/2)+1);

        addBuilding(villageHall);
        addBuilding(cannon);

        /**
         * Load Player Data
         * > To store data on what buildings the player built, we simply draw from a
         * list of buildings and inhabitants.
         * > Buildings and Inhabitants -> serializable, and written as a file onto
         * user's pc
         */

    }

    // method to set the resource storage of the village
    protected void setResourceStorage(ResourceStorage resourceStorage) {
        if (resourceStorage == null) {
            return;
        }

        this.resourceStorage = resourceStorage;
    }

    public Building placeFarm(int x, int y){
        Farm farm = new Farm();
        farm.setPosX(x);
        farm.setPosY(y);

        addBuilding(farm);  //yeah. You can build over buildings. But we call that a feature!
        return farm;
    }

    public void setGuardTime(long guardTime) {
        this.guardTime = guardTime;
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

    public VillageHall getVillageHall() {
        return villageHall;
    }

    public List<Building> getBuildings() {
        return new ArrayList<>(buildings); // copy to prevent external modification
    }

    public List<Inhabitant> getInhabitants() {
        return new ArrayList<>(inhabitants); // copy to prevent external modification
    }

    // method to add a building to the village
    public void addBuilding(Building building) {
        if (building == null) {
            return;
        }

        buildings.add(building);

        // update map with new building
        int x = building.getPosX();
        int y = building.getPosY();

        if (x >= 0 && x < MAP_SIZE && y >= 0 && y < MAP_SIZE) {
            mapBuild[x][y] = building;
        }
    }

    // method to add an inhabitant to the village
    public void addInhabitant(Inhabitant inhabitant) {
        if (inhabitant == null) {
            return;
        }

        inhabitants.add(inhabitant);
        populationSize++;
    }

    // method to spend resources from the village
    public void spendResources(Resources cost) {
        if (cost == null) {
            return;
        }

        for (ResourceType type : ResourceType.values()) {
            resourceStorage.sub(type, cost.getAmount(type));
        }
    }

    public int getResources(ResourceType resourceType) {
        return resourceStorage.getResource(resourceType);
    }

    /**
     * Checks if the village has sufficient resources for a cost.
     *
     * @param cost the Resources object representing the cost
     * @return true if the village has enough resources, false otherwise
     */
    public boolean hasSufficientResources(Resources cost) {
        if (cost == null) {
            return false;
        }

        for (ResourceType type : ResourceType.values()) {
            if (resourceStorage.getResource(type) < cost.getAmount(type)) {
                return false;
            }
        }
        return true;
    }

    public int getMapSize() {
        return MAP_SIZE;
    }

    public void addResources(Resources toAdd) {
        if (toAdd == null) {
            return;
        }

        for (ResourceType type : ResourceType.values()) {
            resourceStorage.add(type, toAdd.getAmount(type));
        }
    }

    public void generateResources() {
        for (Inhabitant inhabitant : inhabitants) {
            if (inhabitant instanceof ResourceVillager) {
                ResourceVillager villager = (ResourceVillager) inhabitant;
                Resources produced = villager.produceResource();
                addResources(produced);
            }
        }
    }
}