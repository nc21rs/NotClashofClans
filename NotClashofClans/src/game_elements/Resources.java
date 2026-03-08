package game_elements;

/**
 * Class stores the quantity of all resources with an Array. The index of each resource is handled by ResourceType
 * This class is what sets the costs for each Building/Inhabitant/Upgrade
 */
public class Resources {

    private int[] amounts;

    public Resources() {
        amounts = new int[ResourceType.values().length];
    }

    public void setAmount(ResourceType resourceType, int quantity) {
        amounts[resourceType.getIndex()] = quantity;
    }

    public int getAmount(ResourceType resourceType) {
        return amounts[resourceType.getIndex()];
    }
}
