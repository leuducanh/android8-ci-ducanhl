package models;

import game.GameWindow;
import utils.Calculate;
import utils.CustomRandom;

/**
 * Created by l on 3/4/2017.
 */
public class PowerUpModel extends GameModel {
    public static int POWER_UP_SPEED = 5;
    private int powerUpType;
    private boolean runForYourLife;

    private int typeMove;
    private boolean flag;

    private boolean invisible;

    public PowerUpModel(int x, int y, int width, int height,int powerUpType) {
        super(x, y, width, height);

//        powerUpType = CustomRandom.customNextInt(2);
        powerUpType = 0;
        runForYourLife = false;

        this.powerUpType = powerUpType;
        typeMove = CustomRandom.customNextInt(4);
        flag = false;
        invisible = true;
    }

    public void moveLeft(){
        x -= POWER_UP_SPEED;
        if(!checkCoordinatePlane()){
            x += POWER_UP_SPEED;
        }
    }

    public void moveRight(){
        x += POWER_UP_SPEED;
        if(!checkCoordinatePlane()){
            x -= POWER_UP_SPEED;
        }
    }

    public void moveUp(){
        y -= POWER_UP_SPEED;
        if(!checkCoordinatePlane()){
            y += POWER_UP_SPEED;
        }
    }

    public void moveDown(){
        y += POWER_UP_SPEED;
        if(!checkCoordinatePlane()){
            y -= POWER_UP_SPEED;
        }
    }

    public void moveCrossLeftDown(){
        y += POWER_UP_SPEED;
        x -= POWER_UP_SPEED;
        if(!checkCoordinatePlane()){
            y -= POWER_UP_SPEED;
            x += POWER_UP_SPEED;
            flag = true;
        }
    }

    public void moveCrossRightDown(){
        y += POWER_UP_SPEED;
        x += POWER_UP_SPEED;
        if(!checkCoordinatePlane()){
            y -= POWER_UP_SPEED;
            x -= POWER_UP_SPEED;
            flag = true;

        }
    }

    public void moveCrossLeftUp(){
        y -= POWER_UP_SPEED;
        x -= POWER_UP_SPEED;
        if(!checkCoordinatePlane()){
            y += POWER_UP_SPEED;
            x += POWER_UP_SPEED;
            flag = true;

        }
    }

    public void moveCrossRightUp(){
        y -= POWER_UP_SPEED;
        x += POWER_UP_SPEED;
        if(!checkCoordinatePlane()){
            y += POWER_UP_SPEED;
            x -= POWER_UP_SPEED;
            flag = true;

        }
    }

    public boolean checkCoordinatePlane(){
        if((x >= GameWindow.FRAME_WIDTH - width/2) || (x <= -height/2) || (y > GameWindow.FRAME_HEIGHT - height) || (y < height)) {
            return false;
        }
        return true;
    }

    public void move(int x1,int y1){
        if(runForYourLife){
            switch (typeMove){
                case(0):{
                    moveCrossLeftDown();
                    break;
                }
                case(1):{
                    moveCrossLeftUp();
                    break;
                }
                case(2):{
                    moveCrossRightDown();
                    break;
                }
                case(3):{
                    moveCrossRightUp();
                    break;
                }
            }

            if(flag){
                flag = false;
                typeMove = CustomRandom.customNextInt(4);
            }
        }else{
            if(Calculate.distand(x,y,x1,y1) < 100){
                runForYourLife = true;
            }
        }
    }

    public int getPowerUpType() {
        return powerUpType;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean isInvisible(){
        return invisible;
    }

    @Override
    public void collisionHandler(GameModel otherGameModel) {
        if(otherGameModel instanceof PlayerPlaneModel){
            invisible = false;
        }
    }

}
