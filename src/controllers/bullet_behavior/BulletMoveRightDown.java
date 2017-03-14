package controllers.bullet_behavior;

import controllers.BulletController;
import models.BulletModel;
import views.BulletView;

/**
 * Created by l on 3/12/2017.
 */
public class BulletMoveRightDown extends BulletMoveBehavior {

    @Override
    public void move(BulletModel bulletModel, BulletView view, BulletController.BulletType type) {
        super.move(bulletModel, view, type);

        bulletModel.moveRightDown();
    }
}
