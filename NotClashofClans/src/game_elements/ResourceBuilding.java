package game_elements;

/**
 * This class represents a resource building in the game. It extends the
 * Building class.
 */
public class ResourceBuilding extends Building{
    /**Attributes*/
    int maxWorkers;         //maximum number of workers that can work here
    int numWorkers;         //current number of workers here
    int resourceCapacity;   //how much product a farm holds before collection
    Resources production;   //type of resource and how much (replaced variable)

    public int getNumWorkers() {return numWorkers;}
    public void setNumWorkers(int numWorkers) {this.numWorkers = numWorkers;}
    public int getMaxWorkers() {return maxWorkers;}
    public void setMaxWorkers(int maxWorkers) {this.maxWorkers = maxWorkers;}

    /**
     * Get resource output type and quantity
     * @return the amount of getResources structure produces
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



    /**
     * Maximum number of getResources a single farm can hold
     * @return maximum capacity
     */
    public int getResourceCapacity() {
        return resourceCapacity;
    }

    /**
     * edit capacity of getResources farm can hold
     * @param resourceCapacity maximum limit it can hold getResources
     */
    protected void setResourceCapacity(int resourceCapacity) {
        this.resourceCapacity = resourceCapacity;
    }   //how much can it hold?

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