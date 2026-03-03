package villageelements;



public class ResourceBuilding extends Building{
    /**Attributes*/
    ResourceType resourceType;
    int workerCapacity;
    int resourceCapacity;

    /**Methods*/
    public ResourceType getResourceType() {
        return resourceType;
    }
    public int getWorkerCapacity() {
        return workerCapacity;
    }
    public int getResourceCapacity() {
        return resourceCapacity;
    }

    protected void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
    protected void setResourceCapacity(int resourceCapacity) {
        this.resourceCapacity = resourceCapacity;
    }
    protected void setWorkerCapacity(int workerCapacity) {
        this.workerCapacity = workerCapacity;
    }


    @Override
    public int getHealth() {
        return super.getHealth();
    }
    @Override
    public int getLevel() {
        return super.getLevel();
    }
    @Override
    public int getPosY() {
        return super.getPosY();
    }
    @Override
    public int getPosX() {
        return super.getPosX();
    }

    @Override
    protected void setCost(Resources productionCost) {
        super.setCost(productionCost);
    }
    @Override
    protected void setDestroyed(boolean destroyed) {
        super.setDestroyed(destroyed);
    }
    @Override
    protected void setHealth(int health) {
        super.setHealth(health);
    }
    @Override
    protected void setLevel(int level) {
        super.setLevel(level);
    }
    @Override
    protected void setPosX(int posX) {
        super.setPosX(posX);
    }
    @Override
    protected void setPosY(int posY) {
        super.setPosY(posY);
    }

}