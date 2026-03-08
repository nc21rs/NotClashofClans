package game_elements;

public interface Upgradeable {

    int getLevel();

    int getMaxLevel();

    Resources getUpgradeCost();

    void upgrade();
}
