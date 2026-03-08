package game_player_database;

import game_elements.Village;

enum Rank{
    BRONZE,SILVER,GOLD,PLATINUM,DIAMOND;
}

public class PlayerDataBase {
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
