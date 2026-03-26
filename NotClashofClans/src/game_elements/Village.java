package game_elements;
/**
 * Depreciated Class
 */

import game_elements.building.*;
import game_elements.inhabitant.villager.*;
import game_player_database.PlayerDataBase;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the player's village in the game. It contains
 * information about all elements of the village (buildings, inhabitants,
 * getResources, etc.) and methods to manipulate them.
 */
public class Village {
    private static final int MAP_SIZE = 11;

    private Building[][] mapBuild;
    private Inhabitant[][] mapHabit;

    private PlayerDataBase player;
    private ResourceStorage resourceStorage;
    private int populationSize;
    private int populationMax;
    private long guardTime;
    private List<Building> buildings;
    private List<Inhabitant> inhabitants;
    private Army army;
    private TownHall townHall;

    public Village() {
        mapBuild = new Building[MAP_SIZE][MAP_SIZE];
        mapHabit = new Inhabitant[MAP_SIZE][MAP_SIZE];

        resourceStorage = new ResourceStorage();
        populationSize = 0;
        populationMax = 100;
        guardTime = 0;
        buildings = new ArrayList<>();
        inhabitants = new ArrayList<>();
        army = new Army();

        townHall = new TownHall();
        townHall.setPosition(5, 5);

        Cannon cannon = new Cannon();
        cannon.setPosition(6, 6);

        Building building1 = new Farm();
        Building building2 = new LumberMill();
        building1.setPosition(4, 5);
        building2.setPosition(6, 5);

        addBuilding(townHall);
        addBuilding(cannon);
        addBuilding(building1);
        addBuilding(building2);

        ResourceVillager villager1 = new Farmer();
        ResourceVillager villager2 = new Collector();
        villager1.setPosition(4, 4);
        villager2.setPosition(6, 4);

        addInhabitant(villager1);
        addInhabitant(villager2);

        // TODO: load player data if exists, otherwise start with default village
    }

    //simple build function
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

    public int getResourceAmount(ResourceType type) {
        return resourceStorage.getResource(type);
    }

    public int getHallLevel() {
        return townHall.getLevel();
    }

    public TownHall getVillageHall() {
        return townHall;
    }

    public List<Building> getBuildings() {
        return new ArrayList<>(buildings);
    }

    public List<Inhabitant> getInhabitants() {
        return new ArrayList<>(inhabitants);
    }

    /**
     * Adds a building (if valid) to the village and updates the map accordingly.
     *
     * @param building the building to add
     */
    public void addBuilding(Building building) {
        if (building == null) {
            return;
        }

        int x = building.getPosX();
        int y = building.getPosY();

        if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE) {
            return;
        }

        if (mapBuild[x][y] != null) {
            return;
        }

        buildings.add(building);
        mapBuild[x][y] = building;
    }

    /**
     * Adds an inhabitant (if valid) to the village.
     *
     * @param inhabitant the inhabitant to add
     */
    public void addInhabitant(Inhabitant inhabitant) {
        if (inhabitant == null) {
            return;
        }

        if (populationSize >= populationMax) {
            return;
        }

        int x = inhabitant.getPosX();
        int y = inhabitant.getPosY();

        if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE) {
            return;
        }

        if (mapHabit[x][y] != null) {
            return;
        }

        inhabitants.add(inhabitant);
        mapHabit[x][y] = inhabitant;
        populationSize++;
    }

    /**
     * Returns village's getResources.
     *
     * @return the getResources currently stored in the village
     */
    public Resources getResources() {
        Resources resources = new Resources();
        for (ResourceType type : ResourceType.values()) {
            resources.setAmount(type, resourceStorage.getResource(type));
        }
        return resources;
    }

    /**
     * Spends getResources from the village.
     *
     * @param cost the cost to be deducted
     */
    public void spendResources(Resources cost) {
        if (cost == null) {
            return;
        }

        for (ResourceType type : ResourceType.values()) {
            resourceStorage.subtract(type, cost.getAmount(type));
        }
    }

    /**
     * Checks if the village has sufficient getResources for a cost.
     *
     * @param cost the Resources object representing the cost
     * @return true if the village has enough getResources, false otherwise
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

    /**
     * Returns the building at the specified coordinates, or null if there is no
     * building at that location.
     *
     * @param x the x-coordinate of the building
     * @param y the y-coordinate of the building
     * @return the building at the specified coordinates, or null if there is no building
     */
    public Building getBuildingAt(int x, int y) {
        if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE) {
            return null;
        }

        return mapBuild[x][y];
    }

    /**
     * Returns the inhabitant at the specified coordinates, or null if there is no
     * inhabitant at that location.
     *
     * @param x the x-coordinate of the inhabitant
     * @param y the y-coordinate of the inhabitant
     * @return the inhabitant at the specified coordinates, or null if there is no inhabitant
     */
    public Inhabitant getInhabitantAt(int x, int y) {
        if (x < 0 || x >= MAP_SIZE || y < 0 || y >= MAP_SIZE) {
            return null;
        }

        return mapHabit[x][y];
    }

    /**
     * Adds getResources to the village's storage.
     *
     * @param toAdd the Resources object representing the getResources to add
     */
    public void addResources(Resources toAdd) {
        if (toAdd == null) {
            return;
        }

        for (ResourceType type : ResourceType.values()) {
            resourceStorage.add(type, toAdd.getAmount(type));
        }
    }

    /**
     * Generates getResources for the village by iterating through all inhabitants and
     * getting their resource production if they are resource villagers.
     */
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