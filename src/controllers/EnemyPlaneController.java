package controllers;

import models.EnemyPlaneModel;
import utils.Utils;
import views.EnemyPlaneView;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by l on 2/27/2017.
 */
public class EnemyPlaneController {

    private EnemyPlaneModel model;
    private EnemyPlaneView view;
    private int countToDrawDestroyState = 0;

    public EnemyPlaneController(EnemyPlaneModel model, EnemyPlaneView view) {
        this.model = model;
        this.view = view;
    }

    public EnemyPlaneController(int x,int y,int width,int height){
        this(new EnemyPlaneModel(x,y,width,height),new EnemyPlaneView(Utils.loadImageFromRes("enemy_plane_white_1.png")));
    }

    public void run(){
        model.move();
    }

    public void draw(Graphics graphics){
        if(!model.getDestroy()){
            view.draw(graphics,model);
        }else{
            BufferedImage explosionImage = (BufferedImage) Utils.loadImageFromRes("explosion.png");
            view.setImage(explosionImage.getSubimage(34*countToDrawDestroyState,0,34,34));
            view.draw(graphics,model);
            countToDrawDestroyState += 1;
            if(countToDrawDestroyState == 5){
                model.setInvisible(false);
            }
        }
    }

    public EnemyPlaneModel getModel() {
        return model;
    }

    public void checkBeingShotByPlayer(int xBullet,int yBullet){
        int xEnemyPlane = model.getX();
        int yEnemyPlane = model.getY();
        int hEnemyPlane = model.getHeight();
        int wEnemyPlane = model.getWidth();
        if(xBullet <= xEnemyPlane +  wEnemyPlane && xBullet >= xEnemyPlane && yBullet <= yEnemyPlane + hEnemyPlane && yBullet >= yEnemyPlane){
            model.setDestroy(true);
        }
    }
}
