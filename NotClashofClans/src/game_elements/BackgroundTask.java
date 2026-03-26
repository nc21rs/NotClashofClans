package game_elements;

/**
 * Class holds data on all background tasks. This object is to be implemented with a thread. Thread will manage all bg tasks.
 * @param <T> any generic type which is a subclasses of upgradeable. This just so happens to only be Buildings and Inhabitants.
 */
public class BackgroundTask <T extends Upgradeable>{
    private long endTime;
    private Upgradeable target; //can either be a building or inhabitant(army unit)
    public BackgroundTask(Upgradeable target, long duration) {
        this.target = target;
        this.endTime = System.currentTimeMillis() + duration;    //
    }

    /**
     * This method will update the building status (isDestroyed = false), when found to be to has finished Construction.
     * Should the object
     * @return
     */
    public boolean isFinished() {
        boolean isDone = System.currentTimeMillis()>=this.endTime;
        if(isDone) { //if the building is in fact complete, also take the time to update its status
            if(target instanceof Building) { //check if target is a building. If soo, update destroyed setting.
                Building building = (Building) target;
                building.setDestroyed(false);
            }
        }
        return isDone;
    }
}
