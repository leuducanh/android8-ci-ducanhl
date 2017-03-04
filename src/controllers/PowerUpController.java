package controllers;

import autoload.AutoLoadImage;
import models.GameModel;
import models.PowerUpModel;
import utils.Utils;
import views.GameView;
import views.PowerUpView;

import java.awt.*;

/**
 * Created by l on 3/4/2017.
 */
public class PowerUpController extends GameController {
    public static long LAST_TIME_POWERUP = 0;
    public static long ADD_POWERUP_AFTER = 6000;
    public static long TIME_VISIBLE_POWERUP = 100000;

    private int imageCount;
    private PlayerPlaneColtroller playerPlaneColtroller;

    private long timeThisPowerUpAppeal;
    public PowerUpController(GameModel model, GameView view) {
        super(model, view);
    }

    public PowerUpController(int x,int y,int width,int height,PlayerPlaneColtroller playerPlaneColtroller,int powerUpType){
        this(new PowerUpModel(x,y,width,height,powerUpType),new PowerUpView(Utils.loadImageFromRes("frame-1.png")));
        this.playerPlaneColtroller = playerPlaneColtroller;
        imageCount = 1;
        timeThisPowerUpAppeal = System.currentTimeMillis();
    }

    public void run(){
        ((PowerUpModel)model).move(playerPlaneColtroller.getModel().getX(),playerPlaneColtroller.getModel().getY());
        view.setImage(factoryImage(((PowerUpModel)model).getPowerUpType()));

        if(System.currentTimeMillis() - timeThisPowerUpAppeal >= TIME_VISIBLE_POWERUP){
            ((PowerUpModel)model).setInvisible(false);
        }
    }

    public Image factoryImage(int powerUpType){
        String s = "frame-1";
        switch (powerUpType){
            case(0):{
                if(imageCount > 0){
                       s = "frame-"+imageCount;
                }
                imageCount++;

                if(imageCount > 10){
                    imageCount = -100;
                }
                return AutoLoadImage.powerupImageMap.get(s);
            }
            case(1):{

            }
        }

        return null;
    }

    public PowerUpModel getModel() {
        return (PowerUpModel) model;
    }
}
