package game_engine;

import game_elements.Resources;

/**
 * This class represents the result of a computed battle from the
 * BattleComputer.
 */
public class ComputedBattle {
    private int attackScore;
    private int defenceScore;
    private boolean win;
    private Resources loot;

    public ComputedBattle(int attackScore, int defenceScore, boolean win, Resources loot) {
        this.attackScore = attackScore;
        this.defenceScore = defenceScore;
        this.win = win;
        this.loot = loot;
    }

    public int getAttackScore() {
        return attackScore;
    }

    public int getDefenceScore() {
        return defenceScore;
    }

    public boolean didWin() {
        return win;
    }

    public Resources getLoot() {
        return loot;
    }
}
