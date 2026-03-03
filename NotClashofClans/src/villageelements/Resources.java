package villageelements;

public class Resources {

    private int[] cost;
    public Resources(){
        cost = new int[ResourceType.values().length];
    }

    protected void setCost(ResourceType resourceType, int quantity) {
        cost[resourceType.getIndex()] = quantity;
    }
}
