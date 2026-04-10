package game_engine;

import game_elements.Army;
import game_elements.ArmyUnit;
import game_elements.inhabitant.army.Catapult;
import game_elements.inhabitant.army.Knight;
import game_elements.inhabitant.army.Soldiers;

import java.util.Random;

/**
 * This class is responsible for generating armies in the game that attack villages.
 */

public class ArmyGenerator extends Thread {
    private int armySize;
    private Army generatedArmy;

    /**
     * Constructor will initialize the size of the army, then generate an army
     * @param armySize
     */
    public ArmyGenerator(int armySize) {
        this.armySize = armySize;
        run();
    }

    /** Getters and Setters */
    public int getArmySize() {return armySize;}
    public void setArmySize(int armySize) {this.armySize = armySize;}
    public Army getGeneratedArmy() {return generatedArmy;}

    /**
     * Running will make ArmyGenerator the object re-roll army selection.
     */
    public void run(){
        final int NUMARMYUNITS = 4; //number of possible army units to pick
        Army army = new Army();
        for( int i = 0; i < armySize; i++){
            Random rand = new Random();
            int newUnit = rand.nextInt(NUMARMYUNITS);
            switch (newUnit){
                case 0:
                    army.addUnit(new ArmyUnit());
                    break;
                case 1:
                    army.addUnit(new Soldiers());
                    break;
                case 2:
                    army.addUnit(new Knight());
                    break;
                case 3:
                    army.addUnit(new Catapult());
                    break;
                default:
                    army.addUnit(new ArmyUnit());
                    System.out.println(getClass().getName() + ": Random made selection outside switchcase");
            }
        }
        generatedArmy = army;
    }
}
