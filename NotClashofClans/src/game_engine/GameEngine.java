package game_engine;

import game_elements.*;
import game_user_interface.InvalidMenuChoiceException;
import game_user_interface.UserInterface;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a task that is being performed in the game.
 * A task can be an upgrade, construction, or any other time-based action.
 */
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


/**
 * This class represents the main game engine for the Not Clash of Clans game.
 * It controls the flow of the game, manages the game state, and handles user
 * interactions.
 * Basically, the main class that runs the game.
 */
public class GameEngine {
    private boolean running;
    private long currentTime;
    private final BattleComputer battleComputer;
    private final AttackExplorer attackExplorer;
    private UserInterface userInterface;
    private Village exploredVillage;

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
        userInterface = new UserInterface(village.getMapSize(), village.getMapSize()); // sync village map size with
        upgradeTask = new ArrayList<>();
        battleComputer = new BattleComputer();
        attackExplorer = new AttackExplorer();
        exploredVillage = attackExplorer.reRollCandidate();

        // run game
        start();
    }

    // method handles running the game
    public void start() {
        // loop until user decides to quit
        while (running) {
            updateTasks(); // check any running tasks.
            // display info
            userInterface.displayResources(village);
            // display map
            userInterface.displayMap(village);
            // display options to user
            userInterface.displayOptions();

            try {
                ActionType actionType = userInterface.getUserAction();
                action(actionType);
            } catch (InvalidMenuChoiceException e) {
                e.printStackTrace();
            }
        }
    }

    public void action(ActionType actionType) {
        switch (actionType) {
            case QUIT:
                running = false;
                userInterface.print("Quitting Game");
                break;

            case UPGRADE_BUILD:
                // get x and y coords
                int[] coords = userInterface.getCoords(village.getMapSize(), village.getMapSize());
                requestUpgrade(coords[0], coords[1]);
                break;

            case BUILD:
                // pick building, then get x and y
                int[] buildSelection = userInterface.getCoords(village.getMapSize(), village.getMapSize());
                village.placeFarm(buildSelection[0], buildSelection[1]);
                break;

            case TRAIN:
                userInterface.print("Train not implemented yet");
                break;

            case ATTACK:
                if (exploredVillage == null) {
                    userInterface.print("No village explored yet.");
                }

                if (!canAttack(village.getArmy(), exploredVillage)) {
                    userInterface.print("Cannot attack.");
                }

                ComputedBattle battleResult = attack(village, exploredVillage);

                if (battleResult.didWin()) {
                    userInterface.print("You won the battle. Loot gained: " + battleResult.getLoot().toString());
                    village.addResources(battleResult.getLoot());
                } else {
                    userInterface.print("You lost the battle.");
                }
                break;

            case PRODUCE:
                userInterface.print("Produce not implemented yet");
                break;

            case EXPLORE:
                exploredVillage = attackExplorer.reRollCandidate();
                userInterface.print("Found a new village");
                break;

            case UPGRADE_TROOP:
                userInterface.print("Upgrade troop not implemented yet");
                break;

            default:
                userInterface.print("That's not an option");
        }
    }


    

    public void updateTasks() {
        showTasks();
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
        Building building = village.getBuildingAt(x, y);
        if (canUpgrade(building, village)) { // is it upgrade-able?
            village.spendResources(building.getUpgradeCost());
            upgradeTask.add(new Task(building, 60)); // Lets just assume all upgrades take 60seconds
            userInterface.print("New Task: Upgrade " + building.getName());
        } else {
            // otherwise, its not.
            userInterface.print("Failed: Cannot Upgrade Building");
        }

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

        // get cost to upgrade element and check if player has enough getResources
        Resources cost = element.getUpgradeCost();

        // if (!village.hasSufficientResources(cost)) {
        // return false;
        // }

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

        // get cost to build building and check if player has enough getResources
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

        // check if village has enough getResources to train inhabitant
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
//     * @param element the Building
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
//     * @param element the Inhabitant element
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

    /**
     * Passses all upgrades information that are queued. Trying to keep all printing
     * to UI class
     * Since Task obj is private, String containing task is being passed to
     * userInterface class
     */
    public void showTasks() {
        List<String[]> taskData = new ArrayList<>();
        userInterface.printTask();
        long now = System.currentTimeMillis();
        if (upgradeTask.isEmpty()) {
            userInterface.print("Nothing is upgrading");
        } else {
            for (Task t : upgradeTask) {
                String name = t.target.getName();
                String time;
                if (t.start + t.duration - now <= 0)
                    time = "done";
                else {
                    time = ((t.start + t.duration - now) / 1000) + "s";
                    taskData.add(new String[] { name, time });
                }
            }

            // Pass only the strings to the UI
            userInterface.printTaskStrings(taskData);
        }
    }

}
