import javax.swing.*;
import java.awt.*;

public class Main {
    JFrame frame;
    Game game;

    public Main() {
        frame=new JFrame("make this work");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension size=new Dimension(1000,1000);
        frame.setSize(size);
        frame.setMinimumSize(size);
        frame.setMaximumSize(size);
        frame.setResizable(false);
        Game game=new Game();


        frame.add(game);

       // game.run();

        frame.setVisible(true);
    }

    public static void main(String args[]){
        new Main();
        System.out.println("MotherFucker");
       // new World();
    }
}
