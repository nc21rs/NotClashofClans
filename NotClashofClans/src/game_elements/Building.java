package game_elements;

public class Building implements Damageable, Upgradeable {

    /** Attributes */
    private String name;    //should have added this from the beginning
    private char shortName = '?'; //default is ?
    private int health;
    private int posX;
    private int posY;
    private int level;
    private boolean destroyed;
    private Resources productionCost;
    private Resources upgradeCost;
    private int maxLevel;
    private boolean isUpgrading = false;    //defaults is no
    private int upgradeTimeSeconds = 10;    //default is 10s
    private final boolean ISBUILDING = true;      //simple fact, no setter

    /** Methods */
    // SETTERS
    protected void setUpgradeTimeSeconds(int upgradeTimeSeconds) {
        this.upgradeTimeSeconds = upgradeTimeSeconds;
    }

    public void setIsUpgrading(boolean upgrading) {
        isUpgrading = upgrading;
    }

    protected void setShortName(char shortName) {
        this.shortName = shortName;
    }

    protected void setUpgradeCost(Resources upgradeCost){this.upgradeCost = upgradeCost;}
    protected void setName(String name){this.name = name;}
    protected void setMaxLevel(int maxLevel){this.maxLevel = maxLevel;}

    protected void setPosX(int posX) {
        this.posX = posX;
    }

    protected void setPosY(int posY) {
        this.posY = posY;
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

    // interface: Upgradable
    // method to get the upgrade cost of a building
    public Resources getUpgradeCost() {
        return upgradeCost;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;

        // if health drops to 0, then building is destroyed
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
            isUpgrading = false;
            return; 
        }
        
        // Upgrade if not at max level
        level++;
        health += 100;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isUpgrading() {
        return isUpgrading;
    }

    @Override
    public int getUpgradeTimeSeconds() {
        return upgradeTimeSeconds;
    }

    @Override
    public boolean isBuilding() {
        return ISBUILDING;
    }
}