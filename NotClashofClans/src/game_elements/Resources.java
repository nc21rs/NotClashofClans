package game_elements;

/**
 * Class stores the quantity of all getResources with an Array.
 * The index of each resource is handled by ResourceType.
 * This class is what sets the costs for each Building/Inhabitant/Upgrade
 */
public class Resources {

    private int[] amounts;

    public Resources() {
        amounts = new int[ResourceType.values().length];
    }

    public void setAmount(ResourceType resourceType, int quantity) {
        // Validate that quantity is not negative
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        amounts[resourceType.getIndex()] = quantity;
    }

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
    public boolean checkAmount(Resources cost) {
        if (cost == null) {
            throw new IllegalArgumentException("Cost cannot be null.");
        }

        for (ResourceType resourceType : ResourceType.values()) {

            // check if cost is bigger than the current amount of getResources
            if (this.getAmount(resourceType) < cost.getAmount(resourceType)) {
                return false; // Not enough getResources
            }
        }
        return true; // enough getResources
    }

    /*
     * Method to remove getResources from current getResources
     *
     * @param toRemove getResources to be removed
     */
    public void removeResources(Resources toRemove) {
        if (toRemove == null) {
            throw new IllegalArgumentException("Resources to be removed cannot be null.");
        }

        for (ResourceType resourceType : ResourceType.values()) {
            int index = resourceType.getIndex();
            this.amounts[index] = this.amounts[index] - toRemove.getAmount(resourceType);

            // even though we check if we can afford the cost, we will make sure no negative
            // getResources are stored.
            // Just in case
            if (this.amounts[index] < 0) {
                this.amounts[index] = 0;
            }
        }
    }

    /*
     * Method to add getResources to current getResources
     *
     * @param toAdd getResources to be added
     */
    public void addResources(Resources toAdd) {
        if (toAdd == null) {
            throw new IllegalArgumentException("Resource to be added cannot be null.");
        }

        for (ResourceType resourceType : ResourceType.values()) {
            int index = resourceType.getIndex();
            this.amounts[index] = this.amounts[index] + toAdd.getAmount(resourceType);
        }
    }
}
