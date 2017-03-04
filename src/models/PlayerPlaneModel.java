package models;

import game.GameWindow;

/**
 * Created by l on 2/27/2017.
 */
public class PlayerPlaneModel extends GameModel {

    public static final int SPEED = 10;

    public PlayerPlaneModel(int x, int y, int width, int height) {
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
