package game_elements;

import java.util.ArrayList;

public class Army {
    private ArrayList<ArmyUnit> armyUnits = new ArrayList<>();

    /*
     * Returns the number of units in the army
     *
     * @return the size of the army
     */
    public int getSize() {
        return armyUnits.size();
    }

    /*
     * Returns the list of all units in the army
     *
     * @return the list army units
     */
    public ArrayList<ArmyUnit> getArmyUnits() {
        return armyUnits;
    }

    /*
    * Adds unit to the army
    *   
    * @param unit the unit to be added to army
    */
    public void addUnit(ArmyUnit unit){
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }

        armyUnits.add(unit);
    }
}
