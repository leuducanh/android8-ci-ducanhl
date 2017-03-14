package controllers.bullet_behavior;

import controllers.BulletController;
import models.BulletModel;
import views.BulletView;

/**
 * Created by l on 3/12/2017.
 */
public class BulletMoveTop_Down extends BulletMoveBehavior {

    @Override
    public void move(BulletModel bulletModel, BulletView view, BulletController.BulletType type) {
        bulletModel.moveTopDown();
    }
}
