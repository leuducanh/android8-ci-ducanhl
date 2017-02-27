package controllers;

import models.PlayerPlaneModel;
import utils.Utils;
import views.PlayerPlaneView;

import java.awt.*;

/**
 * Created by l on 2/27/2017.
 */
public class PlayerPlaneColtroller {

    PlayerPlaneModel model;
    PlayerPlaneView view;

    public PlayerPlaneColtroller(PlayerPlaneModel model, PlayerPlaneView view) {
        this.model = model;
        this.view = view;
    }

    public PlayerPlaneColtroller(int x,int y,int width,int height){
        this(new PlayerPlaneModel(x,y,width,height),new PlayerPlaneView(Utils.loadImageFromRes("plane3.png")));
    }

    public void movePlayerPlaneToLeft(){
        model.moveLeft();
    }

    public void movePlayerPlaneToRight(){
        model.moveRight();
    }

    public void movePlayerPlaneUp(){
        model.moveUp();
    }

    public void movePlayerPlaneDown(){
        model.moveDown();
    }

    public void draw(Graphics graphics){
        view.draw(graphics,model);
    }

    public PlayerPlaneModel getModel() {
        return model;
    }
}
