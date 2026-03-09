package game_elements;

/**
 * This class represents a building in the game. It implements the Damageable
 * and Upgradeable interfaces.
 */
public class Building implements Damageable, Upgradeable {

    /** Attributes */
    private String name;
    private char shortName = '?';
    private int health;
    private int posX;
    private int posY;
    private int level;
    private boolean destroyed;
    private Resources productionCost;
    private Resources upgradeCost;
    private int maxLevel;

    /** Methods */
    // SETTERS
    protected void setShortName(char shortName) {
        this.shortName = shortName;
    }

    protected void setUpgradeCost(Resources upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    protected void setPosX(int posX) {
        this.posX = posX;
    }

    protected void setPosY(int posY) {
        this.posY = posY;
    }

    protected void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    protected void setProductionCost(Resources productionCost) {
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

    // GETTERS
    public char getShortName() {
        return shortName;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Resources getProductionCost() {
        return productionCost;
    }

    public Resources getUpgradeCost() {
        return upgradeCost;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;

        if (health <= 0) {
            health = 0;
            destroyed = true;
        }
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public void upgrade() {
        if (level >= maxLevel) {
            return;
        }

        level++;
        health += 100;
    }

    public String getName() {
        return name;
    }
}