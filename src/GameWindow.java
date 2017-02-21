import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by l on 2/19/2017.
 */
public class GameWindow extends Frame {

    private static final int SPEED = 10;
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 800;

    Graphics offScreenGraphics;
    BufferedImage offScreenImage;
    Image backgroundImage;
    Image planeImage;
    Image landImage;
    private int planeX,planeY;
    private int move = -1;

    public GameWindow(){
        setVisible(true);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);


        offScreenImage = new BufferedImage(FRAME_WIDTH,FRAME_HEIGHT,BufferedImage.TYPE_INT_ARGB);

//        offScreenImage = (BufferedImage) createImage(FRAME_WIDTH,FRAME_HEIGHT);
//        offScreenGraphics = offScreenImage.getGraphics();

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

        backgroundImage = loadImageFromRes("background.png");
        planeImage = loadImageFromRes("plane4.png");
        landImage = loadImageFromRes("island.png");

        planeX = (FRAME_WIDTH - 45)/2;
        planeY = FRAME_HEIGHT - 35;

        update(getGraphics());

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                move = e.getKeyCode();
            }


            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(move == e.getKeyCode()){
                    move = -1;
                }
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    calculatePlaneCoordinate();
                    repaint();
                }
            }
        });

        thread.start();

    }

    @Override
    public void update(Graphics g) {
        if(offScreenImage != null){
            offScreenGraphics = offScreenImage.getGraphics();
            offScreenGraphics.drawImage(backgroundImage,0,0,FRAME_WIDTH,FRAME_HEIGHT,null);
            offScreenGraphics.drawImage(planeImage,planeX,planeY,45,35,null);
            g.drawImage(offScreenImage,0,0,null);
        }


//        g.drawImage(backgroundImage,0,0,FRAME_WIDTH,FRAME_HEIGHT,null);
//        g.drawImage(planeImage,planeX,planeY,45,35,null);
    }

    private void calculatePlaneCoordinate(){
        int x, y;
        x = planeX;
        y = planeY;

        switch (move){
            case (KeyEvent.VK_RIGHT):{
                planeX += SPEED;
                break;
            }
            case (KeyEvent.VK_LEFT):{
                planeX -= SPEED;
                break;
            }
            case (KeyEvent.VK_UP):{
                planeY -= SPEED;
                break;
            }
            case (KeyEvent.VK_DOWN):{
                planeY += SPEED;
                break;
            }
        }
        if(!checkCoordinatePlane()){
            planeX = x;
            planeY = y;
        }
    }

    private boolean checkCoordinatePlane(){
        if((planeX > FRAME_WIDTH - 45) || (planeX < 0) || (planeY > FRAME_HEIGHT - 35) || (planeY < 35)) {
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
