package models;

/**
 * Created by l on 2/27/2017.
 */
public class EnemyPlaneModel {
    public static final int ENEMYPLANE_SPEED = 5;

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean destroy;
    private boolean invisible;
    private int secondToshot;
    private long lastTimeShot;

    public EnemyPlaneModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.destroy = false;
        this.invisible = true;
        secondToshot = 1;
        lastTimeShot = System.currentTimeMillis();
    }

    public static int getEnemyplaneSpeed() {
        return ENEMYPLANE_SPEED;
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

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }

    public boolean getDestroy() {
        return destroy;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public void move(){
        y += ENEMYPLANE_SPEED;
    }

    public int getSecondToshot() {
        return secondToshot;
    }

    public long getLastTimeShot() {
        return lastTimeShot;
    }

    public void setLastTimeShot(long lastTimeShot) {
        this.lastTimeShot = lastTimeShot;
    }
}
