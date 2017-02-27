package views;

import models.EnemyPlaneModel;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by l on 2/27/2017.
 */
public class EnemyPlaneView {

    private Image image;

    public EnemyPlaneView(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics, EnemyPlaneModel model){
            graphics.drawImage(image,model.getX(),model.getY(),model.getWidth(),model.getHeight(),null);
    }

    public void setImage(Image image){
        this.image = image;
    }
}
