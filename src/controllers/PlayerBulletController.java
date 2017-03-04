package controllers;

import models.GameModel;
import models.PlayerBulletModel;
import utils.Utils;
import views.GameView;
import views.PlayerBulletView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by l on 2/26/2017.
 */
public class PlayerBulletController extends GameController{

    public PlayerBulletController(GameModel model, GameView view) {
        super(model, view);
    }

    public PlayerBulletController(int x, int y, int width, int height) {
        super(new PlayerBulletModel(x,y,width,height),new PlayerBulletView(Utils.loadImageFromRes("bullet.png")));
    }

    public void run(){
        ((PlayerBulletModel)model).fly();
    }

    public PlayerBulletModel getModel() {
        return (PlayerBulletModel) model;
    }
}
