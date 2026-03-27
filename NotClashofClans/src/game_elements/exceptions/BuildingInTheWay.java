package game_elements.exceptions;

//================================ Custom Exceptions =================================================================//
public class BuildingInTheWay extends Exception {
    public BuildingInTheWay(String buildingName) {
        super("Error: Cannot build here, " + buildingName + " is in the way");
    }
}
