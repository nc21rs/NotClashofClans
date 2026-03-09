package game_engine;

import game_elements.*;
import game_user_interface.InvalidMenuChoiceException;
import game_user_interface.UserInterface;

import java.util.ArrayList;

class Task {
    long start;
    long duration;
    boolean done;
    Building target;

    public Task(Building target, long durationSeconds) {
        this.target = target;
        start = System.currentTimeMillis();
        duration = durationSeconds * 1000;
        done = false;
    }

    public boolean isDone() {
        return System.currentTimeMillis() >= (start + duration);
    }

}

public class GameEngine {
    private boolean running;
    private long currentTime;
    private final BattleComputer battleComputer;
    private final AttackExplorer attackExplorer;
    private UserInterface userInterface;
    private Village playerVillage;

    UserInterface userInterface;
    Village village;
    ArrayList<Task> upgradeTask;

    /**
     * Constructor. Initializes all required game parameters/processes
     * user_interface, players
     */
    public GameEngine() {
        // initialize important game engine variables
        running = true; // the game is running
        currentTime = System.currentTimeMillis();
        userInterface = new UserInterface(20, 20);
        village = new Village();
        upgradeTask = new ArrayList<>();
        battleComputer = new BattleComputer();
        attackExplorer = new AttackExplorer();

        // run game
        start();
    }

    // method handles running the game
    public void start() {
        // loop until user decides to quit
        while (running) {
            update(); // check any running tasks.
            userInterface.displayResources(village);
            userInterface.displayOptions();

            try {
                ActionType actionType = userInterface.getUserAction();
                action(actionType);
            } catch (InvalidMenuChoiceException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void action(ActionType actionType) {
        switch (actionType) {
            case QUIT:
                running = false;
                break;
            case UPGRADE_BUILD:
                // get x and y coords
                break;
            case BUILD:
                // pick building, then get x and y
            case TRAIN:
                // prompt user with options to train troops
                break;
            case ATTACK:
                // prompt user to scout bases and perform attack
                break;
            default:
                System.out.println("That is not an option");
        }
    }

    public void update() {
        currentTime = System.currentTimeMillis();
        java.util.Iterator<Task> iterator = upgradeTask.iterator(); // analyize list of running upgrades.
        while (iterator.hasNext()) { // for each task.
            Task task = iterator.next();

            if (task.isDone()) { // check if upgrade task done
                task.target.upgrade(); // apply game upgrade.
                iterator.remove(); // remove task from list. Via iterator. (By-passes problem of for-each removing
                                   // live item from list)
            }
        }
    }

    // user wants to upgrade selected grid.
    public void requestUpgrade(int x, int y) {
        Building building = village.mapBuild[x][y];
        if (canUpgrade(building, village)) { // is it upgrade-able?
            village.spendResources(building.getUpgradeCost());
            upgradeTask.add(new Task(building, 60)); // Lets just assume all upgrades take 60seconds

        }
        // otherwise, its not.
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

        if (!village.hasSufficientResources(cost)) {
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
        if (!village.hasSufficientResources(cost)) {
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

        if (!village.hasSufficientResources(cost)) {
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
     * @param attacker the Village that has the army attacking
     * @param defender the Village that is being attacked
     * @return a ComputedBattle object representing the result of the attack
     */
    public ComputedBattle attack(Village attacker, Village defender) {

        ComputedBattle battleResult = battleComputer.computedBattle(attacker, defender);

        if (battleResult.didWin()) {
            // Attacker won: add loot to attacker, remove from defender, and set guard time
            // for defender
            defender.spendResources(battleResult.getLoot());
            defender.setGuardTime(currentTime + 3600000);
            attacker.addResources(battleResult.getLoot());
            return battleResult;
        } else {
            // Attacker lost: set guard time for attacker
            attacker.setGuardTime(currentTime + 3600000);
            return battleResult;
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
        return attackExplorer.reRollCandidate();
    }

    /**
     * Returns an Army object representing the result of generating an incoming army
     * 
     * @return an Army object generated when a village is attacked
     */
    public Army generateIncomingArmy() {
        return new Army();
    }

    public void villageCollectResources(Village village) {
        if (village == null) {
            return;
        }

        village.generateResources();
    }
}
