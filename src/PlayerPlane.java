import java.awt.*;
import java.util.Vector;

/**
 * Created by l on 2/25/2017.
 */
public class PlayerPlane {

    public int x,y;
    public Image image = ImageLoader.loadImageFromRes("plane4.png");
    public int speed;

    PlayerPlane(){

    }

    PlayerPlane(int x,int y,int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
}
