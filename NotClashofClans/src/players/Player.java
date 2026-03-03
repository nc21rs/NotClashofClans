package players;

import villageelements.Village;

enum Rank{
    BRONZE,SILVER,GOLD,PLATINUM,DIAMOND;
}

public class Player {
    private Rank rank;
    private Village village;
    private int trophies;

    public Rank getRank() {
        return rank;
    }
    public int getTrophies() {return trophies;}
    public void addTrophies() {}
    public void removeTrophies(){}
}
