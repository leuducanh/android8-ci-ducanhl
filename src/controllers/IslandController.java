package controllers;

import models.GameModel;
import models.IslandModel;
import utils.CustomRandom;
import utils.Utils;
import views.GameView;
import views.IslandView;

import java.awt.*;
import java.util.Random;

/**
 * Created by l on 3/4/2017.
 */
public class IslandController extends GameController{

    public static long LAST_TIME_ADD_ISLAND = 0;
    public static long ADD_ISLAND_AFTER = 40;

    public static int ISLAND_IMAGES_COUNT = 2;
    public IslandController(GameModel model, GameView view) {
        super(model, view);

    }

    public IslandController(int x,int y,int width,int height){
        this(new IslandModel(x,y,width,height),new IslandView(Utils.loadImageFromRes("island.png")));
    }

    public void run(){
        ((IslandModel)model).moveDown();
    }

    public Image factoryImageIsland(int i){
        switch (i){
            case (0):{
                return Utils.loadImageFromRes("island.png");
            }

            case(1):{
                return Utils.loadImageFromRes("island-2.png");
            }
        }

        return Utils.loadImageFromRes("island.png");
    }

    public void setRandomImage(){
        view.setImage(factoryImageIsland(CustomRandom.customNextInt(ISLAND_IMAGES_COUNT )));
    }

    public IslandModel getModel(){
        return (IslandModel)model;
    }
}
