package game_engine;

import game_elements.*;
import game_elements.building.Cannon;
import game_elements.exceptions.*;
import game_elements.inhabitant.army.Archer;
import game_elements.inhabitant.villager.Collector;
import game_user_interface.InvalidMenuChoiceException;
import game_user_interface.UserInterface;

class GameEngineModel {
    protected VillageControl village;
    protected boolean run;
    protected BattleComputer battleComputer;
    protected UserInterface userInterface;
    protected AttackExplorer attackExplorer;
    protected VillageControl exploredVillage;

    // private BuildingFactory buildingFactory;
    // private InhabitantFactory inhabitantFactory;

}

public class GameEngineControl {
    GameEngineModel gameEngineModel;

    public GameEngineControl() {
        gameEngineModel = new GameEngineModel();
        gameEngineModel.village = new VillageControl(new VillageModel(), new VillageView());
        gameEngineModel.run = true;
        gameEngineModel.battleComputer = new BattleComputer();
        gameEngineModel.userInterface = new UserInterface(gameEngineModel.village.getMapSize(),
                gameEngineModel.village.getMapSize());
        gameEngineModel.attackExplorer = new AttackExplorer();
        gameEngineModel.exploredVillage = new VillageControl(new VillageModel(), new VillageView());
        // runGame();

    }

    // ================================================================================================================//
    private void runGame() {
        while (gameEngineModel.run) {
            gameEngineModel.village.updateTasks(); // thread this later :)
            gameEngineModel.userInterface.displayResources(gameEngineModel.village); // show resources
            gameEngineModel.userInterface.displayMap(gameEngineModel.village);
            gameEngineModel.userInterface.displayOptions();

            try {
                ActionType actionType = gameEngineModel.userInterface.getUserAction();
                action(actionType);
            } catch (InvalidMenuChoiceException e) {
                e.printStackTrace();
            }
        }
    }

    public void runConsoleGame() {
        runGame();
    }

    public String processRequest(String request) {

        if (request == null) {
            return "Request cannot be null";
        }

        String[] requestComponents = request.trim().toUpperCase().split(" ");
        String action = requestComponents[0];

        switch (action) {
            // TODO: IMPLEMENT THE COMMAND PARSER HERE, I FELL LIKE WE CAN MODIFY ACTION
            // METHOD INSTEAD
            // WILL DO IT LATER
        }

        return null;
    }

    public void action(ActionType actionType) {
        switch (actionType) {
            case QUIT:
                gameEngineModel.run = false;
                gameEngineModel.userInterface.print("Quitting Game");
                break;

            case UPGRADE_BUILD:
                // get x and y coords
                int[] coords = gameEngineModel.userInterface.getCoords(gameEngineModel.village.getMapSize(),
                        gameEngineModel.village.getMapSize());
                try {
                    gameEngineModel.village.upgradeBuilding(coords[0], coords[1]);
                } catch (NoBuilderAvaliable noBuilderAvaliable) {
                    gameEngineModel.userInterface.print("No Builder Available");
                } catch (NothingToUpgrade nothingToUpgrade) {
                    gameEngineModel.userInterface.print("Nothing to upgrade");
                } catch (MaxLevel maxLevel) {
                    gameEngineModel.userInterface.print("Building is Max Level");
                }
                break;

            case BUILD:
                try {
                    gameEngineModel.village.placeBuilding(new Cannon());
                } catch (NoBuilderAvaliable noBuilderAvaliable) {
                    gameEngineModel.userInterface.print("No Builder Available");
                } catch (NotEnoughResources notEnoughResources) {
                    gameEngineModel.userInterface.print("Not Enough Resources");
                } catch (BuildingInTheWay buildingInTheWay) {
                    gameEngineModel.userInterface.print("Building In The Way");
                } catch (MaxBuilding MaxBuilding) {
                    gameEngineModel.userInterface.print("Reach Structure Capacity");
                }
                break;

            case TRAIN:
                try {
                    gameEngineModel.village.trainArmy(new Archer());
                } catch (NotEnoughResources notEnoughResources) {
                    gameEngineModel.userInterface.print("Not Enough Resources");
                }
                break;

            case ATTACK:
                if (gameEngineModel.exploredVillage == null) {
                    gameEngineModel.userInterface.print("No village explored yet.");
                }

                if (!canAttack(gameEngineModel.village.getArmy(), gameEngineModel.exploredVillage)) {
                    gameEngineModel.userInterface.print("Cannot attack.");
                }

                ComputedBattle battleResult = attack(gameEngineModel.village, gameEngineModel.exploredVillage);

                if (battleResult.didWin()) {
                    gameEngineModel.userInterface
                            .print("You won the battle. Loot gained: " + battleResult.getLoot().toString());
                    gameEngineModel.village.addResources(battleResult.getLoot());
                } else {
                    gameEngineModel.userInterface.print("You lost the battle.");
                }
                break;

            case PRODUCE:
                try {
                    gameEngineModel.village.trainWorker(new Collector());
                } catch (NotEnoughResources notEnoughResources) {
                    gameEngineModel.userInterface.print("Not Enough Resources");
                } catch (MaxWorker maxWorker) {
                    gameEngineModel.userInterface.print("Max Worker");
                } catch (MaxLevel maxLevel) {
                    gameEngineModel.userInterface.print("Max Level");
                }
                break;

            case EXPLORE:
                // gameEngineModel.exploredVillage =
                // gameEngineModel.attackExplorer.reRollCandidate(); //todo adapt
                // .reRollCanadidate() to use VillageControl
                gameEngineModel.userInterface.print("Found a new village");
                break;

            case UPGRADE_TROOP:
                // userInterface.print("Upgrade troop not implemented yet");
                break;

            default:
                gameEngineModel.userInterface.print("That's not an option");
        }
    }

    /**
     * Returns a boolean value indicating whether a player can attack a village
     *
     * @param attacker the Army that is attacking
     * @param village  the Village that is being attacked
     * @return a boolean value indicating whether the attack is possible
     */
    public boolean canAttack(Army attacker, VillageControl village) {

        // check if attacker or village is null
        if (attacker == null || village == null) {
            return false;
        }

        // check if army is not empty
        if (attacker.getSize() == 0) {
            return false;
        }

        // check if village is not guarded
        if (village.getGuard()) {
            return false;
        }

        return true;
    }

    /**
     * Returns a ComputedBattle object representing the result of an attack
     *
     * @param attacker the Village that has the army attacking
     * @param defender the Village that is being attacked
     * @return a ComputedBattle object representing the result of the attack
     */
    public ComputedBattle attack(VillageControl attacker, VillageControl defender) {

        ComputedBattle battleResult = gameEngineModel.battleComputer.computedBattle(attacker, defender);

        if (battleResult.didWin()) {
            // Attacker won: add loot to attacker, remove from defender, and set guard time
            // for defender
            defender.subtractResources(battleResult.getLoot());
            // defender.setGuardTime(System.currentTimeMillis() + 3600000); //todo adapt
            // backgroundTask to accept gaurd time
            attacker.addResources(battleResult.getLoot());
            return battleResult;
        } else {
            // Attacker lost: set guard time for attacker
            // attacker(System.currentTimeMillis() + 3600000); //todo adapt backgroundTask
            // to accept gaurd time
            return battleResult;
        }
    }

}
