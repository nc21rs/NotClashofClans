package villageelements;

public class Inhabitant implements Damageable, Upgradeable, CanAttack{
    /**Attributes*/
    private int health;
    private int posX;
    private int posY;
    private int attackDamage;
    private int range;
    private int level;
    private boolean isDestroyed;

    protected void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
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

    }
    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }
    @Override
    public int getLevel() {
        return level;
    }
}
