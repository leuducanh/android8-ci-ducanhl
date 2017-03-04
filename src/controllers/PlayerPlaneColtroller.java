package controllers;

import models.PlayerPlaneModel;
import utils.Utils;
import views.PlayerPlaneView;

import java.awt.*;

/**
 * Created by l on 2/27/2017.
 */
public class PlayerPlaneColtroller extends GameController{

    public PlayerPlaneColtroller(int x,int y,int width,int height){
        super(new PlayerPlaneModel(x,y,width,height),new PlayerPlaneView(Utils.loadImageFromRes("plane3.png")));
    }

    public void movePlayerPlaneToLeft(){
        ((PlayerPlaneModel)model).moveLeft();
    }

    public void movePlayerPlaneToRight(){
        ((PlayerPlaneModel)model).moveRight();
    }

    public void movePlayerPlaneUp(){
        ((PlayerPlaneModel)model).moveUp();
    }

    public void movePlayerPlaneDown(){
        ((PlayerPlaneModel)model).moveDown();
    }

    public PlayerPlaneModel getModel() {
        return (PlayerPlaneModel) model;
    }
}
