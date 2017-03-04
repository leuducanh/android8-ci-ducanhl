package controllers;

import autoload.AutoLoadImage;
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

    public PlayerBulletController(int x, int y, int width, int height,int bulletType) {
        super(new PlayerBulletModel(x,y,width,height,bulletType),new PlayerBulletView(Utils.loadImageFromRes("bullet.png")));
    }

    public void run(){
        if(model instanceof PlayerBulletModel){
            switch (((PlayerBulletModel) model).getBulletType()){
                case(2):{
                    ((PlayerBulletModel) model).moveLeftUp();
                    break;
                }
                case (3):{
                    ((PlayerBulletModel) model).moveRightUp();
                    break;
                }
                default:{
                    ((PlayerBulletModel) model).fly();
                }
            }

            view.setImage(factoryImage(((PlayerBulletModel) model).getBulletType()));
        }
    }

    public PlayerBulletModel getModel() {
        return (PlayerBulletModel) model;
    }

    public Image factoryImage(int i){
        switch (i){
            case(0):{
                return AutoLoadImage.bulletImageMap.get("bullet");
            }
            case(1):{
                return AutoLoadImage.bulletImageMap.get("bullet-double");

            }
            case(2):{
                return  AutoLoadImage.bulletImageMap.get("bullet-left");

            }
            case(3):{
                return  AutoLoadImage.bulletImageMap.get("bullet-right");

            }
            case(4):{
                return AutoLoadImage.bulletImageMap.get("bullet-round");

            }
            case(5):{
                return AutoLoadImage.bulletImageMap.get("bullet-single");

            }
        }
        return null;
    }
}
