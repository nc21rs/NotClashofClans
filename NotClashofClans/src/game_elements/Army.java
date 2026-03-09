package game_elements;

import java.util.ArrayList;

/**
 * This class represents an army in the game. It manages a collection of army
 * units.
 */
public class Army {
    private ArrayList<ArmyUnit> armyUnits = new ArrayList<>();

    public int getSize() {
        return armyUnits.size();
    }

    public ArrayList<ArmyUnit> getArmyUnits() {
        return armyUnits;
    }

    /**
     * Adds a specified unit to the army
     * 
     * @param unit to be added to army
     */
    public void addUnit(ArmyUnit unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }

        armyUnits.add(unit);
    }
}
