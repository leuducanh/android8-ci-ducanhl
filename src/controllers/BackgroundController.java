package controllers;

import models.BackgroundModel;
import models.GameModel;
import utils.Utils;
import views.BackgroundView;
import views.GameView;

/**
 * Created by l on 3/4/2017.
 */
public class BackgroundController extends GameController {
    public BackgroundController(GameModel model, GameView view) {
        super(model, view);
    }

    public BackgroundController(int x,int y,int width,int height){
        this(new BackgroundModel(x,y,width,height),new BackgroundView(Utils.loadImageFromRes("background.png")));
    }
}
