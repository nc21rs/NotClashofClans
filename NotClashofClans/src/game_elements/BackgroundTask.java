package game_elements;

/**
 * Class holds data on all background tasks. This object is to be implemented with a thread. Thread will manage all bg tasks.
 */
public class BackgroundTask {
    private long startTime;
    private long endTime;
    public BackgroundTask(long startTime, long duration) {
        this.startTime = startTime;
        this.endTime = startTime + duration;    //
    }
    public boolean isFinished() {
        return System.currentTimeMillis()>=this.endTime;
    }
}
