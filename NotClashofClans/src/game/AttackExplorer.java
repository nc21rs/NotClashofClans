package game;

import villageelements.Village;

import java.util.Random;

class VillageGenerator{
    private Random random;
    public Village generateVillage(){return new Village();}
}

public class AttackExplorer extends VillageGenerator{
    private Random random;
    public Village showCandidate(){return new Village();}
    public Village reRollCandidate(){return new Village();}
}
