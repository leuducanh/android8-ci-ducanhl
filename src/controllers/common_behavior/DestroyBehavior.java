package controllers.common_behavior;

import autoload.AutoLoadImage;
import models.GameModel;
import views.GameView;

/**
 * Created by l on 3/14/2017.
 */
public class DestroyBehavior {
    protected long delay = 200;
    protected long lastTimeDraw = System.currentTimeMillis();
    protected int drawCount = 0;

    protected GameView view;
    protected GameModel model;

    public void destroy(GameView view, GameModel model){
        this.model = model;
        this.view  = view;
    }

    public void setImage(){
        view.setImage(AutoLoadImage.explosionImageMap.get("explosion0"));
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastTimeDraw > delay){
            lastTimeDraw = currentTime;
            drawCount++;

            if(drawCount >= 10){
                model.setVisible(false);
                drawCount = 0;
            }
        }
    }
}
