package controllers;

import models.EnemyPlaneModel;
import models.GameModel;
import utils.Utils;
import views.EnemyPlaneView;
import views.GameView;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by l on 2/27/2017.
 */
public class EnemyPlaneController extends GameController{

    public static long LAST_TIME_ADD_ENEMYPLANE = 0;
    public static long ADD_ENEMYPLANE_AFTER = 2000;

    private int countToDrawDestroyState = 0;
    private int countToDrawImage = 1;

    public EnemyPlaneController(GameModel model, GameView view) {
        super(model,view);
    }

    public EnemyPlaneController(int x,int y,int width,int height,boolean havePowerUp,int enemyPlaneType){
        this(new EnemyPlaneModel(x,y,width,height,havePowerUp,enemyPlaneType),new EnemyPlaneView(Utils.loadImageFromRes("enemy_plane_white_1.png")));
    }

    public void run(){
        ((EnemyPlaneModel)model).move();
    }

    public void checkAndDraw(Graphics graphics){
        if(model instanceof EnemyPlaneModel){
            if(!((EnemyPlaneModel) model).getDestroy()){
                super.draw(graphics);
            }else{
                BufferedImage explosionImage = (BufferedImage) Utils.loadImageFromRes("explosion.png");
                view.setImage(explosionImage.getSubimage(34*countToDrawDestroyState,0,34,34));
                view.draw(graphics,model);
                countToDrawDestroyState += 1;
                if(countToDrawDestroyState == 5){
                    ((EnemyPlaneModel) model).setInvisible(false);
                    countToDrawDestroyState = 4;
                }
            }
        }
    }

    public EnemyPlaneModel getModel() {
        return (EnemyPlaneModel) model;
    }

    public void checkBeingShotByPlayer(int xBullet,int yBullet){
        int xEnemyPlane = model.getX();
        int yEnemyPlane = model.getY();
        int hEnemyPlane = model.getHeight();
        int wEnemyPlane = model.getWidth();
        if(xBullet <= xEnemyPlane +  wEnemyPlane && xBullet >= xEnemyPlane && yBullet <= yEnemyPlane + hEnemyPlane && yBullet >= yEnemyPlane){
            ((EnemyPlaneModel)model).setDestroy(true);
        }
    }

    public Image factoryImage(){
        String s;
        switch (((EnemyPlaneModel)model).getEnemyPlaneType()){
            case(0):{
                s = "enemy_plane_white_" + countToDrawImage + ".png";
                countToDrawImage++;
                if(countToDrawImage > 3){
                    countToDrawImage = 1;
                }

                return Utils.loadImageFromRes(s);
            }
            case(1):{

            }
        }

        return null;
    }


}
