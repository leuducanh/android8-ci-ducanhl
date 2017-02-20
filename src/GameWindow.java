import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by l on 2/19/2017.
 */
public class GameWindow extends Frame{

    Image backgroundImage;
    Image planeImage;
    Image landImage;
    private int planeX,planeY;

    public GameWindow(){
        setVisible(true);
        setSize(400,600);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("Window closing");
                System.exit(1);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                System.out.println("Window closed");
            }
        });

        //1:load image

        //2:draw image
            backgroundImage = loadImageFromRes("background.png");
            planeImage = loadImageFromRes("plane4.png");
            landImage = loadImageFromRes("island.png");

        planeX = (400 - 45)/2;
        planeY = 600 - 35;

            update(getGraphics());

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int x, y;
                x = planeX;
                y = planeY;
                switch (e.getKeyCode()){
                    case (KeyEvent.VK_RIGHT):{
                        planeX += 5;
                        break;
                    }
                    case (KeyEvent.VK_LEFT):{
                        planeX -= 5;
                        break;
                    }
                    case (KeyEvent.VK_UP):{
                        planeY -= 5;
                        break;
                    }
                    case (KeyEvent.VK_DOWN):{
                        planeY += 5;
                        break;
                    }
                }
                if(!checkCoordinatePlane()){
                    planeX = x;
                    planeY = y;
                }
                repaint();
                repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

            }
        });

    }



    @Override
    public void update(Graphics g) {
        g.drawImage(backgroundImage,0,0,400,600,null);
        g.drawImage(planeImage,planeX,planeY,45,35,null);
    }

    private boolean checkCoordinatePlane(){
        if((planeX > 400 - 45) || (planeX < 0) || (planeY > 600 - 35) || (planeY < 35)) {
            return false;
        }
        return true;
    }

    private Image loadImageFromRes(String url){
        try {
            Image image = ImageIO.read(new File("resources/" + url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
