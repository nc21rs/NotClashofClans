package game_elements;



public class ResourceBuilding extends Building{
    /**Attributes*/
    int workerCapacity;     //number of workers required to work
    int resourceCapacity;   //how much product a farm holds before collection
    Resources production;   //type of resource and how much (replaced variable)

    /**
     * Get resource output type and quantity
     * @return the amount of resources structure produces
     */
    public Resources getProduction() {
        return production;
    }


    /**
     * Edit resource output type and quantity
     * @param production Resource data of what it outputs
     */
    protected void setProduction(Resources production) {
        this.production = production;
    }

    /**Methods*/
    /**
     * Number of workers required to keep farm functioning
     * @return number of required workers
     */
    public int getWorkerCapacity() {
        return workerCapacity;
    }

    /**
     * Maximum number of resources a single farm can hold
     * @return maximum capacity
     */
    public int getResourceCapacity() {
        return resourceCapacity;
    }

    /**
     * edit capacity of resources farm can hold
     * @param resourceCapacity maximum limit it can hold resources
     */
    protected void setResourceCapacity(int resourceCapacity) {
        this.resourceCapacity = resourceCapacity;
    }   //how much can it hold?
    protected void setWorkerCapacity(int workerCapacity) {
        this.workerCapacity = workerCapacity;
    } //how many workers are needed to operate farm?


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
    protected void setProductionCost(Resources productionCost) {
        super.setProductionCost(productionCost);
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