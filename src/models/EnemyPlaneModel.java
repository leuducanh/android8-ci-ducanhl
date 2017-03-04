package models;

/**
 * Created by l on 2/27/2017.
 */
public class EnemyPlaneModel extends GameModel{
    public static final int ENEMYPLANE_SPEED = 5;
    public static final long TIME_TO_HAVE_POWERUP = 4000;
    public static final long LAST_TIME_POWERUP = System.currentTimeMillis();

    private int enemyPlaneType;
    private boolean destroy;
    private boolean invisible;
    private int secondToshot;
    private long lastTimeShot;

    private boolean havePowerUp;
    private int powerUpType;
    public EnemyPlaneModel(int x, int y, int width, int height,boolean havePowerUp,int enemyPlaneType) {
        super(x, y, width, height);
        this.destroy = false;
        this.invisible = true;
        this.enemyPlaneType = enemyPlaneType;
        this.havePowerUp = havePowerUp;
        if(havePowerUp){
            powerUpType = 1;
        }
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

    public boolean isHavePowerUp() {
        return havePowerUp;
    }

    public int getPowerUpType() {
        return powerUpType;
    }

    public void setHavePowerUp(boolean havePowerUp) {
        this.havePowerUp = havePowerUp;
    }

    public int getEnemyPlaneType() {
        return enemyPlaneType;
    }

    public void setPowerUpType(int powerUpType) {
        this.powerUpType = powerUpType;
    }

}
