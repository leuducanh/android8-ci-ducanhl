package controllers;

import models.GameModel;
import views.GameView;

import java.awt.*;

/**
 * Created by l on 2/28/2017.
 */
public class GameController {
    protected GameModel model;
    protected GameView view;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void draw(Graphics graphics){
        view.draw(graphics,model);
    }
}
