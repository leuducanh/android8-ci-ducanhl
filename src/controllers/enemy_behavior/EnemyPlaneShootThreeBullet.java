package controllers.enemy_behavior;

import controllers.BulletController;
import controllers.ControllerManager;
import models.BulletModel;
import models.EnemyPlaneModel;

/**
 * Created by l on 3/19/2017.
 */
public class EnemyPlaneShootThreeBullet extends EnemyPlaneShootBehavior {
    @Override
    public void shoot(EnemyPlaneModel model, long timeDelayToShoot, BulletController.BulletType type) {
        super.shoot(model, timeDelayToShoot, type);
    }

    @Override
    public void createBullet() {
        if(type == BulletController.BulletType.LEFTDOWN || type == BulletController.BulletType.RIGHTDOWN){
            ControllerManager.gameControllers.add(BulletController.create(model.getX() + model.getWidth()/2 - BulletModel.BULLET_WIDTH/2,model.getY() + model.getHeight(),model, BulletController.BulletType.LEFTDOWN, BulletController.BulletMoveType.LEFT_DOWN));
            ControllerManager.gameControllers.add(BulletController.create(model.getX() + model.getWidth()/2 - BulletModel.BULLET_WIDTH/2,model.getY() + model.getHeight(),model, BulletController.BulletType.RIGHTDOWN, BulletController.BulletMoveType.RIGHT_DOWN));
            ControllerManager.gameControllers.add(BulletController.create(model.getX() + model.getWidth()/2 - BulletModel.BULLET_WIDTH/2,model.getY() + model.getHeight(),model, BulletController.BulletType.SINGLE, BulletController.BulletMoveType.TOP_DOWN));
        }else{
            ControllerManager.gameControllers.add(BulletController.create(model.getX() + model.getWidth()/2 - BulletModel.BULLET_WIDTH/2,model.getY() + model.getHeight(),model,type, BulletController.BulletMoveType.LEFT_DOWN));
            ControllerManager.gameControllers.add(BulletController.create(model.getX() + model.getWidth()/2 - BulletModel.BULLET_WIDTH/2,model.getY() + model.getHeight(),model,type, BulletController.BulletMoveType.RIGHT_DOWN));
            ControllerManager.gameControllers.add(BulletController.create(model.getX() + model.getWidth()/2 - BulletModel.BULLET_WIDTH/2,model.getY() + model.getHeight(),model,type, BulletController.BulletMoveType.TOP_DOWN));
        }
    }
}
