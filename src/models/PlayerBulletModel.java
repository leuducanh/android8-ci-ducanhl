package models;

/**
 * Created by l on 2/26/2017.
 */
public class PlayerBulletModel extends GameModel{
    public static final int PLAYERBULLET_SPEED = 10;

    private boolean collision;
    private int bulletType;

    private boolean visible;
    public PlayerBulletModel(int x, int y, int width, int height,int bulletType) {
        super(x, y, width, height);

        this.bulletType = bulletType;
        collision = false;
        visible = true;
    }

    public boolean isCollision() {
        return collision;
    }

    public int getBulletType() {
        return bulletType;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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

    public void increaseWidth(){
        this.width += 10;
    }

    public void increaseHeight(){
        this.height += 10;
    }

    @Override
    public void collisionHandler(GameModel otherGameModel) {
        if(otherGameModel instanceof EnemyPlaneModel){
            collision = true;
        }
    }

    public void fly(){
        y -= PLAYERBULLET_SPEED;
    }

    public void moveRightUp(){
        y -= PLAYERBULLET_SPEED;
        x += PLAYERBULLET_SPEED;
    }

    public void moveLeftUp(){
        y -= PLAYERBULLET_SPEED;
        x -= PLAYERBULLET_SPEED;
    }
}
