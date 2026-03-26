package game_elements;

import java.util.Arrays;

/**
 * Class stores the quantity of all getResources with an Array.
 * The index of each resource is handled by ResourceType.
 * This class is what sets the costs for each Building/Inhabitant/Upgrade
 */
public class Resources {

    private int[] amounts;

    /**
     * Default Cost for construction is absolutely nothing, lol
     */
    public Resources() {
        amounts = new int[ResourceType.values().length];
        Arrays.fill(amounts, 0);    //when called, set the cost to zero
    }

    /**
     * Method Builder with Enums
     * @param resourceType
     * @param quantity
     */
    public void setAmount(ResourceType resourceType, int quantity) {
        // Validate that quantity is not negative
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        amounts[resourceType.getIndex()] = quantity;
    }

    /**
     * Reading
     * @param resourceType
     * @return
     */
    public int getAmount(ResourceType resourceType) {
        return amounts[resourceType.getIndex()];
    }

    /*
     * Checks if the current getResources are sufficient to cover the given cost.
     *
     * @param cost The getResources required.
     * 
     * @return true if there are enough getResources, false otherwise.
     */
    public boolean checkAmount() {

        for (ResourceType resourceType : ResourceType.values()) {

            // check if cost is bigger than the current amount of getResources
            if (this.getAmount(resourceType) < this.getAmount(resourceType)) {
                return false; // Not enough getResources
            }
        }
        return true; // enough getResources
    }
}
