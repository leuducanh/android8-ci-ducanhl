package controllers.enemy_behavior;

import autoload.AutoLoadImage;
import controllers.EnemyPlaneController;
import models.EnemyPlaneModel;
import views.EnemyPlaneView;

import java.awt.*;

/**
 * Created by l on 3/12/2017.
 */
public class EnemyPlaneMoveBehavior {
    protected long delay = 200;
    protected long lastTimeDraw = System.currentTimeMillis();
    protected int drawCount = 1;

    protected EnemyPlaneView enemyPlaneView;
    protected EnemyPlaneController.EnemyPlaneType type;

    public void move(EnemyPlaneModel model, EnemyPlaneView view, EnemyPlaneController.EnemyPlaneType type, EnemyPlaneController enemyPlaneController){
        this.type = type;
        this.enemyPlaneView = view;

    }

    public void setImage(){
        enemyPlaneView.setImage(AutoLoadImage.enemyPlaneHashMapFactory(type).get("xuong1"));
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastTimeDraw > delay){
            lastTimeDraw = currentTime;
            drawCount++;
            if(drawCount >= 4){
                drawCount = 1;
            }
        }
    }
}
