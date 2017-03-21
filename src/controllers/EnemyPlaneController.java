package controllers;

import autoload.AutoLoadImage;
import controllers.common_behavior.DestroyBehavior;
import controllers.enemy_behavior.*;
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
    private EnemyPlaneShootBehavior enemyPlaneShootBehavior;
    private DestroyBehavior destroyBehavior;

    private EnemyPlaneType type;
    private BulletController.BulletType bulletType;
    private EnemyPlaneShootType shootType;
    private long timeDelayToShoot;

    public EnemyPlaneController(GameModel model, GameView view) {
        super(model,view);
    }

    public EnemyPlaneController(int x, int y, int width, int height, EnemyPlaneView enemyPlaneView, EnemyPlaneType type, BulletController.BulletType bulletType,long timeDelayToShoot,EnemyPlaneShootType shootType){
        this(new EnemyPlaneModel(x,y,width,height),enemyPlaneView);
        destroyBehavior = new DestroyBehavior();

        this.type = type;
        this.bulletType = bulletType;
        this.timeDelayToShoot = timeDelayToShoot;
        this.shootType = shootType;
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
                enemyPlaneShootBehavior.shoot((EnemyPlaneModel)model,timeDelayToShoot,bulletType);
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

    public static enum EnemyPlaneShootType{
        ONE,
        TWO,
        THREE
    }

    public static enum EnemyPlaneMoveType{
        TOP_DOWN,
        DOWN_LEFT,
        DOWN_RIGHT
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


    public void setEnemyPlaneMoveBehavior(EnemyPlaneMoveBehavior enemyPlaneMoveBehavior) {
        this.enemyPlaneMoveBehavior = enemyPlaneMoveBehavior;
    }

    public void setEnemyPlaneShootBehavior(EnemyPlaneShootBehavior enemyPlaneShootBehavior) {
        this.enemyPlaneShootBehavior = enemyPlaneShootBehavior;
    }

    public static EnemyPlaneController create(int x, int y, int width, int height, EnemyPlaneType type, BulletController.BulletType bulletType, long timeDelayToShoot, EnemyPlaneShootType shootType,EnemyPlaneMoveType moveType){
        EnemyPlaneController enemyPlaneController = null;
        EnemyPlaneShootBehavior enemyPlaneShootBehavior = null;
        EnemyPlaneMoveBehavior enemyPlaneMoveBehavior = null;
        switch (shootType){
            case ONE:{
                enemyPlaneShootBehavior = new EnemyPlaneShootOneBullet();
                break;
            }

            case TWO:{
                enemyPlaneShootBehavior = new EnemyPlaneShootTwoBullet();
                break;
            }

            case THREE:{
                enemyPlaneShootBehavior = new EnemyPlaneShootThreeBullet();
                break;
            }
        }

        switch (type){
            case ENEMYWHITE:{
                enemyPlaneController = new EnemyPlaneController(x,y,width,height,new EnemyPlaneView(AutoLoadImage.whitePlaneImageMap.get("xuong1")),type,bulletType,timeDelayToShoot,shootType);
                break;
            }

            case ENEMYYELLOW:{
                enemyPlaneController = new EnemyPlaneController(x,y,width,height,new EnemyPlaneView(AutoLoadImage.yellowPlaneImageMap.get("xuong1")),type,bulletType,timeDelayToShoot,shootType);
                break;
            }
        }

        switch (moveType){
            case TOP_DOWN:{
                enemyPlaneMoveBehavior = new EnemyPlaneMoveTop_Down();
                break;
            }
            case DOWN_LEFT:{
                enemyPlaneMoveBehavior = new EnemyPlaneMoveDown_Left();
                break;
            }
            case DOWN_RIGHT:{
                enemyPlaneMoveBehavior = new EnemyPlaneMoveDown_Right();
                break;
            }
        }
        enemyPlaneController.setEnemyPlaneShootBehavior(enemyPlaneShootBehavior);
        enemyPlaneController.setEnemyPlaneMoveBehavior(enemyPlaneMoveBehavior);
        return enemyPlaneController;
    }
}
