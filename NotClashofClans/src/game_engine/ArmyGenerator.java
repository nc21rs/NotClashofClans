package game_engine;

import game_elements.Army;

import java.util.Random;

/**
 * This class is responsible for generating armies in the game that attack villages.
 */
public class ArmyGenerator {
    private Random dice;

    public Army createArmy(){
        return new Army();
    }

}
