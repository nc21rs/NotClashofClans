package game_elements;

/**
 * This class represent a village's resource storage.
 * It performs operations on the getResources.
 */
public class ResourceStorage {
    private int[] resources;
    private int[] capacity;

    public ResourceStorage() {
        resources = new int[ResourceType.values().length];
        capacity = new int[ResourceType.values().length];

        for (int i = 0; i < resources.length; i++) {
            resources[i] = 0;   //initialize all getResources to be zero
        }
        //manually assigning resource capacity.
        capacity[ResourceType.FOOD.getIndex()] = 800;
        capacity[ResourceType.WOOD.getIndex()] = 400;
        capacity[ResourceType.IRON.getIndex()] = 200;
        capacity[ResourceType.GOLD.getIndex()] = 100;
    }

    /**
     * Adds a specific quantity of a resource type to the player's storage.
     *
     * @param resourceType the type of resource to add
     * @param gain          the quantity of the resource to add
     */
    public void add(ResourceType resourceType, int gain) {
        int index = resourceType.getIndex();
        resources[index] += gain;

        if (resources[index] > capacity[index]) {
            resources[index] = capacity[index];
        }
    }

    /**
     * Subtracts a specific quantity of a resource type from the player's storage.
     *
     * @param resourceType the type of resource to subtract
     * @param cost          the quantity of the resource to subtract
     */
    public void subtract(ResourceType resourceType, int cost) {
        int index = resourceType.getIndex();
        resources[index] -= cost;
        //Let it go into the negatives. For debug purposes.
//        if (resources[index] < 0) {
//            resources[index] = 0;
//        }
    }

    /**
     * Returns the capacity of a specific resource type in the player's storage.
     *
     * @param resourceType the type of resource to check
     * @return the capacity of the specified resource type
     */
    public int getCapacity(ResourceType resourceType) {
        return capacity[resourceType.getIndex()];
    }

    /**
     * Returns the amount of a specific resource type that the player has.
     *
     * @param resourceType the type of resource to check
     * @return the amount of the specified resource type
     */
    public int getResource(ResourceType resourceType) {
        return resources[resourceType.getIndex()];
    }
}
