package views;

import models.PlayerPlaneModel;

import java.awt.*;

/**
 * Created by l on 2/27/2017.
 */
public class PlayerPlaneView {

    private Image image;

    public PlayerPlaneView(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics, PlayerPlaneModel model){
        graphics.drawImage(image,model.getX(),model.getY(),model.getWidth(),model.getHeight(),null);
    }
}
