package game_user_interface;

import game_engine.ActionType;
import java.util.Scanner;

public class UserInterface {
    private int width;
    private int height;
    private Scanner scanner;

    public UserInterface(int width, int height) {
        this.width = width;
        this.height = height;
        this.scanner = new Scanner(System.in);
    }

    // disp
    public void displayMap() {
        System.out.println("Map size is " + width + "x" + height);
        System.out.println("===== Map =====");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(". ");
            }
            System.out.println();
        }

        System.out.println("===============\n");
    }

    // method to display the action options to the user
    public void displayOptions() {
        System.out.println("Choose an action:");
        System.out.println("1. Build");
        System.out.println("2. Train Troops");
        System.out.println("3. Produce");
        System.out.println("4. Upgrade Troop");
        System.out.println("5. Upgrade Building");
        System.out.println("6. Explore Villages");
        System.out.println("7. Attack");
        System.out.println("8. Quit Game");
    }

    public ActionType getUserAction() {
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                return ActionType.BUILD;
            case 2:
                return ActionType.TRAIN;
            case 3:
                return ActionType.PRODUCE;
            case 4:
                return ActionType.UPGRADE_TROOP;
            case 5:
                return ActionType.UPGRADE_BUILD;
            case 6:
                return ActionType.EXPLORE;
            case 7:
                return ActionType.ATTACK;
            case 8:
                return ActionType.QUIT;
            default:
                System.out.println("Invalid choice. Please try again.");
                return getUserAction();
        }
    }

    public void sendActionRequest(ActionType actionType) {
        if (actionType == null) {
            System.out.println("No action sent.");
            return;
        }

        System.out.println("Requested action: " + actionType);
    }
}
