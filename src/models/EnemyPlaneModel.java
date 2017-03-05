package models;

import game.GameWindow;
import utils.CustomRandom;

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

    private int specialMove;
    private int typeRandomMove;
    private int limitY;

    private boolean flag;
    private boolean moveChange;

    public EnemyPlaneModel(int x, int y, int width, int height,boolean havePowerUp,int enemyPlaneType,int limitY,int specialMove) {
        super(x, y, width, height);
        this.destroy = false;
        this.invisible = true;
        this.enemyPlaneType = enemyPlaneType;
        this.havePowerUp = havePowerUp;
        this.limitY = limitY;
        this.specialMove = specialMove;
        if(havePowerUp){
            powerUpType = 1;
        }
        secondToshot = 1;
        lastTimeShot = System.currentTimeMillis();
        flag = true;
        moveChange = false;
    }

    public boolean isMoveChange() {
        return moveChange;
    }

    public void setMoveChange(boolean moveChange) {
        this.moveChange = moveChange;
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

    @Override
    public void collisionHandler(GameModel otherGameModel) {
        if(otherGameModel instanceof PlayerBulletModel){
            destroy = true;
        }
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

    public int getSpecialMove() {
        return specialMove;
    }

    public int getLimitY() {
        return limitY;
    }

    public void setPowerUpType(int powerUpType) {
        this.powerUpType = powerUpType;
    }
    public void moveLeft(){
        x -= ENEMYPLANE_SPEED;
        if(!checkCoordinatePlane()){
            x += ENEMYPLANE_SPEED;
            flag = true;
        }
    }

    public void moveRight(){
        x += ENEMYPLANE_SPEED;
        if(!checkCoordinatePlane()){
            x -= ENEMYPLANE_SPEED;
            flag = true;
        }
    }

    public void moveUp(){
        y -= ENEMYPLANE_SPEED;
        if(!checkCoordinatePlane()){
            y += ENEMYPLANE_SPEED;
            flag = true;
        }
    }

    public void moveDown(){
        y += ENEMYPLANE_SPEED;
        if(!checkCoordinatePlane()){
            y -= ENEMYPLANE_SPEED;
            flag = true;
        }
    }


    public void increaseWidth(){
        this.width += 3;
    }
    public void increaseHeight(){
        this.height += 3;
    }
    public void moveCircle(int r){

        double speedScale = (0.001*2*Math.PI)/ENEMYPLANE_SPEED;
        double angle  = System.currentTimeMillis()*speedScale;

        int x1 = (int) (r * Math.sin(ENEMYPLANE_SPEED * angle));
        int y1 = (int) (r * Math.cos(ENEMYPLANE_SPEED * angle));

        x += x1;
        y += y1;
    }
    public void moveRandom(){
        if(flag){
            flag = false;
            typeRandomMove = CustomRandom.customNextInt(4);
        }else{
            switch (typeRandomMove){
                case(0):{
                    moveLeft();
                    break;
                }
                case(1):{
                    moveRight();
                    break;
                }
                case(2):{
                    moveUp();
                    break;
                }
                case(3):{
                    moveDown();
                    break;
                }
            }
        }
    }
    public boolean checkCoordinatePlane(){
        if((x >= GameWindow.FRAME_WIDTH - width/2) || (x <= -height/2) || (y > limitY) || (y < height)) {
            return false;
        }
        return true;
    }

}
