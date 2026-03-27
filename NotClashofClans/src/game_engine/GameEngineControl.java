package game_engine;

import game_elements.VillageControl;
import game_elements.VillageModel;
import game_elements.VillageView;
import game_user_interface.InvalidMenuChoiceException;
import game_user_interface.UserInterface;

class GameEngineModel{
    protected VillageControl village;
    protected boolean run;
    protected BattleComputer battleComputer;
    protected UserInterface userInterface;
}

public class GameEngineControl {
    GameEngineModel gameEngineModel;
    GameEngineControl(){
        gameEngineModel.village = new VillageControl(new VillageModel(), new VillageView());
        gameEngineModel.run = true;
        gameEngineModel.battleComputer = new BattleComputer();
        gameEngineModel.userInterface = new UserInterface(gameEngineModel.village.getMapSize(),gameEngineModel.village.getMapSize());



    }
    //================================================================================================================//
    private void runGame(){
        while(gameEngineModel.run){
            gameEngineModel.village.updateTasks(); //thread this later :)
            gameEngineModel.userInterface.displayResources(gameEngineModel.village);    //show resources
            gameEngineModel.userInterface.displayOptions();

            try {
                ActionType actionType = gameEngineModel.userInterface.getUserAction();
                action(actionType);
            } catch (InvalidMenuChoiceException e) {
                e.printStackTrace();
            }
        }
    }

    public void action(ActionType actionType) {
        switch (actionType) {
            case QUIT:
                gameEngineModel.run = false;
                gameEngineModel.userInterface.print("Quitting Game");
                break;

            case UPGRADE_BUILD:
                // get x and y coords
//                int[] coords = gameEngineModel.userInterface.getCoords(gameEngineModel.village.getMapSize(), gameEngineModel.village.getMapSize());
//
                break;

            case BUILD:

                break;

            case TRAIN:
//                userInterface.print("Train not implemented yet");
                break;

            case ATTACK:
//                if (exploredVillage == null) {
//                    userInterface.print("No village explored yet.");
//                }
//
//                if (!canAttack(village.getArmy(), exploredVillage)) {
//                    userInterface.print("Cannot attack.");
//                }
//
//                ComputedBattle battleResult = attack(village, exploredVillage);
//
//                if (battleResult.didWin()) {
//                    userInterface.print("You won the battle. Loot gained: " + battleResult.getLoot().toString());
//                    village.addResources(battleResult.getLoot());
//                } else {
//                    userInterface.print("You lost the battle.");
//                }
                break;

            case PRODUCE:
//                userInterface.print("Produce not implemented yet");
                break;

            case EXPLORE:
//                exploredVillage = attackExplorer.reRollCandidate();
//                userInterface.print("Found a new village");
                break;

            case UPGRADE_TROOP:
//                userInterface.print("Upgrade troop not implemented yet");
                break;

            default:
                gameEngineModel.userInterface.print("That's not an option");
        }
    }

}
