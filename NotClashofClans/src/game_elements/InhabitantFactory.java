package game_elements;

import game_elements.inhabitant.army.Archer;
import game_elements.inhabitant.army.Catapult;
import game_elements.inhabitant.army.Knight;
import game_elements.inhabitant.army.Soldiers;
import game_elements.inhabitant.villager.Builder;
import game_elements.inhabitant.villager.Collector;
import game_elements.inhabitant.villager.Farmer;
import game_elements.inhabitant.villager.Miner;

import java.util.InputMismatchException;

public class InhabitantFactory {

    public Inhabitant createInhabitant(String type) {
        switch (type.toUpperCase()) {
            case "BUILDER":
                return new Builder();
            case "FARMER":
                return new Farmer();
            case "MINER":
                return new Miner();
            case "COLLECTOR":
                return new Collector();
            case "SOLDIER":
                return new Soldiers();
            case "ARCHER":
                return new Archer();
            case "KNIGHT":
                return new Knight();
            case "CATAPULT":
                return new Catapult();
            default:
                throw new IllegalArgumentException("Invalid inhabitant type: " + type);
        }
    }
}
