package game_player_database;

import game_elements.Village;

/**
 * This enum represents the different ranks that a player can achieve in the game.
 */
enum Rank{
    BRONZE,SILVER,GOLD,PLATINUM,DIAMOND;
}

/**
 * This class represents the player's database in the game. It contains
 * information about the player's rank, village, trophies, etc.
 */
public class PlayerDataBase {
    private Rank rank;
    private int trophies;

    public Rank getRank() {
        return rank;
    }
    public int getTrophies() {return trophies;}
    public void addTrophies() {}
    public void removeTrophies(){}
}
