package models;

/**
 * Created by l on 2/27/2017.
 */
public class EnemyBulletModel extends GameModel{
    public static final int ENEMYBULLET_SPEED = 7;

    public EnemyBulletModel(int x, int y, int width, int height) {
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

    public void move(){
        y += ENEMYBULLET_SPEED;
    }
}
