package rec;

import game.GameWindow;

import java.util.Random;
import java.util.Vector;

/**
 * Created by l on 2/25/2017.
 */
public class rec {
    public static double angle = 3;

    public static void main(String[] args) {
        while(true){
            int x = (int) (GameWindow.FRAME_WIDTH/2 + 100 * Math.sin(angle));
            int y = (int) (GameWindow.FRAME_HEIGHT/2 + 100 * Math.cos(angle));
            angle+= 0.1;
            System.out.println(x + " " + y);
        }
    }


}
