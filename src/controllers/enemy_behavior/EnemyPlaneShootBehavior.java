package controllers.enemy_behavior;

import controllers.BulletController;
import models.EnemyPlaneModel;

/**
 * Created by l on 3/19/2017.
 */
public abstract class EnemyPlaneShootBehavior {
    protected long lastTimeShoot = System.currentTimeMillis();
    protected BulletController.BulletType type;
    protected EnemyPlaneModel model;

    public void shoot(EnemyPlaneModel model, long timeDelayToShoot, BulletController.BulletType type){
        this.type = type;
        this.model = model;
        long currentTimeToShoot = System.currentTimeMillis();
        if(currentTimeToShoot - lastTimeShoot > timeDelayToShoot){
            lastTimeShoot = currentTimeToShoot;
            createBullet();
        }
    }

    public abstract void createBullet();
}
