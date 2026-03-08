package game_elements;

public class ResourceVillager extends Inhabitant{
    /**Attributes*/
    private int productionCapacity;
    private ResourceType resourceProduced;

    /**Attributes*/
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

    public Resources produceResource(){
        Resources produced = new Resources();
        produced.setAmount(resourceProduced, productionCapacity);
        return produced;
    }

    @Override
    protected void setHealth(int health) {
        super.setHealth(health);
    }
    @Override
    protected void setDestroyed(boolean destroyed) {
        super.setDestroyed(destroyed);
    }
    @Override
    protected void setLevel(int level) {
        super.setLevel(level);
    }
    @Override
    protected void setPosY(int posY) {
        super.setPosY(posY);
    }
    @Override
    protected void setPosX(int posX) {
        super.setPosX(posX);
    }
    @Override
    protected void setAttackDamage(int attackDamage) {
        super.setAttackDamage(attackDamage);
    }
    @Override
    protected void setRange(int range) {
        super.setRange(range);
    }
    @Override
    public int getPosX() {
        return super.getPosX();
    }
    @Override
    public int getPosY() {
        return super.getPosY();
    }
    @Override
    public int getLevel() {
        return super.getLevel();
    }
    @Override
    public int getHealth() {
        return super.getHealth();
    }
    @Override
    public int getAttackDamage() {
        return super.getAttackDamage();
    }
    @Override
    public int getRange() {
        return super.getRange();
    }
}
