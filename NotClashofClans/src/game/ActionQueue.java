package game;

import java.util.List;

class ActionTimer{
    public ActionTimer(){}
    private ActionType action;
    private long startTime;
    private long timeNeeded;

    public long getTimeLeft(long currentTime){return 0;}
    public boolean isFinished(){return false;}
}

public class ActionQueue {
    List<ActionTimer> actions;

    public void add(ActionTimer act){}
    public void update(long currentTime){}
    public void completeAction(ActionTimer act){}
}
