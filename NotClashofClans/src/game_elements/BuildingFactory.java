package game_elements;

import game_elements.building.*;

import java.util.InputMismatchException;

public class BuildingFactory extends Building {

    public Building createBuilding(String type){
        switch (type) {
            case "TOWNHALL":
                return new TownHall();
            case "CANNON":
                return new Cannon();
            case "ARCHERTOWER":
                return new ArcherTowers();
            case "FARM":
                return new Farm();
            case "LUMBERMILL":
                return new LumberMill();
            case "IRONMINE":
                return new IronMine();
            case "GOLDMINE":
                return new GoldMine();
            default:
                throw new InputMismatchException("Invalid building type: " + type);
        }
    }
}
