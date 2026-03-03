package villageelements;

public class DefenceBuilding extends Building implements CanAttack{
    private int damage;
    private int range;

    /**Methods*/
    protected void setDamage(int damage) {
        this.damage = damage;
    }
    protected void setRange(int range) {
        this.range = range;
    }

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
    protected void setCost(Resources productionCost) {
        super.setCost(productionCost);
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

    /**Interface*/
    @Override
    public int getAttackDamage() {
        return damage;
    }
    @Override
    public int getRange() {
        return range;
    }
}
