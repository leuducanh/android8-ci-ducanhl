package controllers.bullet_behavior;

import controllers.EnemyBulletController;
import models.GameModel;
import views.EnemyBulletView;
import views.GameView;

/**
 * Created by l on 3/12/2017.
 */
public class EnemyBulletMoveTop_Down extends EnemyBulletMoveBehavior {

    @Override
    public void move(EnemyBulletController enemyBulletController, EnemyBulletView view, EnemyBulletController.BulletType type) {
        enemyBulletController.getModel().moveTopDown();
    }
}
