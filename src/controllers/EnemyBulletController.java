package controllers;

import models.EnemyBulletModel;
import utils.Utils;
import views.EnemyBulletView;

import java.awt.*;

/**
 * Created by l on 2/27/2017.
 */
public class EnemyBulletController {

    EnemyBulletModel model;
    EnemyBulletView view;

    public EnemyBulletController(EnemyBulletModel model, EnemyBulletView view) {
        this.model = model;
        this.view = view;
    }

    public EnemyBulletController(int x,int y,int width,int height){
        this(new EnemyBulletModel(x,y,width,height),new EnemyBulletView(Utils.loadImageFromRes("enemy_bullet.png")));
    }

    public void run(){
        model.move();
    }

    public void draw(Graphics graphics){
        view.draw(graphics,model);
    }

    public EnemyBulletModel getModel() {
        return model;
    }
}
