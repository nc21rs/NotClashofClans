package game_elements;

/**
 * This class represents an inhabitant in the game. It implements the
 * Damageable, Upgradeable, and CanAttack interfaces.
 */
public class Inhabitant implements Damageable, Upgradeable, CanAttack {
    /** Attributes */
    private String name;
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

    protected void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {return name;}

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
        level++;

        // increase stats of inhabitant when it is upgraded
        health += 10;
        attackDamage += 5;
    }
}
