package game_elements;

public class Inhabitant implements Damageable, Upgradeable, CanAttack {
    /** Attributes */
    private String name;                    //should have added this from the beginning
    private char shortName = '?';           //default is ?
    private int health;
    private int posX;
    private int posY;
    private int attackDamage;
    private int range;
    private int level;
    private boolean destroyed;
    private boolean onField;
    private Resources upgradeCost;
    private Resources productionCost;
    private int maxLevel;
    private boolean isUpgrading = false;    //defaults is no
    private int upgradeTimeSeconds = 10;    //default is 10s
    private final boolean ISBUILDING = false;     //simple fact, no setter


    protected void setName(String name) {
        this.name = name;
    }

    protected void setShortName(char shortName) {
        this.shortName = shortName;
    }

    public void setIsUpgrading(boolean upgrading) {
        isUpgrading = upgrading;
    }

    protected void setOnField(boolean onField) {
        this.onField = onField;
    }

    protected void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    protected void setRange(int range) {
        this.range = range;
    }

    protected void setLevel(int level) {
        this.level = level;
    }

    protected void setHealth(int health) {
        this.health = health;
    }

    protected void setPosY(int posY) {
        this.posY = posY;
    }

    protected void setPosX(int posX) {
        this.posX = posX;
    }

    protected void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    protected void setUpgradeCost(Resources upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    protected void setProductionCost(Resources productionCost) {
        this.productionCost = productionCost;
    }

    protected void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    @Override
    public int getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;

        // if health drops to 0, then inhabitant is destroyed
        if (health <= 0) {
            health = 0;
            destroyed = true;
        }
    }

    public boolean getOnField() {
        return onField;
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

    // method to get the upgrade cost of an inhabitant
    public Resources getUpgradeCost() {
        return upgradeCost;
    }

    public Resources getProductionCost() {
        return productionCost;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public void upgrade() {
        isUpgrading = false;
        level++;

        // increase stats of inhabitant when it is upgraded
        health += 10;
        attackDamage += 5;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public char getShortName() {
        return shortName;
    }

    @Override
    public int getUpgradeTimeSeconds() {
        return upgradeTimeSeconds;
    }

    @Override
    public boolean isUpgrading() {
        return isUpgrading;
    }

    @Override
    public boolean isBuilding() {
        return ISBUILDING;
    }
}
