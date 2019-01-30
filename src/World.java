import rpg.Entity;

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
    private WorldLoader damnBro;
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
        damnBro = new WorldLoader();
        //metatroph se pinaka char gia tis syntetagmenes
        toDecart();
        //
        System.out.println("World Loaded");


    }

    public World(File level,File level_npcs) {
        this.level = level;
        damnBro = new WorldLoader(level,level_npcs);
        toDecart();
        System.out.println("World Loaded");
    }

    private void toDecart() {
        //length

        sworld = damnBro.extract();
        sent=damnBro.extract_npcs();
        //arxikopoiountai meta to extract
        lenx = damnBro.getI();
        System.out.println("what" + lenx);
        leny = damnBro.getJ();
        System.out.println("what" + leny);
        //System.out.println(sworld);
        //System.out.println(damnBro.getI()+damnBro.getJ()+"");

        world = new char[damnBro.getI()][damnBro.getJ()];
        npcs_coor =  new String[damnBro.getI()][damnBro.getJ()];
        npcs=new Vector<>();
        //WORLD INTIT
        Scanner decart = new Scanner(sworld);
        int y = 0;
        System.out.println(decart.nextLine());
        while (decart.hasNextLine()) {

            String x = decart.nextLine();
            x = x.replaceAll("-", "");
            System.out.println("->" + x);
            for (int z = 0; z < damnBro.getI(); z++) {
                //System.out.println(z);
                // if(lenx==10 && leny==9){world[z][y] = x.charAt(z);}
                world[z][y] = x.charAt(z);

            }
            y++;
        }
        //ENTITIES INIT




    }

    public char[][] getWorld() {
        return world;
    }
    public char[][] getNpcs() {
        return world;
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
                    x=x+scanner.nextLine();

                }
                System.out.println(x);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return x;
        }
    }
}
