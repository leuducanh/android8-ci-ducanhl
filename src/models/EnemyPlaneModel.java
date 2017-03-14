package models;

import game.GameWindow;
import utils.CustomRandom;

/**
 * Created by l on 2/27/2017.
 */
public class EnemyPlaneModel extends GameModel{
    public static final int ENEMYPLANE_SPEED = 5;

    public EnemyPlaneModel(int x, int y, int width, int height) {
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

    @Override
    public void collisionHandler(GameModel otherGameModel) {
        if(otherGameModel instanceof PlayerBulletModel){
            destroy = true;
        }
    }


    public boolean getDestroy() {
        return destroy;
    }


    public void move(){
        y += ENEMYPLANE_SPEED;
    }

    public void moveLeft(){
        x -= ENEMYPLANE_SPEED;
    }

    public void moveRight(){
        x += ENEMYPLANE_SPEED;
    }

    public void moveUp(){
        y -= ENEMYPLANE_SPEED;
    }

    public void moveDown(){
        y += ENEMYPLANE_SPEED;
    }

    public void moveDownRight(){
        y += ENEMYPLANE_SPEED;
        x += ENEMYPLANE_SPEED;
    }

    public void moveDownLeft(){
        y += ENEMYPLANE_SPEED;
        x -= ENEMYPLANE_SPEED;
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

//    public boolean checkCoordinatePlane(){
//        if((x >= GameWindow.FRAME_WIDTH - width/2) || (x <= -height/2) || (y > limitY) || (y < height)) {
//            return false;
//        }
//        return true;
//    }

}
