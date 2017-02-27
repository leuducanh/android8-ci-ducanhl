package controllers;

import models.PlayerBulletModel;
import utils.Utils;
import views.PlayerBulletView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by l on 2/26/2017.
 */
public class PlayerBulletController {

    private PlayerBulletModel model;
    private PlayerBulletView view;

    public PlayerBulletController(int x,int y,int width,int height) {
        model = new PlayerBulletModel(x,y,width,height);
        view = new PlayerBulletView(Utils.loadImageFromRes("bullet.png"));
    }

    public void run(){
            model.fly();
    }

    public PlayerBulletModel getModel() {
        return model;
    }

    public void draw(Graphics graphics){
            view.draw(graphics,model);
    }
}
