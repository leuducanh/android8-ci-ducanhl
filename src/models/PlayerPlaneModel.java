package models;

import game.GameWindow;

/**
 * Created by l on 2/27/2017.
 */
public class PlayerPlaneModel {

    public static final int SPEED = 10;
    private int x;
    private int y;
    private int width;
    private int height;

    public PlayerPlaneModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
