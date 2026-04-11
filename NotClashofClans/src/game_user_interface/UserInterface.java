package game_user_interface;

import game_elements.ResourceType;
import game_elements.Village;
import game_elements.VillageControl;
import game_engine.ActionType;
//import game_engine.GameEngine;

import java.util.*;
import java.util.stream.IntStream;

/**
 * This class represents the user interface of the game. It is responsible for
 * displaying
 * the game state to the user and getting user input for actions. It
 * communicates with
 * GameEngine to send user action requests and receive updates on the game
 * state.
 */
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
    public void displayMap(VillageControl village) {
        System.out.println("Map size is " + width + "x" + height);
        System.out.println("===== Map =====");

        // Print a grid of dots representing the map using streams
        IntStream.range(0, height).forEach(row -> {
            IntStream.range(0, width).forEach(col -> {
                if (village.getBuilding(row, col) != null) {
                    System.out.print(village.getBuilding(row, col).getShortName() + " ");
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
    public void displayVillage(VillageControl village) {
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
     * Method to display the village getResources.
     *
     * @param village the Village whose getResources will be displayed
     */
    public void displayResources(VillageControl village) {
        if (village == null) {
            System.out.println("No village provided.");
            return;
        }

        System.out.println("\n===== RESOURCES =====");
        // prints each resource individually.
        System.out.printf("%d Food\n", village.getResources().getResource(ResourceType.FOOD));
        System.out.printf("%d Wood\n", village.getResources().getResource(ResourceType.WOOD));
        System.out.printf("%d Iron\n", village.getResources().getResource(ResourceType.IRON));
        System.out.printf("%d Gold\n", village.getResources().getResource(ResourceType.GOLD));
//        System.out.printf("%d Population\n", village.getResourceAmount(ResourceType.POPULATION));
        System.out.println("=====================\n");
    }

    /**
     * Prompts the user for X and Y coordinates and returns them as an array.
     *
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
                if (x < 0 || x >= maxX)
                    System.out.println("---- Out of bounds ----");
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
                if (y < 0 || y >= maxY)
                    System.out.println("---- Out of bounds ----");
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
    public void printTask() {
        System.out.println("\n===== Upgrades: =====");
    }

    public void printTaskStrings(List<String[]> data) {
        for (String[] row : data) {
            System.out.printf("Building: %-10s | Time: %s%n", row[0], row[1]);
        }
    }

    /***
     * organization
     *
     * @param msg message to print
     */
    public void print(String msg) {
        System.out.println(msg);
    }

    public String getBuildingChoice() {
        print("\n===== Building Options =====");
        print("1. Farm");
        print("2. Lumber Mill");
        print("3. Iron Mine");
        print("4. Gold Mine");
        print("5. Cannon");
        print("6. Archer Tower");

        int userChoice = scanner.nextInt();

        switch (userChoice) {
            case 1:
                return "TOWNHALL";
            case 2:
                return "CANNON";
            case 3:
                return "ARCHERTOWER";
            case 4:
                return "FARM";
            case 5:
                return "LUMBERMILL";
            case 6:
                return "IRONMINE";
            case 7:
                return "GOLDMINE";
            default:
                return null;
        }
    }

    //=======================================================================Section Contains updated User Interaction
    /*

    /BUILD DEFENCE CANNON 1,1
    /BUILD RESOURCE GOLDMINE 1,2

    /TRAIN ARMY ARCHER
    /TRAIN VILLAGER COLLECTOR

    /UPGRADE BUILD 1,1
    /UPGRADE ARMY ARCHER

    /EXPLORE
    /NEXT
    /ATTACK

    /QUIT

    COMMAND LINE FLOWCHART
    0. LIST
    1. BUILD
        a. DEFENSE
        b. RESOURCE
    2. TRAIN
        a. ARMY
        b. VILLAGER
    3. UPGRADE
        a. BUILDING
        b. ARMY
    4. EXPLORE
        a. NEXT
        b. ATTACK
    5. QUIT
     */

//    /**
//     * verify that the provided request is what the application is expecting.
//     * assuming given request is such
//     *
//     * @param request the user's request
//     * @return isValid
//     */
//    public boolean validateRequest(String request) {
//        //Strictly removes the '/' then checks the first word
//        StringTokenizer tokenizer = new StringTokenizer(request);
//        String action = tokenizer.nextToken().toUpperCase();  //  {LIST, BUILD, TRAIN, UPGRADE, EXPLORE, QUIT}
//        action = action.substring(1);
//        switch (action) {
//            case "LIST", "BUILD", "TRAIN", "UPGRADE", "EXPLORE", "QUIT":
//                return true;
//            default:
//                return false;
//        }
//
//    }


    public String showCommands(){
        return "Commands:" +
                "/LIST" +
                "/BUILD <structure> <x,y>" +
                "/TRAIN <entity>" +
                "/UPGRADE_BUILD <x,y>" +
                "/UPGRADE_UNIT <unit_name>" +
                "/EXPLORE" +
                "/QUIT";
    }
    /**
     * check if the provided user input is a request to the server
     *
     * @param request String user input
     * @return isRequest
     */
    public boolean isRequest(String request) {
        //strictly check if not empty, THEN validate if isRequest
        return request.length() > 1 && request.charAt(0) == '/';
    }

    //transform user's request in an action
    public ActionType processRequest(String request) {
        if (!isRequest(request))
            return ActionType.NULL;
//        if (!validateRequest(request))
//            return ActionType.NOT_ACTION;
        /*
        tokenize the request. Only analyze the first token with primary request
         */
        StringTokenizer tokenizer = new StringTokenizer(request.substring(1));
        String action = tokenizer.nextToken().toUpperCase();
        switch (action) {
            case "LIST":
                return ActionType.LIST;
            case "BUILD":
                return ActionType.BUILD;
            case "TRAIN":
                return ActionType.TRAIN;
            case "UPGRADE":
                return ActionType.UPGRADE;
            case "EXPLORE":
                return ActionType.EXPLORE;
            case "QUIT":
                return ActionType.QUIT;
            default:
                return ActionType.NOT_ACTION;
        }
    }

    public boolean validateStructure(){
        return false;
    }

    public boolean validateUpgrade(){
        return false;
    }


    public String outputMap(VillageControl village) {
        String output = "";
        output += "Map size is " + width + "x" + height+"\n";
        output += "===== Map =====\n";
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                if (village.getBuilding(row,col) != null)
                    output += village.getBuilding(row, col).getShortName() + " ";
                else
                    output += " ";
            }
            output += "\n";
        }
        output += "===============\n";
        return output;
    }

    public String outputResources(VillageControl village) {
        if (village == null){
            return "No village selected\n";
        }

        String output = "";
        output = output.concat(String.format("%d Food\n",village.getResources().getResource(ResourceType.FOOD)));
        output = output.concat(String.format("%d Wood\n", village.getResources().getResource(ResourceType.WOOD)));
        output = output.concat(String.format("%d Iron\n", village.getResources().getResource(ResourceType.IRON)));
        output = output.concat(String.format("%d Gold\n", village.getResources().getResource(ResourceType.GOLD)));
        output = output.concat("=====================\n");
        return output;
    }

    public boolean validateCoords(int x, int y, VillageControl village) {
        if(x < 0 || x>= width)
            return false;
        if(y < 0 || y >= height)
            return false;
        if(village.getBuilding(x, y) != null)
            return false;
        return true;
    }




}