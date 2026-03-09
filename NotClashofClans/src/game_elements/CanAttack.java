package game_elements;

/**
 * This interface represents the ability to attack in the game.
 * It is implemented by classes that can perform attacks, such as army units.
 */
public interface CanAttack {

    /**
     * Returns the attack damage of the unit.
     * 
     * @return the attack damage of the unit
     */
    public int getAttackDamage();

    /**
     * Returns the attack range of the unit.
     * 
     * @return the attack range of the unit
     */
    public int getRange();
}
