package game_engine;

import java.util.List;

import game_elements.*;
import ChallengeDecision.*;

/**
 * This class serves as an adapter design pattern for the attack functionality in the game.
 * We translate the attacking village and defending village into the format needed 
 * for the Arbitrer given in the ChallengeDecision package. After the battle is 
 * decided by the Arbitrer, we translate the result back into a ComputedBattle 
 * that can be used in the game engine.
 */
public class AttackAdapter {

    /**
     * Computes the result of a battle between two villages.
     * 
     * @param attacker the attacking village
     * @param defender the defending village
     * @return the result of the battle as a ComputedBattle object
     */
    public ComputedBattle computeBattle (Village attacker, Village defender) {
        ChallengeEntitySet<Double, Double> battleAtackers = translateAttackerSet(attacker);
        ChallengeEntitySet<Double, Double> battleDefenders = translateDefenderSet(defender);

        ChallengeResult battleResult = Arbitrer.challengeDecide(battleAtackers, battleDefenders);

        boolean win = battleResult.getChallengeWon();
        Resources loot = translateLoot(battleResult);
        int attackScore = translateAttackScore(attacker);
        int defenceScore = translateDefenceScore(defender);

        return new ComputedBattle(attackScore, defenceScore, win, loot);
    }

    /**
     * Translates the attacker's village into an attack score.
     * 
     * @param attacker the attacking village
     * @return the attack score
     */
    private int translateAttackScore(Village attacker){
        
        if (attacker == null) {
            return 0;
        }

        int attackScore = 0;

        for (ArmyUnit unit : attacker.getArmy().getArmyUnits()) {
            if (!unit.isDestroyed()) {
                attackScore += unit.getAttackDamage();
            }
        }

        return attackScore;
    }

    /**
     * Translates the defender's village into a defence score.
     * 
     * @param defender the defending village
     * @return the defence score
     */
    private int translateDefenceScore(Village defender){
        
        if (defender == null) {
            return 0;
        }

        int defenceScore = 0;

        for (Building building : defender.getBuildings()) {
            if (!building.isDestroyed() && building instanceof DefenceBuilding) {
                DefenceBuilding defenceBuilding = (DefenceBuilding) building;
                defenceScore += defenceBuilding.getAttackDamage();
            }
        }

        for (Inhabitant inhabitant : defender.getInhabitants()) {
            if (!inhabitant.isDestroyed() && !(inhabitant instanceof ArmyUnit)) {
                defenceScore += inhabitant.getAttackDamage();
            }
        }

        return defenceScore;
    }

    /**
     * Converts the loot from the battle result into a resource object
     * 
     * @param battleResult the result of the battle
     * @return a Resources object representing the loot
     */
    private Resources translateLoot(ChallengeResult battleResult) {
        Resources loot = new Resources();

        // check if battleResult or its loot is null to avoid NullPointerException
        if (battleResult == null || battleResult.getLoot() == null) { 
            return loot;
        }

        for (ResourceType type : ResourceType.values()) {
            int index = type.getIndex();
            double amountToLoot = battleResult.getLoot().get(index).getProperty();
            loot.setAmount(type, (int)amountToLoot);
        }
  
        return loot;
    }


    /**
     * Translates the attacker's village army into a set of ChallengeAttacks for the Arbitrer to evaluate.
     * 
     * @param attacker the attacking village
     * @return a set of ChallengeAttacks representing the attacker's army
     */
    private ChallengeEntitySet<Double, Double> translateAttackerSet(Village attacker) {

        // translate the attacking village's army into a ChallengeEntitySet
        ChallengeEntitySet<Double, Double> attackerSet = new ChallengeEntitySet<>();

        if (attacker == null ){
            return attackerSet;
        } 

        //loop through the army units in the attacker's village and add them to the attackerSet as ChallengeAttacks
        for (ArmyUnit unit : attacker.getArmy().getArmyUnits()) {
            if (!unit.isDestroyed()) {
                ChallengeAttack<Double, Double> attackerToAdd = new ChallengeAttack<>((double)unit.getAttackDamage(), (double)unit.getHealth());
                attackerSet.getEntityAttackList().add(attackerToAdd);
            }
        }
        
        // set of attackers from the attacker's village is returned 
        return attackerSet;
    }


    /**
     * Translates the defender's village into a set of ChallengeDefences and challengeResources for the Arbitrer to evaluate.
     * 
     * @param defender the defending village
     * @return a set of ChallengeDefences representing the defender's village
     */
    private ChallengeEntitySet<Double, Double> translateDefenderSet(Village defender) {

        // translate the defending village's buildings and inhabitants into a ChallengeEntitySet
        ChallengeEntitySet<Double, Double> defenderSet = new ChallengeEntitySet<>();

        if (defender == null ){
            return defenderSet;
        } 

        // loop through the defensive buildings in the defender's village and add them to the defenderSet as ChallengeDefences
        for (Building building : defender.getBuildings()) {
            if (!building.isDestroyed() && building instanceof DefenceBuilding) {
                DefenceBuilding defenceBuilding = (DefenceBuilding) building;

                ChallengeDefense<Double, Double> defenceToAdd = new ChallengeDefense<>((double)defenceBuilding.getAttackDamage(), (double)defenceBuilding.getHealth());
                defenderSet.getEntityDefenseList().add(defenceToAdd);
            }
        }

        // loop through the non army inhabitants in the defender's village and add them to the defenderSet as ChallengeDefences
        for (Inhabitant inhabitant : defender.getInhabitants()) {
            if (!inhabitant.isDestroyed() && !(inhabitant instanceof ArmyUnit)) {
                ChallengeDefense<Double, Double> defenceToAdd = new ChallengeDefense<>((double)inhabitant.getAttackDamage(), (double)inhabitant.getHealth());
                defenderSet.getEntityDefenseList().add(defenceToAdd);
            }
        }

        // Resources can from defending village can be looted if the attacker wins, so they are added to the defenderSet as ChallengeResources 
        for (ResourceType type : ResourceType.values()) {
            ChallengeResource<Double, Double> resourceToAdd = new ChallengeResource<>((double)defender.getResources().getAmount(type));
            defenderSet.getEntityResourceList().add(resourceToAdd);
        }

        // set of defenders from the defending village is returned 
        return defenderSet;
    }

}
