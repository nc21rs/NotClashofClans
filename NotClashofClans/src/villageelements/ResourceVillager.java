package villageelements;

public class ResourceVillager extends Inhabitant{
    /**Attributes*/
    private int productionCapacity;
    /**Attributes*/
    protected void setProductionCapacity(int productionCapacity) {
        this.productionCapacity = productionCapacity;
    }
    public int getProductionCapacity() {
        return productionCapacity;
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
