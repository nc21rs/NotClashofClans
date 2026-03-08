package game_engine;

import game_elements.*;
import game_user_interface.UserInterface;

/**
 * Author Notes: Norman
 *
 * We have 3 Packages: Game Engine, UI, Assets, +Players(JDBS)
 * > Running the game will create ONLY 1 of each instance
 * > Game Engine is the heart.
 */

public class GameEngine {
    private boolean running;
    private long currentTime;
    private UserInterface userInterface;
    private Village playerVillage;
    private BattleComputer battleComputer;
    private AttackExplorer attackExplorer;
    
    /**
     * Constructor. Initializes all required game parameters/processes 
     * user_interface, players
     */
    public GameEngine() {
        // initialize important game engine variables
        running = true; 
        currentTime = System.currentTimeMillis(); 
        userInterface = new UserInterface(20,20); 
        playerVillage = new Village(); 
        battleComputer = new BattleComputer();
        attackExplorer = new AttackExplorer();
    }

    /**
     * Returns a boolean value indicating whether a player can attack a village
     * 
     * @param attacker the Army that is attacking
     * @param village  the Village that is being attacked
     * @return a boolean value indicating whether the attack is possible
     */
    public boolean canAttack(Army attacker, Village village) {

        // check if attacker or village is null
        if (attacker == null || village == null) {
            return false;
        }

        // check if army is not empty
        if (attacker.getSize() == 0) {
            return false;
        }

        // check if village is not guarded
        if (village.getGuardTime() > currentTime) {
            return false;
        }

        return true;
    }

    /**
     * Returns a boolean value indicating whether a player can upgrade an element
     * 
     * @param element the Upgradeable element
     * @param village the Village that contains the element
     * @return a boolean value indicating whether the upgrade is possible
     */
    public boolean canUpgrade(Upgradeable element, Village village) {

        // check if element or village is null
        if (element == null || village == null) {
            return false;
        }

        // check if element is already at max level (denoted by Village Hall level)
        if (element.getLevel() >= element.getMaxLevel()) {
            return false;
        }

        // get cost to upgrade element and check if player has enough resources
        Resources cost = element.getUpgradeCost();
        
        if (!village.hasSufficientResources(cost)){
            return false;
        }


        return true;
    }

    /**
     * Returns a boolean value indicating whether a player can build a building
     * 
     * @param building the Building to be constructed
     * @param village  the Village that contains the building
     * @return a boolean value indicating whether the building can be constructed
     */
    public boolean canBuild(Building building, Village village) {
        // check if building or village is null
        if (building == null || village == null) {
            return false;
        }

        // get cost to build building and check if player has enough resources
        Resources cost = building.getProductionCost();
        if (!village.hasSufficientResources(cost)){
            return false;
        }

        // check if building coordinates are valid (positive and within map boundaries)
        if (building.getPosX() < 0 || building.getPosY() < 0) {
            return false;
        }
        if (building.getPosX() >= village.getMapSize() || building.getPosY() >= village.getMapSize()) {
            return false;
        }

        // check if building coordinates are not already occupied
        for (Building b : village.getBuildings()) {
            if (b.getPosX() == building.getPosX() && b.getPosY() == building.getPosY()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns a boolean value indicating whether a player can train an inhabitant
     * 
     * @param inhabitant the Inhabitant of the village
     * @param village    the Village that contains the Inhabitant
     * @return a boolean value indicating whether the training is possible
     */
    public boolean canTrain(Inhabitant inhabitant, Village village) {
        // check if inhabitant or village is null
        if (inhabitant == null || village == null) {
            return false;
        }

        // check if village has enough resources to train inhabitant
        Resources cost = inhabitant.getProductionCost();
        
        if (!village.hasSufficientResources(cost)){
            return false;
        }


        // check if village has enough population capacity to train inhabitant
        if (village.getPopulation() >= village.getPopulationMax()) {
            return false;
        }

        return true;
    }

    /**
     * Updates the current time in the game
     */
    public void updateTime() {
        currentTime = System.currentTimeMillis();
    }

    /**
     * Returns a ComputedBattle object representing the result of an attack
     * 
     * @param attacker the Army that is attacking
     * @param defender the Village that is being attacked
     * @return a ComputedBattle object representing the result of the attack
     */
    public ComputedBattle attack(Army attacker, Village defender) {
        // check if attack is possible
        if (canAttack(attacker, defender)) { // attack possible
            return battleComputer.computedBattle(attacker, defender);
        } else { // attack not possible
            return null;
        }
    }

    /**
     * Returns an ActionTimer object representing the time required to upgrade an
     * element
     * 
     * @param element the Upgradeable element
     * @param village the Village that contains the element
     * @return an ActionTimer object representing the time required to upgrade the
     *         element
     */
    public ActionTimer upgrade(Upgradeable element, Village village) {
        if (canUpgrade(element, village)) {
            village.spendResources(element.getUpgradeCost());
            element.upgrade();
            return new ActionTimer();
        } else {
            return null;
        }
    }

    /**
     * Returns an ActionTimer object representing the time required to build a
     * building
     * 
     * @param element the Building
     * @param village the Village where the building is being constructed
     * @return an ActionTimer object representing the time required to build the
     *         building
     */
    public ActionTimer build(Building building, Village village) {

        if (canBuild(building, village)) {
            village.spendResources(building.getProductionCost());
            village.addBuilding(building);
            return new ActionTimer();
        } else {
            return null;
        }
    }

    /**
     * Returns an ActionTimer object representing the time required to train an
     * inhabitant
     * 
     * @param element the Inhabitant element
     * @param village the Village where the inhabitant is being trained
     * @return an ActionTimer object representing the time required to train the
     *         inhabitant
     */
    public ActionTimer train(Inhabitant inhabitant, Village village) {
        if (canTrain(inhabitant, village)) {
            village.spendResources(inhabitant.getProductionCost());
            village.addInhabitant(inhabitant);
            return new ActionTimer();
        } else {
            return null;
        }
    }

    /**
     * Returns a Village object representing the result of exploring for new
     * villages
     * 
     * @return a Village object generated when exploring for new villages
     */
    public Village exploreVillages() {
        return new Village();
    }

    /**
     * Returns an Army object representing the result of generating an incoming army
     * 
     * @return an Army object generated when a village is attacked
     */
    public Army generateIncomingArmy() {
        return new Army();
    }
}
