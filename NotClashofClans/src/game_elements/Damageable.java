package game_elements;

public interface Damageable {
    void takeDamage(int damage);
    int getHealth();
    boolean isDestroyed();
}
