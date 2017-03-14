package controllers;

import autoload.AutoLoadImage;
import models.PlayerPlaneModel;
import utils.Utils;
import views.PlayerPlaneView;

import java.awt.*;

/**
 * Created by l on 2/27/2017.
 */
public class PlayerPlaneColtroller extends GameController{

    private int countToDrawDestroyState = 0;
    private int countToDrawImage = 0;

    public PlayerPlaneColtroller(int x,int y,int width,int height){
        super(new PlayerPlaneModel(x,y,width,height),new PlayerPlaneView(Utils.loadImageFromRes("plane3.png")));
    }

    public void movePlayerPlaneToLeft(){
        ((PlayerPlaneModel)model).moveLeft();
        ((PlayerPlaneModel)model).decrePowerUpTime();
    }

    public void movePlayerPlaneToRight(){
        ((PlayerPlaneModel)model).moveRight();
        ((PlayerPlaneModel)model).decrePowerUpTime();

    }

    public void movePlayerPlaneUp(){
        ((PlayerPlaneModel)model).moveUp();
        ((PlayerPlaneModel)model).decrePowerUpTime();

    }

    public void movePlayerPlaneDown(){
        ((PlayerPlaneModel)model).moveDown();
        ((PlayerPlaneModel)model).decrePowerUpTime();

    }

    public void draw(Graphics graphics) {
        if(model instanceof PlayerPlaneModel){
            if(!model.isDestroy()){
                view.setImage(Utils.loadImageFromRes("plane4.png"));
            }else{
                view.setImage(AutoLoadImage.explosionImageMap.get("explosion"+countToDrawDestroyState));
                countToDrawDestroyState += 1;
                if(countToDrawDestroyState == 10){
                     model.setVisible(false);
                    countToDrawDestroyState = 9;
                }
            }
        }
        super.draw(graphics);
    }

    public PlayerPlaneModel getModel() {
        return (PlayerPlaneModel) model;
    }
}
