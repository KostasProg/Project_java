package rpg;

import java.awt.*;

public class Warrior extends Entity {


    public Warrior(int level, String name, Image img) {
        super(level, name, img);
        setStregth(getStregth()+1);
    }
    @Override //No reason forgot i had one in the motherclass
    public void setPosition(int x,int y){
        super.x=x;
        super.y=y;

    }

}
