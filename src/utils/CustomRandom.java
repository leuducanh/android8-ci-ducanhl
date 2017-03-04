package utils;

import controllers.GameController;
import models.GameModel;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by l on 3/4/2017.
 */
public class CustomRandom{

    public static int customNextInt(int i){
        Random r = new Random();
        return r.nextInt(i);
    }

    public static Vector<Integer> customGeneratePosition(Vector<GameModel> vGM, int rangeWidth){
        Vector<Integer> v = new Vector<>();

        int xmin,xmax;
        int a,b;
        int remember1 = 1;
        int remember2 = rangeWidth;
        for(int i = 0;i < vGM.size();i++){
            int mX = vGM.get(i).getX();
            int mW = vGM.get(i).getWidth();
            a = mX - remember1;
            b = remember2 - (mX + mW);
            if(a > b){
                remember2 = mX;
            }else{
                remember1 = mX + mW;
            }
        }
        int gerX;
        int gerW;

            gerW = customNextInt(remember2 - remember1) + GameModel.MIN_WIDTH_OF_MODEL;
            if(gerW > GameModel.MAX_WIDTH_OF_MODEL){
                gerW = GameModel.MAX_WIDTH_OF_MODEL;
            }
            gerX = customNextInt(remember2 - remember1 + 1) + remember1;

            if(gerX+gerW > remember2){
                gerX = remember2 - gerW - 1;
            }

            v.add(gerX);
            v.add(gerW);

            return v;
    }
}
