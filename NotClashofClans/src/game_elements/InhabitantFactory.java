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
    public Inhabitant Factory(int[] type){
        if(type[0]==0){//army
            switch(type[1]){    //Soldiers for case 1, but will also be the safe default
                case 1:
                    return new Soldiers();
                case 2:
                    return new Archer();
                case 3:
                    return new Catapult();
                case 4:
                    return new Knight();
                default:
                    throw new InputMismatchException("unexpected type");
            }
        } else {//villagers
            switch (type[1]){
                case 1:
                    return new Builder();
                case 2:
                    return new Collector();
                case 3:
                    return new Farmer();
                case 4:
                    return new Miner();
                default:
                    throw new InputMismatchException("unexpected type");
            }
        }
    }
}
