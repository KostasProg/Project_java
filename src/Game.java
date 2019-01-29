import rpg.Entity;
import rpg.Warrior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class Game extends Canvas implements Runnable {
    Thread thread;
    int i, j;
    boolean running;
    Image dbImage;
    Image warrior, grass, wall, barbarian;
    World world;
    char[][] cworld;
    String[][] enemies;
    int px, py;//Player position
    int dx, dy;
    int maxX, maxY;
    Warrior player, barb1;

    public Game() {

        dx = 0;
        dy = 0;
        world = new World(new File("C:\\Users\\Anonymous\\IdeaProjects\\Game\\src\\Level_2.txt"));
        cworld = world.getWorld();
        maxX = world.getLenx();
        maxY = world.getLeny();

        loadRes();
        loadPlayers();
        Dimension size = new Dimension(1000, 900);
        setSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setFocusable(true);
        thread = new Thread(this);
        running = false;
        i = 200;
        j = 200;
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                px = i / 100;
                py = j / 100;

                //System.out.println("MaxX-" + maxX + "\nMaxY-" + maxY + "\npx-" + px + "\npy-" + py + "\ndx" + dx);
                repaint();
                int id = e.getKeyCode();
                repaint();

                if (id == KeyEvent.VK_A && i > 0) {
                    if (collision(cworld[px - 1 + dx][py + dy]) && dx > 1 && px == 3) {
                        dx--;
                    } else if (collision(cworld[px - 1 + dx][py + dy])) {
                        i -= 100;
                    }

                }
                if (id == KeyEvent.VK_D && i < 900) {
                    if (dx + 10 < maxX && px == 7 && collision(cworld[px + 1 + dx][py + dy])) {
                        dx++;
                    } else if (collision(cworld[px + 1 + dx][py + dy])) {
                        i += 100;
                    }

                }
                if (id == KeyEvent.VK_W && j > 0) {
                    if (collision(cworld[px + dx][py + dy - 1]) && dy > 1 && py == 3) {
                        dy--;
                    } else if (collision(cworld[px + dx][py - 1 + dy])) {
                        j -= 100;
                    }
                }
                if (id == KeyEvent.VK_S && j < 800) {
                    if (collision(cworld[px + dx][py + 1 + dy]) && dy + 9 < maxY && py == 6) {
                        dy++;
                    } else if (collision(cworld[px + dx][py + 1 + dy])) {
                        j += 100;
                    }
                }

                collision_enemy();
                repaint();

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public boolean collision(char tile) {
        return !(tile == 'R');//false gia na xalaei thn if
    }

    public void collision_enemy() {
        if(px==barb1.getX() && py==barb1.getY()){
            player.battle(player,barb1);
        }
    }

    public void loadRes() {
        warrior = new ImageIcon("C:\\Users\\Anonymous\\IdeaProjects\\Game\\resources\\Warrior.png").getImage();
        grass = new ImageIcon("C:\\Users\\Anonymous\\IdeaProjects\\Game\\resources\\Grass.png").getImage();
        wall = new ImageIcon("C:\\Users\\Anonymous\\IdeaProjects\\Game\\resources\\Wall.png").getImage();
        barbarian = new ImageIcon("C:\\Users\\Anonymous\\IdeaProjects\\Game\\resources\\Barbarian_1.png").getImage();
    }

    public void loadPlayers() {
        player = new Warrior(300, "MotherFucker", warrior);
        barb1 = new Warrior(1, "simpleFucker", barbarian);
        barb1.setPosition(4, 5);
        enemies = new String[maxX][maxY];
        for (int f = 0; f < maxX; f++) {
            for (int n = 0; n < maxY; n++) {
                enemies[f][n] = "0";
            }
        }
        enemies[barb1.getX()][barb1.getY()] = "b";
    }


    @Override
    public void run() {


    }

    public void generateWorld(Graphics g) {

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 9; y++) {
                switch (cworld[x + dx][y + dy]) {

                    case 'G':

                        g.drawImage(grass, x * 100, y * 100, this);
                        break;
                    case 'R':
                        g.drawImage(wall, x * 100, y * 100, this);
                        break;

                }
                switch (enemies[x + dx][y + dy]) {

                    case "b":
                        g.drawImage(barbarian, barb1.getX() * 100 - dx * 100, barb1.getY() * 100 - dy * 100, this);
                        break;


                }


            }
        }
    }

    public void doubleFistin() {
        dbImage = this.createImage(1000, 900);
        Graphics bg = dbImage.getGraphics();
        generateWorld(bg);

        bg.drawRect(i, j, 100, 100);
        bg.drawImage(player.getImg(), i, j, this);
        bg.setColor(Color.red);

    }

    @Override
    public void paint(Graphics g) {
        doubleFistin();

        g.drawImage(dbImage, 0, 0, this);


    }
}
