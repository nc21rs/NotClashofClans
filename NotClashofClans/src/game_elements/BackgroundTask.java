package game_elements;

import game_elements.inhabitant.villager.Builder;

/**
 * Class holds data on all background tasks. This object is to be implemented with a thread. Thread will manage all bg tasks.
 * @param <T> any generic type which is a subclasses of upgradeable. This just so happens to only be Buildings and Inhabitants.
 */
public class BackgroundTask <T extends Upgradeable>{
    private long endTime;
    private Upgradeable target; //can either be a building/army/worker/resourceVillager
    private boolean isUpgrade;
    public BackgroundTask(Upgradeable target, long duration, boolean isUpgrade) {
        this.target = target;
        this.endTime = System.currentTimeMillis() + duration;
        this.isUpgrade = isUpgrade;
    }

    /**
     * This method will update the building status (isDestroyed = false), when found to be to has finished Construction.
     * Should the object
     * @return
     */
    public boolean performTask(VillageModel village) {
        boolean done = System.currentTimeMillis()>=this.endTime;
        if(done) { //if the building is in fact complete, also take the time to update its status
            if(!isUpgrade){//Making a new instance
                if (target instanceof Building) { //if building, set to destroyed to false. (our indicator for building)
                    Building building = (Building) target;
                    building.setDestroyed(false);
                } else if (target instanceof ArmyUnit) {//add to unit to army
                    ArmyUnit armyUnit = (ArmyUnit) target;
                    village.getArmy().addUnit(armyUnit);
                } else if (target instanceof ResourceVillager) {
                    village.setNumWorkers(village.getNumWorkers()+1);
                } else {//other assume is a builder
                    village.setNumBuilders(village.getNumBuilders()+1);
                }
            } else { //otherwise upgrade

            }
        }
        return done;
    }
}
