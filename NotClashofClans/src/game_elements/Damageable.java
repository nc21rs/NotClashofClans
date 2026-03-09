package game_elements;

/**
 * This interface represents the ability to take damage in the game.
 * It is implemented by classes that can be damaged, such as army units and
 * buildings.
 */
public interface Damageable {
    /**
     * Applies damage to the object.
     * 
     * @param damage the amount of damage to apply
     */
    void takeDamage(int damage);

    /**
     * Returns the current health of the object.
     * 
     * @return the current health of the object
     */
    int getHealth();

    /**
     * Returns whether the object is destroyed.
     * 
     * @return true if the object is destroyed, false otherwise
     */
    boolean isDestroyed();
}