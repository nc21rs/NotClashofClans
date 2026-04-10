package game_player_database;

import java.util.Map;
import java.util.HashMap;

/**
 * This enum represents the different ranks that a player can achieve in the
 * game.
 */
enum Rank {
    BRONZE, SILVER, GOLD, PLATINUM, DIAMOND;
}

/**
 * This class represents the player's database in the game. It contains
 * information about the player's rank, village, trophies, etc.
 * 
 * Unfortunately, we were not able to have the database fully implemented in
 * time,
 * but we have a placeholder Map that can be used to store players info. This
 * map is used for authentication.
 */
public class PlayerDataBase {

    private Rank rank;
    private int trophies;
    private Map<String, String> playerLoginInfo;

    public PlayerDataBase() {
        // Map with username and password for authentication
        playerLoginInfo = new HashMap<>();

        // Placeholder data for testing
        playerLoginInfo.put("rafael", "1234");
        playerLoginInfo.put("norman", "1234");
    }

    public Rank getRank() {
        return rank;
    }

    public int getTrophies() {
        return trophies;
    }

    public void addTrophies() {
    }

    public void removeTrophies() {
    }

    /**
     * This method authenticates if a player exists in the database and the password
     * is correct.
     * This is used when clients try to connect to the server.
     * 
     * @param username Player's username
     * @param password Player's password
     * @return true if the player exists and the password is correct, if not, false.
     */
    public boolean playerAuthentication(String username, String password) {
        if (playerLoginInfo.containsKey(username) && playerLoginInfo.get(username).equals(password)) {
            return true;
        }
        return false;
    }
}
