package rpg;

import javax.swing.*;
import java.awt.*;

abstract public class Entity {
    private String id;
    private int level, health_points, attack, defense;
    private int stregth;
    private int vitality;
    private String name;
    int x, y;//Kartesios style
    private Image img;
    private Boolean hostile;

    public Entity(int level, String name, Image img) {
        this.level=level;
        this.stregth = level * 2;
        attack = stregth * 2;
        health_points = vitality * 2 + 10;
        defense = stregth * 1 + vitality / 2;
        this.vitality = level * 2;
        this.name = name;
        this.img = img;
        hostile=false;
    }
    public Entity(int level, String name, Image img,Boolean hostile) {
        this.level=level;
        this.stregth = level * 2;
        attack = stregth * 2;
        health_points = vitality * 2 + 10;
        defense = stregth * 1 + vitality / 2;
        this.vitality = level * 2;
        this.name = name;
        this.img = img;
        this.hostile=hostile;
    }
    public abstract Image attack_effect();
    public boolean hostile(){return this.hostile;}

    public int getStregth() {
        return stregth;
    }

    public int getVitality() {
        return vitality;
    }

    public void setStregth(int stregth) {
        this.stregth = stregth;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getHealth_points() {
        return health_points;
    }

    public int getAttack() {
        return attack;
    }

    public String getName() {
        return name;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImg() {
        return img;
    }

    public void die() {
        this.img = new ImageIcon("resources\\dead.png").getImage();
    }

    public void battle(Entity e1, Entity e2) {
        System.out.println("Battle");
        if (e1.health_points > 0 && e2.health_points > 0) {

            e1.health_points =e1.health_points - e2.attack + e1.defense;
            System.out.println(e1.health_points+"healt1");
            e2.health_points = e2.health_points -e1.attack + e2.defense;
            System.out.println(e2.health_points+"healt1");


        }
        if (e1.health_points < 0) {
            e1.die();
        }
        if (e2.health_points < 0) {
            e2.die();
        }

    }

}
