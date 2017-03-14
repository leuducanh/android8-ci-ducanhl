package controllers.bullet_behavior;

import autoload.AutoLoadImage;
import controllers.EnemyBulletController;
import models.GameModel;
import views.EnemyBulletView;
import views.GameView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by l on 3/12/2017.
 */
public class EnemyBulletMoveBehavior {
    protected long lastTimeDraw = System.currentTimeMillis();
    protected long delay = 100;
    protected GameView view;
    protected EnemyBulletController.BulletType type;
    protected int howManyPicOnABullet = 1;
    protected int drawCount = 0;

    public void move(EnemyBulletController enemyBulletController, EnemyBulletView view, EnemyBulletController.BulletType type){
        this.type = type;
        this.view = view;
    }

    public void setImage(){
        Vector<Image> images = new Vector<>();
        switch (type){
            case DOUBLE:{
                images.add(AutoLoadImage.bulletImageMap.get("bullet-double"));
                break;
            }
            case LEFTDOWN:{
                images.add(AutoLoadImage.bulletImageMap.get("bullet-left"));
                break;
            }
            case RIGHTDOWN:{
                images.add(AutoLoadImage.bulletImageMap.get("bullet-right"));
                break;
            }
            case ROUND:{
                images.add(AutoLoadImage.bulletImageMap.get("bullet-round"));
                break;
            }
            case SINGLE:{
                images.add(AutoLoadImage.bulletImageMap.get("bullet-single"));
                break;
            }
        }

        view.setImage(images.get(drawCount));
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastTimeDraw > delay){
            drawCount++;
            if(drawCount >= howManyPicOnABullet){
                drawCount = 0;
            }
        }
    }
}
