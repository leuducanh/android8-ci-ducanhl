package pool;

import controllers.GameController;
import models.GameModel;

import java.util.Vector;

/**
 * Created by l on 3/5/2017.
 */
public class Conllision {

    private Vector<GameController> gameControllers;

    public void openPool(){
        gameControllers = new Vector<>();
    }

    public void addToThePool(GameController gameController){
        gameControllers.add(gameController);
    }

    public void checkCollision(){
        for(int i = 0;i < gameControllers.size() - 1;i++){
            for(int j = 0;j < gameControllers.size();j++){
                if(gameControllers.get(i).getModel().checkContact(gameControllers.get(j).getModel())){
                    gameControllers.get(i).getModel().collisionHandler(gameControllers.get(j).getModel());
                    gameControllers.get(j).getModel().collisionHandler(gameControllers.get(i).getModel());
                }
            }
        }
    }

    public void delete(){
        for(int i = 0;i < gameControllers.size();i++){
            if(!gameControllers.get(i).getModel().isVisible()){
                gameControllers.removeElementAt(i);
            }
        }
    }
}
