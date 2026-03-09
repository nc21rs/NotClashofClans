package game_elements;

public interface Upgradeable {
    //Both classes shares this feature
    String getName();
    boolean isUpgrading();
    int getUpgradeTimeSeconds();
    boolean isBuilding();
    char getShortName();

    int getLevel();

    int getMaxLevel();

    Resources getUpgradeCost();

    void upgrade();
}
