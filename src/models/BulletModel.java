package models;

/**
 * Created by l on 3/14/2017.
 */
public class BulletModel extends GameModel {

    public static final int BULLET_SPEED = 10;
    public static final int BULLET_WIDTH = 10;
    public static final int BULLET_HEIGHT = 10;

    public Plane planeFrom;

    public BulletModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
    public BulletModel(int x, int y,Plane planeFrom) {
        super(x, y, BULLET_WIDTH,BULLET_HEIGHT);
        this.planeFrom = planeFrom;
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

    public void moveTopDown(){
        y += BULLET_SPEED;
    }
    public void moveLeftDown(){
        y += BULLET_SPEED;
        x -= BULLET_SPEED;
    }
    public void moveRightDown(){
        y += BULLET_SPEED;
        x += BULLET_SPEED;
    }

    @Override
    public void collisionHandler(GameModel otherGameModel) {

    }

    public static enum Plane{
        ENEMY_PLANE,
        PLAYER_PLANE
    }
}
