package views;

import models.EnemyBulletModel;

import java.awt.*;

/**
 * Created by l on 2/27/2017.
 */
public class EnemyBulletView {
    private Image image;

    public EnemyBulletView(Image image) {
        this.image = image;
    }

    public void draw(Graphics graphics, EnemyBulletModel model){
        graphics.drawImage(
                image,
                model.getX(),
                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null
        );
    }
}
