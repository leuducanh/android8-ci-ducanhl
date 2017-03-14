package controllers;

import autoload.AutoLoadImage;
import controllers.bullet_behavior.BulletMoveBehavior;
import controllers.bullet_behavior.BulletMoveLeftDown;
import controllers.bullet_behavior.BulletMoveRightDown;
import controllers.bullet_behavior.BulletMoveTop_Down;
import models.BulletModel;
import models.EnemyPlaneModel;
import models.GameModel;
import views.BulletView;
import views.GameView;

/**
 * Created by l on 3/14/2017.
 */
public class BulletController extends GameController{

    private BulletMoveBehavior bulletMoveBehavior;

    private BulletType bulletType;

    public BulletController(GameModel model, GameView view) {
        super(model, view);
    }

    public BulletController(int x, int y, BulletView bulletView, BulletModel.Plane planeFrom,BulletType type){
        this(new BulletModel(x,y,planeFrom),bulletView);
        this.bulletType = type;
    }

    @Override
    public void run() {
        if(model instanceof BulletModel){
            if(model.isDestroy()){
                model.setVisible(false);
            }else{
                bulletMoveBehavior.move((BulletModel) model,(BulletView)view,bulletType);
            }
        }
    }

    public static enum BulletType{
        SINGLE,
        DOUBLE,
        LEFTDOWN,
        RIGHTDOWN,
        ROUND
    }

    public static enum BulletMoveType{
        TOP_DOWN,
        RIGHT_DOWN,
        LEFT_DOWN
    }

    public void setBulletMoveBehavior(BulletMoveBehavior bulletMoveBehavior) {
        this.bulletMoveBehavior = bulletMoveBehavior;
    }

    public static BulletController create(int x, int y, GameModel gameModel, BulletType bulletType,BulletMoveType bulletMoveType){
        BulletModel.Plane planeFrom;
        if(gameModel instanceof EnemyPlaneModel){
            planeFrom = BulletModel.Plane.ENEMY_PLANE;
        }else{
            planeFrom = BulletModel.Plane.PLAYER_PLANE;
        }

        BulletMoveBehavior bulletMoveBehavior = null;
        switch (bulletMoveType){
            case TOP_DOWN:{
                bulletMoveBehavior = new BulletMoveTop_Down();
                break;
            }
            case RIGHT_DOWN:{
                bulletMoveBehavior = new BulletMoveRightDown();
                break;
            }
            case LEFT_DOWN:{
                bulletMoveBehavior = new BulletMoveLeftDown();
                break;
            }
        }

        BulletController bulletController = new BulletController(x,y,new BulletView(AutoLoadImage.bulletImageMap.get("bullet")),planeFrom,bulletType);
        bulletController.setBulletMoveBehavior(bulletMoveBehavior);

        return bulletController;
    }
}
