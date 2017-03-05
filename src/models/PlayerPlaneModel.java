package models;

import game.GameWindow;

/**
 * Created by l on 2/27/2017.
 */
public class PlayerPlaneModel extends GameModel {

    public static final int SPEED = 17;

    private boolean haveBulletPowerUp;
    private int bulletPowerUpType;

    private boolean haveRocketPowerUp;
    private int powerUpTime;

    private boolean isPowerUp;

    private boolean destroy;
    private boolean visible;
    public PlayerPlaneModel(int x, int y, int width, int height) {
        super(x, y, width, height);

        haveBulletPowerUp = false;

        visible = true;
        destroy = false;

    }

    public boolean isDestroy() {
        return destroy;
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

    public boolean isPowerUp() {
        return isPowerUp;
    }

    public void setPowerUp(boolean powerUp) {
        isPowerUp = powerUp;
    }

    @Override
    public void collisionHandler(GameModel otherGameModel) {
        if(otherGameModel instanceof PowerUpModel){
            if(((PowerUpModel) otherGameModel).getPowerUpType() == 0){
                isPowerUp = true;
                powerUpTime = 10000;
            }
        }
        if(otherGameModel instanceof EnemyBulletModel){
            destroy = true;
        }
    }

    public void decrePowerUpTime(){
        powerUpTime -= 1;
        if(powerUpTime == 0){
            isPowerUp = false;
        }
    }

    public void moveLeft(){
        x -= SPEED;
        if(!checkCoordinatePlane()){
            x += SPEED;
        }
    }

    public void moveRight(){
        x += SPEED;
        if(!checkCoordinatePlane()){
            x -= SPEED;
        }
    }

    public void moveUp(){
        y -= SPEED;
        if(!checkCoordinatePlane()){
            y += SPEED;
        }
    }

    public void moveDown(){
        y += SPEED;
        if(!checkCoordinatePlane()){
            y -= SPEED;
        }
    }

    public boolean checkCoordinatePlane(){
        if((x >= GameWindow.FRAME_WIDTH - width/2) || (x <= -height/2) || (y > GameWindow.FRAME_HEIGHT - height) || (y < height)) {
            return false;
        }
        return true;
    }
}
