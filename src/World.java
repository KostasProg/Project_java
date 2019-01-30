import rpg.Entity;
import rpg.Warrior;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class World {
    //10x9 default
    // R->rock ,G->grass
    private String sworld,sent/*entities*/;
    private char[][] world;
    private String[][] npcs_coor;
    private WorldLoader worldLoader;
    private File level,level_npcs;
    private int lenx, leny;
    private Vector<Entity> npcs;

    public int getLenx() {
        return lenx;
    }

    public int getLeny() {
        return leny;
    }

    public World() {
        worldLoader = new WorldLoader();
        //metatroph se pinaka char gia tis syntetagmenes
        toDecart();
        //
        System.out.println("World Loaded");


    }

    public World(File level,File level_npcs) {
        this.level = level;
        worldLoader = new WorldLoader(level,level_npcs);
        toDecart();
        System.out.println("World Loaded");
    }

    private void toDecart() {
        //length

        sworld = worldLoader.extract();
        sent=worldLoader.extract_npcs();
        //arxikopoiountai meta to extract
        lenx = worldLoader.getI();
        System.out.println("what" + lenx);
        leny = worldLoader.getJ();
        System.out.println("what" + leny);
        //System.out.println(sworld);
        //System.out.println(worldLoader.getI()+worldLoader.getJ()+"");

        world = new char[worldLoader.getI()][worldLoader.getJ()];
        npcs_coor =  new String[worldLoader.getI()][worldLoader.getJ()];
        npcs=new Vector<>();
        //WORLD INTIT
        Scanner decart = new Scanner(sworld);
        int y = 0;
        System.out.println(decart.nextLine());
        while (decart.hasNextLine()) {

            String x = decart.nextLine();
            x = x.replaceAll("-", "");
            System.out.println("->" + x);
            for (int z = 0; z < worldLoader.getI(); z++) {
                //System.out.println(z);
                // if(lenx==10 && leny==9){world[z][y] = x.charAt(z);}
                world[z][y] = x.charAt(z);

            }
            y++;
        }
        //ENTITIES INIT
        decart = new Scanner(sent);

        decart.nextLine();
        while (decart.hasNextLine()) {
        String info=decart.nextLine();
            System.out.println(info);
        String stringx=info.substring(2,3);
            System.out.println(stringx);
        String stringy=info.substring(4,5);
            System.out.println(stringy);
        String type=info.substring(6,7);
            System.out.println(type);
            String level=info.substring(7);
            System.out.println(level);
            switch (type){
                case"b":
                    npcs.addElement(new Warrior(Integer.valueOf(level),"Barbarian",new ImageIcon("resources\\Barbarian_1.png").getImage(),true));
                    npcs.lastElement().setPosition(Integer.valueOf(stringx),Integer.valueOf(stringy));
                    break;
            }
        }



    }

    public char[][] getWorld() {
        return world;
    }

    public Vector<Entity> getNpcs() {
        return npcs;
    }

    private class WorldLoader {
        private File level,level_npcs;
        private Scanner scanner;
        private int i, j;

        public WorldLoader(File level,File level_npcs) {
            this.level = level;
            this.level_npcs=level_npcs;
            try {
                scanner = new Scanner(level);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Errorr gamw thn poutana soy");
            }
        }

        public WorldLoader() {
            this.level = new File("src\\Level_1.txt");
            this.level_npcs=new File("src\\Level_1_npcs.txt");
            try {
                scanner = new Scanner(level);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Errorr gamw thn poutana soy");
            }
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public String extract() {
            String x = "";
            i = 0;
            j = 0;
            try {

                scanner = new Scanner(level);
                while (scanner.hasNextLine()) {
                    x = x + "\n" + scanner.nextLine();
                    if (i == 0) {
                        i = x.lastIndexOf("\n") / 2;
                    }
                    j++;

                    //System.out.println(i);
                }
                //System.out.println(x+"\n");

                i--;
                //  System.out.println("i->" + i + "  \nj->" + j + "\n");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return x;
        }
        public String extract_npcs() {
            String x = "";

            try {
                scanner = new Scanner(level_npcs);
                while (scanner.hasNextLine()) {
                    x=x+"\n"+scanner.nextLine();

                }
                System.out.println(x);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return x;
        }
    }
}
