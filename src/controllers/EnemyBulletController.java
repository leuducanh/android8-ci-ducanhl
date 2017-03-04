package controllers;

import models.EnemyBulletModel;
import models.GameModel;
import utils.Utils;
import views.EnemyBulletView;
import views.GameView;

import java.awt.*;

/**
 * Created by l on 2/27/2017.
 */
public class EnemyBulletController extends GameController{


    public EnemyBulletController(GameModel model, GameView view) {
        super(model, view);
    }

    public EnemyBulletController(int x,int y,int width,int height){
        this(new EnemyBulletModel(x,y,width,height),new EnemyBulletView(Utils.loadImageFromRes("enemy_bullet.png")));
    }

    public void run(){
        ((EnemyBulletModel)model).move();
    }


    public EnemyBulletModel getModel() {
        return (EnemyBulletModel) model;
    }
}
