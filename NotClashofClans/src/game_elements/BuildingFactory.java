package game_elements;

import game_elements.building.*;

import java.util.InputMismatchException;

public class BuildingFactory extends Building {
    Building Factory(int[] type){
        if(type[0]==0) {    //resource building
            switch (type[1]){
                case 1:
                    return new Cannon();
                case 2: //case 1, returns cannon, but also for default
                    return new ArcherTowers();
                default:
                    throw new InputMismatchException("unexpected type");
            }
        } else {    //defence building
            switch (type[1]){
                case 1:
                    return new Farm();
                case 2:
                    return new LumberMill();
                case 3:
                    return new IronMine();
                case 4:
                    return new GoldMine();
                default:
                    throw new InputMismatchException("unexpected type");
            }
        }
    }
}
