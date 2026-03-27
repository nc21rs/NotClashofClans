package game_elements;

import game_elements.building.*;
import java.util.InputMismatchException;

public class BuildingFactory {

    public Building createBuilding(String type, int x_cord, int y_cord) {
        Building building;

        switch (type) {
            case "TOWNHALL":
                building = new TownHall();
                break;
            case "CANNON":
                building = new Cannon();
                break;
            case "ARCHERTOWER":
                building = new ArcherTowers();
                break;
            case "FARM":
                building = new Farm();
                break;
            case "LUMBERMILL":
                building = new LumberMill();
                break;
            case "IRONMINE":
                building = new IronMine();
                break;
            case "GOLDMINE":
                building = new GoldMine();
                break;
            default:
                throw new InputMismatchException("Invalid building type: " + type);
        }

        building.setPosX(x_cord);
        building.setPosY(y_cord);
        return building;
    }
}
