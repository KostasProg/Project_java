package rpg;

import javax.swing.*;
import java.awt.*;

public class Warrior extends Entity {


    public Warrior(int level, String name, Image img) {
        super(level, name, img);
        setStregth(getStregth()+1);
    }
    public Warrior(int level, String name, Image img,Boolean hostile) {
        super(level, name, img,hostile);
        setStregth(getStregth()+1);
    }

    @Override
    public Image attack_effect() {
        return new ImageIcon("resources\\slash.png").getImage();
    }


}
