package game_engine;

import java.util.List;

/**
 * This class represents the action queue in the game. It manages the actions
 * that are currently being performed by the player.
 */
class ActionTimer {
    public ActionTimer() {
    }

    private ActionType action;
    private long startTime;
    private long timeNeeded;

    public long getTimeLeft(long currentTime) {
        return 0;
    }

    public boolean isFinished() {
        return false;
    }
}

public class ActionQueue {
    List<ActionTimer> actions;

    public void add(ActionTimer act) {
    }

    public void update(long currentTime) {
    }

    public void completeAction(ActionTimer act) {
    }
}
