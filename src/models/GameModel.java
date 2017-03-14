package models;

import java.awt.*;

/**
 * Created by l on 2/28/2017.
 */
public abstract class GameModel {
    public static final int MIN_WIDTH_OF_MODEL = 30;
    public static final int MIN_HEIGHT_OF_MODEL = 30;
    public static final int MAX_WIDTH_OF_MODEL = 70;
    public static final int MAX_HEIGHT_OF_MODEL = 70;

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected boolean visible;
    protected boolean destroy;


    public GameModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        visible = true;
        destroy = false;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean checkContact(GameModel otherGameModel){
        Rectangle r1 = new Rectangle(x,y,width,height);
        Rectangle r2 = new Rectangle(otherGameModel.x,otherGameModel.y,otherGameModel.width,otherGameModel.height);
        return (r1.intersects(r2));
    }

    public abstract void collisionHandler(GameModel otherGameModel);

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDestroy() {
        return destroy;
    }

    public void setDestroy(boolean destroy) {
        this.destroy = destroy;
    }
}
