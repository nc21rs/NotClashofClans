package game_engine;

import game_elements.Village;
import game_elements.building.*;
import game_elements.inhabitant.villager.*;
import game_elements.ResourceType;
import game_elements.Resources;
import java.util.Random;

class VillageGenerator{
    protected Random random;

    public VillageGenerator() {
        this.random = new Random();
    }

    /**
     * Generates a random village with random resources, buildings, and inhabitants.
     * 
     * @return a randomly generated Village 
     */
    public Village generateVillage() {
        Village generatedVillage = new Village();

        // Generate resources for generated village
        // random number for each resource
        Resources randomResources = new Resources();
        for (ResourceType type : ResourceType.values()) {
            randomResources.setAmount(type, random.nextInt(100));
        }
        generatedVillage.addResources(randomResources);

        // Generate buildings for generated village
        for (int i = 0; i < 1 + random.nextInt(4); i++) {
            
            int defenceChoice = random.nextInt(2);

            // Generate random building
            if (defenceChoice == 0) {
                generatedVillage.addBuilding(new Cannon());
            } else if (defenceChoice == 1) {
                generatedVillage.addBuilding(new ArcherTowers());
            } 
        }
    
        // generate inhabitants for generated village
        for (int i = 0; i < 1 + random.nextInt(4); i++) {
            int villagerChoice =  random.nextInt(4);

            if (villagerChoice == 0) {
                generatedVillage.addInhabitant(new Builder());
            } else if (villagerChoice == 1) {
                generatedVillage.addInhabitant(new Farmer());
            } else if (villagerChoice == 2) {
                generatedVillage.addInhabitant(new Collector());
            } else if (villagerChoice == 3) {
                generatedVillage.addInhabitant(new Miner());
            }
        }

        return generatedVillage;
    }
}

/**
 * This class is responsible for generating random villages for the player to attack.
 * It uses the VillageGenerator for abstraction on village generation process.
 */
public class AttackExplorer extends VillageGenerator{
    private Village candidate; 

    public AttackExplorer() {
        this.candidate = generateVillage();
    }

    /**
     * Returns the current candidate village for the player to attack.
     * 
     * @return the current candidate village
     */
    public Village showCandidate(){
        return candidate;
    }

    /**
     * Generates a new candidate village for the player to attack.
     * 
     * @return the new candidate village
     */
    public Village reRollCandidate(){
        this.candidate = generateVillage();
        return candidate;
    }
}
