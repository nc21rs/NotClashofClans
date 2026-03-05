package villageelements;

public class Resources {

    private int[] amounts;

    public Resources() {
        amounts = new int[ResourceType.values().length];
    }

    protected void setAmount(ResourceType resourceType, int quantity) {
        amounts[resourceType.getIndex()] = quantity;
    }

    public int getAmount(ResourceType resourceType) {
        return amounts[resourceType.getIndex()];
    }
}
