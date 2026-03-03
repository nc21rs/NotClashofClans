package game;

import villageelements.*;

public class GameEngine {
    private long currentTime;

    public boolean canAttack(Army attacker, Village village){return false;}
    public boolean canUpgrade(Upgradeable element, Village village){return false;}
    public boolean canBuild(Building element,Village village){return false;}
    public boolean canTrain(Inhabitant element, Village village){return false;}
    public void updateTime(){}
    public ComputedBattle attack(Army attacker, Village defender){return new ComputedBattle();}
    public ActionTimer update(Upgradeable element, Village village){return new ActionTimer();}
    public ActionTimer build(Upgradeable element, Village village){return new ActionTimer();}
    public ActionTimer train(Inhabitant element, Village village){return new ActionTimer();}
    public Village exploreVillages(){return new Village();}
    public Army generateIncomingArmy(){return new Army();}
}
