package game_elements;

/**
 * This class represents a villager that produces resources for the player's
 * village. Extends the Inhabitant class.
 */
public class ResourceVillager extends Inhabitant {

    private int productionCapacity;
    private ResourceType resourceProduced;

    public ResourceVillager() {
        // Default values for a resource villager
        super();
        this.productionCapacity = 10;
        this.resourceProduced = ResourceType.FOOD;
    }

    protected void setProductionCapacity(int productionCapacity) {
        this.productionCapacity = productionCapacity;
    }

    protected void setResourceProduced(ResourceType resourceProduced) {
        this.resourceProduced = resourceProduced;
    }

    public int getProductionCapacity() {
        return productionCapacity;
    }

    public ResourceType getResourceProduced() {
        return resourceProduced;
    }

    /**
     * Produces resources based on the villager's production capacity and
     * the type of resource it produces.
     * 
     * @return the resources produced by the villager
     */
    public Resources produceResource() {
        Resources produced = new Resources();
        produced.setAmount(resourceProduced, productionCapacity);
        return produced;
    }
}
