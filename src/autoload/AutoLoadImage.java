package autoload;

import controllers.EnemyPlaneController;
import utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by l on 3/4/2017.
 */
public class AutoLoadImage {
    public static HashMap<String,Image> powerupImageMap;
    public static HashMap<String,Image> explosionImageMap;
    public static HashMap<String,Image> whitePlaneImageMap;
    public static HashMap<String,Image> yellowPlaneImageMap;
    public static HashMap<String,Image> bulletImageMap;
    public static HashMap<String,Image> bloodImageMap;
    public static Image lazerShot;

    public static void init() {
        powerupImageMap = new HashMap<>();
        explosionImageMap = new HashMap<>();
        whitePlaneImageMap = new HashMap<>();
        yellowPlaneImageMap = new HashMap<>();
        bulletImageMap = new HashMap<>();
        bloodImageMap = new HashMap<>();

        for(int i = 1;i <= 10;i++){
            powerupImageMap.put("frame-"+i, Utils.loadImageFromRes("frame-"+i+".png"));
        }

        int c = 0;
        BufferedImage explosionImage = (BufferedImage) Utils.loadImageFromRes("explosion1.png");
        for(int i = 0;i <= 2;i++){
            for(int j = 0;j <= 2;j++){
                explosionImageMap.put("explosion"+c,explosionImage.getSubimage(170*j,i*170,170,170));
                c++;
            }
        }


        for(int i = 1;i <= 3;i++){
            whitePlaneImageMap.put("enemy_plane_white_" + i,Utils.loadImageFromRes("enemy_plane_white_" + i + ".png"));
        }

        for(int i = 1;i <= 3;i++){
            yellowPlaneImageMap.put("enemy_plane_yellow_" + i,Utils.loadImageFromRes("enemy_plane_yellow_" + i + ".png"));
        }

        bulletImageMap.put("bullet",Utils.loadImageFromRes("bullet.png"));
        bulletImageMap.put("bullet-double",Utils.loadImageFromRes("bullet-double.png"));
        bulletImageMap.put("bullet-left",Utils.loadImageFromRes("bullet-left.png"));
        bulletImageMap.put("bullet-right",Utils.loadImageFromRes("bullet-right.png"));
        bulletImageMap.put("bullet-round",Utils.loadImageFromRes("bullet-round.png"));
        bulletImageMap.put("bullet-single",Utils.loadImageFromRes("bullet-single.png"));

        BufferedImage bloodImage = (BufferedImage) Utils.loadImageFromRes("bloodcut.png");
        for(int i =0 ;i < 7;i++){
            bloodImageMap.put("bloodcut"+i,bloodImage.getSubimage(64*i,0,64,64));
        }

        lazerShot = Utils.loadImageFromRes("laserGreenShot.png");
    }

    public static HashMap<String,Image> enemyPlaneHashMapFactory(EnemyPlaneController.EnemyPlaneType type){
        switch (type){
            case ENEMYWHITE:{
                return whitePlaneImageMap;
            }
            case ENEMYYELLOW:{
                return yellowPlaneImageMap;
            }
        }
        return null;
    }
}
