package controllers;

import autoload.AutoLoadImage;
import controllers.common_behavior.DestroyBehavior;
import controllers.enemy_behavior.EnemyPlaneMoveBehavior;
import game.GameWindow;
import models.EnemyPlaneModel;
import models.GameModel;
import utils.CustomRandom;
import utils.Utils;
import views.EnemyPlaneView;
import views.GameView;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by l on 2/27/2017.
 */
public class EnemyPlaneController extends GameController{
//
//    public static long LAST_TIME_ADD_ENEMYPLANE = 0;
//    public static long ADD_ENEMYPLANE_AFTER = 2000;
//
//    private int countToDrawDestroyState = 0;
//    private int countToDrawImage = 0;

    private EnemyPlaneMoveBehavior enemyPlaneMoveBehavior;
    private DestroyBehavior destroyBehavior;

    private EnemyPlaneType type;

    public EnemyPlaneController(GameModel model, GameView view) {
        super(model,view);
    }

    public EnemyPlaneController(int x,int y,int width,int height,EnemyPlaneView enemyPlaneView,EnemyPlaneType type){
        this(new EnemyPlaneModel(x,y,width,height),enemyPlaneView);
        destroyBehavior = new DestroyBehavior();

        this.type = type;
    }

    public void run(){
//        if (model instanceof EnemyPlaneModel){
//
//            if(((EnemyPlaneModel) model).isMoveChange()){
//                switch (((EnemyPlaneModel) model).getSpecialMove()){
//                    case(0):{
//                        ((EnemyPlaneModel) model).moveRandom();
//                        break;
//                    }
//                    case(1):{
//                        ((EnemyPlaneModel) model).moveCircle(CustomRandom.customNextInt(5)+5);
//                        break;
//                    }
//                }
//            }else{
//                ((EnemyPlaneModel)model).move();
//            }
//
//            if(((EnemyPlaneModel)model).getY() > ((EnemyPlaneModel) model).getLimitY()){
//                ((EnemyPlaneModel) model).setMoveChange(true);
//            }
//            if(!((EnemyPlaneModel) model).getDestroy()){
//                view.setImage(factoryImage());
//
//            }else{
//                view.setImage(AutoLoadImage.explosionImageMap.get("explosion"+countToDrawDestroyState));
//                ((EnemyPlaneModel) model).increaseHeight();
//                ((EnemyPlaneModel) model).increaseWidth();
//                countToDrawDestroyState += 1;
//                if(countToDrawDestroyState == 10){
//                    ((EnemyPlaneModel) model).setVisible(false);
//                    countToDrawDestroyState = 9;
//                }
//            }
//
//        }

        if(model instanceof EnemyPlaneModel){
            if(model.isDestroy()){
                destroyBehavior.destroy(view,model);
            }else{
                enemyPlaneMoveBehavior.move((EnemyPlaneModel)model,(EnemyPlaneView)view,type,this);
            }
        }

    }

//    public void draw(Graphics graphics){
//        if(model instanceof EnemyPlaneModel){
//            if(!((EnemyPlaneModel) model).getDestroy()){
//                view.setImage(factoryImage());
//
//            }else{
//                view.setImage(AutoLoadImage.explosionImageMap.get("explosion"+countToDrawDestroyState));
//                ((EnemyPlaneModel) model).increaseHeight();
//                ((EnemyPlaneModel) model).increaseWidth();
//                countToDrawDestroyState += 1;
//                if(countToDrawDestroyState == 10){
//                    ((EnemyPlaneModel) model).setVisible(false);
//                    countToDrawDestroyState = 9;
//                }
//            }
//            view.draw(graphics,model);
//
//        }
//    }

    public EnemyPlaneModel getModel() {
        return (EnemyPlaneModel) model;
    }

    public static enum EnemyPlaneType{
        ENEMYWHITE,
        ENEMYYELLOW
    }

//    public Image factoryImage(){
//        String s;
//        switch (((EnemyPlaneModel)model).getEnemyPlaneType()){
//            case(0):{
//                s = "enemy_plane_yellow_" + countToDrawImage;
//                countToDrawImage++;
//                if(countToDrawImage > 3){
//                    countToDrawImage = 1;
//                }
//
//                return AutoLoadImage.yellowPlaneImageMap.get(s);
//            }
//            case(1):{
//                s = "enemy_plane_white_" + countToDrawImage;
//                countToDrawImage++;
//                if(countToDrawImage > 3){
//                    countToDrawImage = 1;
//                }
//
//                return AutoLoadImage.whitePlaneImageMap.get(s);
//            }
//        }
//
//        return null;
//    }


}
