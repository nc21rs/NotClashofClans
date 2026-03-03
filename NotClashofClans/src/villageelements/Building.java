package villageelements;

public class Building implements Damageable, Upgradeable{

    /**Attributes*/
    private int health;
    private int posX;
    private int posY;
    private int level;
    private boolean destroyed;
    private Resources productionCost;

    /**Methods*/
    //SETTERS
    protected void setPosX(int posX) {
        this.posX = posX;
    }
    protected void setPosY(int posY) {
        this.posY = posY;
    }
    protected void setCost(Resources productionCost) {
        this.productionCost = productionCost;
    }
    protected void setLevel(int level) {
        this.level = level;
    }
    protected void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
    protected void setHealth(int health) {
        this.health = health;
    }

    //GETTERS
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
    public Resources getCost() {
        return productionCost;
    }

    @Override
    public void takeDamage(int damage) {

    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public int getLevel() {
        return level;
    }
}