package autoload;

import utils.Utils;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by l on 3/4/2017.
 */
public class AutoLoadImage {
    public static HashMap<String,Image> powerupImageMap;

    public static void init() {
        powerupImageMap = new HashMap<>();

        for(int i = 1;i <= 10;i++){
            powerupImageMap.put("frame-"+i, Utils.loadImageFromRes("frame-"+i+".png"));
        }
    }
}
