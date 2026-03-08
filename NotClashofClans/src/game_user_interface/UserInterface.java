package game_user_interface;

import game_engine.ActionType;
import game_elements.Village;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

public class UserInterface {
    private int width;
    private int height;
    private Scanner scanner;

    public UserInterface(int width, int height) {
        this.width = width;
        this.height = height;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Method to display the map of the current village.
     */
    public void displayMap() {
        System.out.println("Map size is " + width + "x" + height);
        System.out.println("===== Map =====");

        // Print a grid of dots representing the map using streams
        IntStream.range(0, height).forEach(row -> {
            IntStream.range(0, width).forEach(col -> System.out.print(". "));
            System.out.println();
        });

        System.out.println("===============\n");
    }

    /**
     * Method to display the choice options to the user.
     */
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

    /**
     * Method to get the user's action.
     * 
     * @return the ActionType corresponding to the user's choice
     */
    public ActionType getUserAction() throws InvalidMenuChoiceException, InputMismatchException {
        while (true) {
            try {
                System.out.print("Enter the number corresponding to your action: ");
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
                        throw new InvalidMenuChoiceException(" Please choose a number between 1 and 8.");
                }
            } catch (InvalidMenuChoiceException e) {
                System.out.println("Invalid choice." + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Choice must be a number.");
            } finally {
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    /**
     * Method to send the user's action request to the game engine.
     * 
     * @param actionType the ActionType corresponding to the user's choice
     */
    public void sendActionRequest(ActionType actionType) {
        if (actionType == null) {
            System.out.println("No action sent.");
            return;
        }

        System.out.println("Requested action: " + actionType);
    }

    /**
     * Method to display the village with buildings.
     * 
     * @param village the Village to be displayed
     */
    public void displayVillage(Village village) {
        if (village == null) {
            System.out.println("No village provided");
            return;
        }

        // for now, just display the map of the village
        IntStream.range(0, height).forEach(row -> {
            IntStream.range(0, width).forEach(col -> System.out.print(". "));
            System.out.println();
        });
    }

    /**
     * Method to display the village resources.
     *
     * @param village the Village whose resources will be displayed
     */
    public void displayResources(Village village) {
        if (village == null) {
            System.out.println("No village provided.");
            return;
        }

        System.out.println("===== RESOURCES =====");
        System.out.println(village.getResources());
        System.out.println("=====================\n");
    }

}
