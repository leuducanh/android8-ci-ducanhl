package models;


/**
 * Created by l on 3/4/2017.
 */
public class IslandModel extends GameModel{
    public static final int SPEED_ISLAND = 2;

    public IslandModel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void collisionHandler(GameModel otherGameModel) {

    }

    public void moveDown(){
        y += SPEED_ISLAND;
    }
}
