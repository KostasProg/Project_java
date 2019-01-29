import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class World {
    //10x9 default
    // R->rock ,G->grass
    private String sworld;
    private char[][] world;
    private WorldLoader damnBro;
    private File level;
    private int lenx,leny;

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

    public World(File level) {
        this.level = level;
        damnBro = new WorldLoader(level);
        toDecart();
        System.out.println("World Loaded");
    }

    private void toDecart() {
        //length

        sworld = damnBro.extract();
        //arxikopoiountai meta to extract
        lenx=damnBro.getI();
        System.out.println("what"+lenx);
        leny=damnBro.getJ();
        System.out.println("what"+leny);
        //System.out.println(sworld);
        //System.out.println(damnBro.getI()+damnBro.getJ()+"");

        world = new char[damnBro.getI()][damnBro.getJ()];
        Scanner decart = new Scanner(sworld);
        int y = 0;
        System.out.println(decart.nextLine());
        while (decart.hasNextLine()) {

            String x = decart.nextLine();
            x=x.replaceAll("-","");
            System.out.println("->" + x);
            for (int z = 0; z < damnBro.getI(); z++) {
                //System.out.println(z);
               // if(lenx==10 && leny==9){world[z][y] = x.charAt(z);}
                world[z][y] = x.charAt(z);

            }
            y++;
        }

    }

    public char[][] getWorld() {
        return world;
    }

    private class WorldLoader {
        private File level;
        private Scanner scanner;
        private int i, j;

        public WorldLoader(File level) {
            this.level = level;
            try {
                scanner = new Scanner(level);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Errorr gamw thn poutana soy");
            }
        }

        public WorldLoader() {
            this.level = new File("C:\\Users\\Anonymous\\IdeaProjects\\Game\\src\\Level_1.txt");
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
    }
}
