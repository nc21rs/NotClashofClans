package game_engine;

import game_elements.*;

import java.util.Random;

/**
 * This class represents the result of a computed battle from the
 * BattleComputer.
 */
class ComputedBattle {
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

/**
 * This class is responsible for computing the outcome of a battle between an
 * attacking village and a defending village.
 */
public class BattleComputer {
    // For randomness in battle outcomes, loot, etc.
    // Will use later for more complex battle mechanics
    private Random dice = new Random();

    public ComputedBattle computedBattle(Village attacker, Village village) {
        int attackScore = computeAttackScore(attacker);
        int defenceScore = computeDefenceScore(village);
        boolean win = attackScore > defenceScore;
        Resources loot = computeLoot(village, win);

        return new ComputedBattle(attackScore, defenceScore, win, loot);
    }

    /**
     * Computes the attack score of an army. Very simple for now, sum attack damage
     * of army units.
     * 
     * @param attacker the Army that is attacking
     * @return the attack score of the army
     */
    private int computeAttackScore(Village attacker) {
        if (attacker == null) {
            return 0;
        }

        int score = 0;
        for (ArmyUnit unit : attacker.getArmy().getArmyUnits()) {
            if (!unit.isDestroyed()) {
                score += unit.getAttackDamage();
            }
        }

        return score;
    }

    /**
     * Computes the defence score of a village. Very simple for now, sum attack
     * damage of defensive
     * buildings and inhabitants (non-army units).
     * 
     * @param village the Village that is being defended
     * @return the defence score of the village
     */
    private int computeDefenceScore(Village village) {
        if (village == null) {
            return 0;
        }

        int score = 0;

        // get defence score from defensive buildings
        for (Building building : village.getBuildings()) {
            if (building instanceof DefenceBuilding && !building.isDestroyed()) {
                DefenceBuilding defenceBuilding = (DefenceBuilding) building;
                score += defenceBuilding.getAttackDamage();
            }
        }

        // get defence score from inhabitants
        for (Inhabitant inhabitant : village.getInhabitants()) {
            // for simplicity,
            // we will assume that army units of a village do not help the defence score
            if (inhabitant instanceof ArmyUnit) {
                continue;
            } else {
                if (!inhabitant.isDestroyed()) {
                    score += inhabitant.getAttackDamage();
                }
            }
        }

        return score;
    }

    /**
     * Computes the loot obtained from attacking a village if the attacker wins.
     * For now, the loot is a random amount of each resource type.
     * 
     * @param village the Village that is being attacked
     * @param win     whether the attacker won or not
     * @return the Resources representing the loot obtained from the attack
     */
    private Resources computeLoot(Village village, boolean win) {
        Resources loot = new Resources();

        if (win) {
            for (ResourceType type : ResourceType.values()) {
                int amount = dice.nextInt(village.getResourceAmount(type) + 1); // random amount between 0 and total
                                                                                // getResources of that type in the village
                loot.setAmount(type, amount);
            }
        }

        return loot;
    }

}
