package controllers.bullet_behavior;

import controllers.EnemyBulletController;
import views.EnemyBulletView;

/**
 * Created by l on 3/12/2017.
 */
public class EnemyBulletMoveLeftDown extends EnemyBulletMoveBehavior {
    @Override
    public void move(EnemyBulletController enemyBulletController, EnemyBulletView view, EnemyBulletController.BulletType type) {
        super.move(enemyBulletController, view, type);

        enemyBulletController.getModel().moveLeftDown();
    }
}
