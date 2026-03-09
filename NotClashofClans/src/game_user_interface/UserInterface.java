package game_user_interface;

import game_elements.ResourceType;
import game_engine.ActionType;
import game_elements.Village;
import game_engine.GameEngine;

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
    public void displayMap(Village village) {
        System.out.println("Map size is " + width + "x" + height);
        System.out.println("===== Map =====");

        // Print a grid of dots representing the map using streams
        IntStream.range(0, height).forEach(row -> {
            IntStream.range(0, width).forEach(col -> {
                if (village.mapBuild[row][col] != null){
                    System.out.print(village.mapBuild[row][col].getShortName()+" ");
                } else {
                    System.out.print(". ");
                }
            });
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

        System.out.println("\n===== RESOURCES =====");
        //prints each resource individually.
        System.out.printf("%d Food\n",village.getResources(ResourceType.FOOD));
        System.out.printf("%d Wood\n",village.getResources(ResourceType.WOOD));
        System.out.printf("%d Iron\n",village.getResources(ResourceType.IRON));
        System.out.printf("%d Gold\n",village.getResources(ResourceType.GOLD));
        System.out.printf("%d Population\n",village.getResources(ResourceType.POPULATION));
        System.out.println("=====================\n");
    }

    /**
     * Prompts the user for X and Y coordinates and returns them as an array.
     * @param maxX The maximum width of the map
     * @param maxY The maximum height of the map
     * @return int array [x,y]
     */
    public int[] getCoords(int maxX, int maxY) {
        int x = -1;
        int y = -1;

        System.out.println("\n===== Enter Target Coordinates =====");

        // Get X Coordinate
        while (x < 0 || x >= maxX) {
            System.out.printf("Enter X [0,%d]: ", maxX - 1);
            if (scanner.hasNextInt()) {
                x = scanner.nextInt();
                if (x < 0 || x >= maxX) System.out.println("---- Out of bounds ----");
            } else {
                System.out.println("---- Enter a number ----");
                scanner.next(); // Clear invalid token
            }
        }

        // Get Y Coordinate
        while (y < 0 || y >= maxY) {
            System.out.printf("Enter Y [0,%d]: ", maxY - 1);
            if (scanner.hasNextInt()) {
                y = scanner.nextInt();
                if (y < 0 || y >= maxY) System.out.println("---- Out of bounds ----");
            } else {
                System.out.println("---- Enter a number ----");
                scanner.next(); // Clear invalid token
            }
        }

        scanner.nextLine(); // Crucial: clear the "Enter" key from the buffer
        return new int[]{x, y};
    }

    /**
     * (WIP)
     * Pair of helper methods to print any upgrade that is in progress
     */
    public void printTask(){
        System.out.println("\n===== Upgrades: =====");
    }
    public void printTaskStrings(List<String[]> data) {
        for (String[] row : data) {
            System.out.printf("Building: %-10s | Time: %s%n", row[0], row[1]);
        }
    }

    /***
     * organization
     * @param msg message to print
     */
    public void print(String msg){
        System.out.println(msg);
    }

}
