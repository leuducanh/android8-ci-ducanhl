package controllers;

import game.GameWindow;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by l on 3/11/2017.
 */
public class ControllerManager {
    public static Vector<GameController> gameControllers;

    public ControllerManager() {
        gameControllers = new Vector<>();
    }

    public void add(GameController gameController) {
        this.gameControllers.add(gameController);
    }

    public void run() {
        removeDeadGameControllers();
        for(int i = 0;i < gameControllers.size();i++){
            gameControllers.get(i).run();
        }
    }

    private void removeDeadGameControllers() {
        Iterator<GameController> gameControllerIterator = this.gameControllers.iterator();
        while(gameControllerIterator.hasNext()) {
            GameController gameController = gameControllerIterator.next();
            if(!gameController.getModel().isVisible()) {
                gameControllerIterator.remove();
            }else if(gameController.getModel().getY() > GameWindow.FRAME_HEIGHT){
                gameControllerIterator.remove();
            }
        }
    }

    public void draw(Graphics g) {
        for(int i = 0;i < gameControllers.size();i++) {
            if(gameControllers.get(i) instanceof PlayerPlaneColtroller){
                ((PlayerPlaneColtroller)gameControllers.get(i)).draw(g);
            }else{
                gameControllers.get(i).draw(g);
            }
        }
    }
}
