package pool;

import controllers.GameController;
import models.GameModel;

import java.util.Vector;

/**
 * Created by l on 3/5/2017.
 */
public class Conllision {

    public static Vector<GameController> gameControllersCollision;

    public static void openPool(){
        gameControllersCollision = new Vector<>();
    }


    public void checkCollision(){
        for(int i = 0;i < gameControllersCollision.size() - 1;i++){
            for(int j = 0;j < gameControllersCollision.size();j++){
                if(gameControllersCollision.get(i).getModel().checkContact(gameControllersCollision.get(j).getModel())){
                    gameControllersCollision.get(i).getModel().collisionHandler(gameControllersCollision.get(j).getModel());
                    gameControllersCollision.get(j).getModel().collisionHandler(gameControllersCollision.get(i).getModel());
                }
            }
        }
    }

    public void delete(){
        for(int i = 0;i < gameControllersCollision.size();i++){
            if(!gameControllersCollision.get(i).getModel().isVisible()){
                gameControllersCollision.removeElementAt(i);
            }
        }
    }
}
