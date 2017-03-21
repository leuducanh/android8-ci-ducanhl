package controllers.bullet_behavior;

import controllers.BulletController;
import models.BulletModel;
import views.BulletView;

/**
 * Created by l on 3/19/2017.
 */
public class BulletMoveRightUp extends BulletMoveBehavior {

    @Override
    public void move(BulletModel bulletModel, BulletView view, BulletController.BulletType type) {
        super.move(bulletModel, view, type);
        bulletModel.moveRightUp();
    }
}
