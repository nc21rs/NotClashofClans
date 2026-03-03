package villageelements;

/**
    "Enums are a special type of class" -W3S
    > Has static method for obtaining list of items
    > No instantiation
 */
public enum ResourceType {
    FOOD(0),WOOD(1),IRON(2),GOLD(3);

    private int index;  //constructor (but its special)
    ResourceType(int index){
        this.index = index;
    }

    public int getIndex() { //static get method
        return index;
    }
}



