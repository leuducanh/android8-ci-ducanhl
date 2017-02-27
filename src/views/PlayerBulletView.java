package views;

import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by l on 2/26/2017.
 */
public class PlayerBulletView {
    private Image imge;

    public PlayerBulletView(Image imge) {
        this.imge = imge;
    }

    public void draw(Graphics graphics, PlayerBulletModel model){
        graphics.drawImage(
                imge,model.getX(),
                model.getY(),
                model.getWidth(),
                model.getHeight(),
                null);
    }
}
