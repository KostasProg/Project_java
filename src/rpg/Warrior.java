package rpg;

import java.awt.*;

public class Warrior extends Entity {


    public Warrior(int level, String name, Image img) {
        super(level, name, img);
        setStregth(getStregth()+1);
    }


}
