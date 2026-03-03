package game;

import villageelements.*;

import java.util.Random;

class ComputedBattle{
    private int attackScore;
    private int defenceScore;
    private boolean win;
    private Resources loot;
    public ComputedBattle(){}
}

public class BattleComputer {
    private Random dice;
    public ComputedBattle computedBattle(Army attacker, Village defender){return new ComputedBattle();}
    private int computeAttackScore(Army army){return 0;}
    private int computeDefenceScore(Village village){return 0;}
    private Resources computeLoot(Village village, boolean win){
        return new Resources();
    }
}
