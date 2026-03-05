package villageelements;

public interface Upgradeable {

    int getLevel();

    int getMaxLevel();

    Resources getUpgradeCost();

    void upgrade();
}
