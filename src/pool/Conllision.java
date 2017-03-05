package pool;

import models.GameModel;

import java.util.Vector;

/**
 * Created by l on 3/5/2017.
 */
public class Conllision {

    private Vector<GameModel> gameModels;

    public void openPool(){
        gameModels = new Vector<>();
    }

    public void addToThePool(GameModel gameModel){
        gameModels.add(gameModel);
    }

    public void checkCollision(){

    }
}
