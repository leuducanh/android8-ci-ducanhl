package models;

/**
 * Created by l on 2/26/2017.
 */
public class PlayerBulletModel extends GameModel{
    public static final int PLAYERBULLET_SPEED = 10;


    public PlayerBulletModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void fly(){
        y -= PLAYERBULLET_SPEED;
    }
}
