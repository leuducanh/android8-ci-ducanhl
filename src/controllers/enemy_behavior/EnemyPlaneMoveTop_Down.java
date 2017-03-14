package controllers.enemy_behavior;

import controllers.EnemyPlaneController;
import models.EnemyPlaneModel;
import views.EnemyPlaneView;

/**
 * Created by l on 3/14/2017.
 */
public class EnemyPlaneMoveTop_Down extends EnemyPlaneMoveBehavior{

    @Override
    public void move(EnemyPlaneModel model, EnemyPlaneView view, EnemyPlaneController.EnemyPlaneType type, EnemyPlaneController enemyPlaneController) {
        super.move(model, view, type, enemyPlaneController);

        model.moveDown();

        setImage();
    }
}
