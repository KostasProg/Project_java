import rpg.Entity;
import rpg.Warrior;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

public class Game extends Canvas implements Runnable {
    private Thread thread;
    private int i, j;
    private boolean running;
    private Image dbImage;
    private Image warrior, grass, wall, barbarian;
    private World world;
    private char[][] cworld;
    private int px, py;//Player position
    private int dx, dy;
    private int maxX, maxY;
    Warrior player;
    private Vector<Entity> npcs;
     private int last_i, last_j;//battle go back
    private String playerName;

    public Game(Dimension size,String playerName) {
        this.playerName=playerName;
        dx = 0;
        dy = 0;
        world = new World(new File("src\\Level_2.txt"), new File("src\\Level_2_npcs.txt"));
        npcs = world.getNpcs();
        cworld = world.getWorld();
        maxX = world.getLenx();
        maxY = world.getLeny();

        loadRes();
        loadPlayers();

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
                    last_i = -1;
                    last_j = 0;
                    if (collision(cworld[px - 1 + dx][py + dy]) && dx > 1 && px == 3) {
                        dx--;

                    } else if (collision(cworld[px - 1 + dx][py + dy])) {
                        i -= 100;

                    }

                }
                if (id == KeyEvent.VK_D && i < 900) {
                    last_i = 1;
                    last_j = 0;
                    if (dx + 10 < maxX && px == 7 && collision(cworld[px + 1 + dx][py + dy])) {
                        dx++;

                    } else if (collision(cworld[px + 1 + dx][py + dy])) {
                        i += 100;

                    }

                }
                if (id == KeyEvent.VK_W && j > 0) {
                    last_i = 0;
                    last_j = -1;
                    if (collision(cworld[px + dx][py + dy - 1]) && dy > 1 && py == 3) {
                        dy--;

                    } else if (collision(cworld[px + dx][py - 1 + dy])) {
                        j -= 100;

                    }
                }
                if (id == KeyEvent.VK_S && j < 800) {
                    last_i = 0;
                    last_j = 1;
                    if (collision(cworld[px + dx][py + 1 + dy]) && dy + 9 < maxY && py == 6) {
                        dy++;

                    } else if (collision(cworld[px + dx][py + 1 + dy])) {
                        j += 100;

                    }
                }

                //collision_npc();
                repaint();

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public boolean collision(char tile) {

        for (Entity e : npcs) {
            if (px+dx+last_i  == e.getX()  && py +dy+last_j  == e.getY()  ) {
                System.out.println("bro"+last_j+last_i);

                i-=last_i*200;
                j-=last_j*200;
                px=i/100;
                py=j/100;
                if(cworld[px+dx-last_i*2][py+dy-last_i*2]=='R'){
                    px=i/100;
                    py=j/100;
                    i+=last_i*200;
                    j+=last_j*200;
                    System.out.println("here");
                }
                if(e.hostile())
                {player.battle(player, e);
                /*battle(player,e)*/;

                }

                return false;


            }
        }
        return !(tile == 'R') ;//false gia na xalaei thn if
    }


    public void loadRes() {
        warrior = new ImageIcon("resources\\Warrior.png").getImage();
        grass = new ImageIcon("resources\\Grass.png").getImage();
        wall = new ImageIcon("resources\\Wall.png").getImage();
        barbarian = new ImageIcon("resources\\Barbarian_1.png").getImage();
    }

    public void loadPlayers() {
        player = new Warrior(300, playerName, warrior);


    }
    public void battle(Entity e1,Entity e2){
        Graphics g= this.getGraphics();
        i=5;

        g.drawImage(new ImageIcon("resources\\slash.png").getImage(),0,0,null);




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


            }
        }
        for (Entity e : npcs) {
            g.drawImage(e.getImg(), e.getX() * 100 - dx * 100, e.getY() * 100 - dy * 100, this);
        }


    }

    public void doubleFistin() {
        dbImage = this.createImage(1200, 1000);
        Graphics bg = dbImage.getGraphics();
        generateWorld(bg);

        bg.drawRect(i, j, 100, 100);
        bg.drawImage(player.getImg(), i, j, this);
        bg.setColor(Color.red);
        bg.drawImage(new ImageIcon("resources\\Menu2.png").getImage(),1000,0,this);

    }


    @Override
    public void paint(Graphics g) {
        doubleFistin();

        g.drawImage(dbImage, 0, 0, this);


    }
}
