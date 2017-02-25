import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

/**
 * Created by l on 2/19/2017.
 */
public class GameWindow extends Frame {

    public static final int SPEED = 10;
    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 800;

    Graphics offScreenGraphics;
    BufferedImage offScreenImage;
    Image backgroundImage;
    Image planeImage;
    Image landImage;
    Image enemyPlaneImage;
    Image enemyBullet;
    BufferedImage explosionImage;

    Vector<PlayerBullet> playerBulletList;
    Vector<EnemyPlane> enemyPlaneList;
    Vector<EnemyPlane> enemyPlaneListDestroy;


    int enemyX,enemyY;

    int move = -1;
    int shoot = -1;

    long now = System.currentTimeMillis();

    PlayerPlane playerPlane;

    public GameWindow(){
        setVisible(true);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);

        playerBulletList = new Vector<>();
        enemyPlaneList = new Vector<>();
        enemyPlaneListDestroy = new Vector<>();

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
        playerPlane = new PlayerPlane((FRAME_WIDTH - 45)/2,FRAME_HEIGHT - 35,SPEED);


        backgroundImage = loadImageFromRes("background.png");
        planeImage = loadImageFromRes("plane4.png");
        enemyPlaneImage = loadImageFromRes("enemy_plane_white_1.png");
        landImage = loadImageFromRes("island.png");
        explosionImage = (BufferedImage) loadImageFromRes("explosion.png");

        enemyX = FRAME_WIDTH / 2;
        enemyY = 35/2;

        update(getGraphics());

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                switch (e.getKeyCode()){
                    case(KeyEvent.VK_SPACE):{
                        shoot = KeyEvent.VK_SPACE;
                        break;
                    }
                    default:{
                        move = e.getKeyCode();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(move == e.getKeyCode()){
                    move = -1;
                }
                if(shoot == e.getKeyCode()){
                    shoot = -1;
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
                    calculatePlayerBullet();
                    calculateEnemyBullet();
                    calculateEnemyPlaneCoordinate();
                    if(System.currentTimeMillis() - now > 1000){
                        now = System.currentTimeMillis();
                        addEnemyPlane();
                    }
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
            offScreenGraphics.drawImage(planeImage,playerPlane.x,playerPlane.y,45,35,null);
            if(playerBulletList != null){
                for(int i = 0;i < playerBulletList.size();i++){
                    offScreenGraphics.drawImage(playerBulletList.get(i).image,playerBulletList.get(i).x,playerBulletList.get(i).y,45/2,35/2,null);
                }

            }
            if(enemyPlaneList != null){
                for(int i = 0;i < enemyPlaneList.size();i++){
                    offScreenGraphics.drawImage(enemyPlaneList.get(i).image,enemyPlaneList.get(i).x,enemyPlaneList.get(i).y,45,35,null);
                    Vector<EnemyBullet> enemyBullets = enemyPlaneList.get(i).enemyBulletList;
                    for(int j = 0;j < enemyBullets.size();j++){
                        if(enemyBullets.get(j) != null){
                            offScreenGraphics.drawImage(enemyBullets.get(j).image,enemyBullets.get(j).x,enemyBullets.get(j).y,45/2,35/2,null);

                        }
                    }
                }
            }
            if(enemyPlaneListDestroy != null){
                for(int i = 0;i < enemyPlaneListDestroy.size();i++){
                    offScreenGraphics.drawImage(enemyPlaneListDestroy.get(i).image,enemyPlaneListDestroy.get(i).x,enemyPlaneListDestroy.get(i).y,45,35,null);
                }
            }

            g.drawImage(offScreenImage,0,0,null);
        }
    }



    private void calculatePlayerBullet(){
        if(shoot == KeyEvent.VK_SPACE){
            PlayerBullet playerBullet = new PlayerBullet();
            playerBullet.image = loadImageFromRes("bullet.png");

            playerBullet.x = playerPlane.x + 45/2 - 45/2/2;
            playerBullet.y = playerPlane.y - 35/2;
            playerBullet.speed = 10;
            playerBulletList.add(playerBullet);
        }

        for(int i = 0;i < playerBulletList.size();i++){
            playerBulletList.get(i).y -= playerBulletList.get(i).speed;
            if(playerBulletList.get(i).y < 0){
                playerBulletList.remove(i);
            }
        }
    }

    private void addEnemyPlane(){
        Random r = new Random();
        int sl = r.nextInt(7);
        for(int i = 0;i < sl;i++){
            EnemyPlane enemyPlane = new EnemyPlane();
            enemyPlane.x = r.nextInt(600);
            enemyPlane.y = 0;
            enemyPlane.speed = 6;
            enemyPlane.image = enemyPlaneImage;
            enemyPlane.enemyBulletList = new Vector<>();
            enemyPlaneList.add(enemyPlane);
        }
    }

    private void calculateEnemyPlaneCoordinate(){
        for(int i = 0;i < enemyPlaneList.size();i++){
            enemyPlaneList.get(i).y += enemyPlaneList.get(i).speed;
            if(enemyPlaneList.get(i).y > 800){
                enemyPlaneList.remove(i);
            }
            if(enemyPlaneList.size() != 0){
                for(int j = 0;j < playerBulletList.size();j++){
                    int xPBu = playerBulletList.get(j).x;
                    int yPBu = playerBulletList.get(j).y;
                    int xEnPla = enemyPlaneList.get(i).x;

                    int yEnPla = enemyPlaneList.get(i).y;
                    if(xPBu <= xEnPla + 45 && xPBu >= xEnPla && yPBu <= yEnPla + 35 && yPBu >= yEnPla){
                        enemyPlaneListDestroy.add(enemyPlaneList.get(i));
                        enemyPlaneList.remove(i);
                        break;
                    }
                }
            }
        }
        for(int i = 0;i < enemyPlaneListDestroy.size();i++){
            enemyPlaneListDestroy.get(i).image =  (BufferedImage)explosionImage.getSubimage(34*enemyPlaneListDestroy.get(i).explosionState,0,34,34);
            if(System.currentTimeMillis() % 5 == 0){
                enemyPlaneListDestroy.get(i).explosionState += 1;
            }
            if(enemyPlaneListDestroy.get(i).explosionState == 5){
                enemyPlaneListDestroy.remove(i);
            }
        }
    }

    private void calculateEnemyBullet(){
        for(int i = 0 ;i < enemyPlaneList.size();i++){
            EnemyPlane eP = enemyPlaneList.get(i);
            if((System.currentTimeMillis() - eP.countdownToShoot) > 500){
                eP.countdownToShoot = System.currentTimeMillis();
                EnemyBullet eB = new EnemyBullet();
                eB.speed = 15;
                eB.x = eP.x + 45/2 - 45/2/2 ;
                eB.y = eP.y + 35/2;
                eB.image = ImageLoader.loadImageFromRes("bullet-double.png");
                eP.enemyBulletList.add(eB);
            }

            for(int j = 0;j < eP.enemyBulletList.size();j++){
                EnemyBullet eB = eP.enemyBulletList.get(j);
                eB.y += eB.speed;
                if(eB.y > 800){
                    eP.enemyBulletList.remove(j);
                }
            }
        }
    }

    private void calculatePlaneCoordinate(){
        int x, y;
        x = playerPlane.x;
        y = playerPlane.y;

        switch (move){
            case (KeyEvent.VK_RIGHT):{
                playerPlane.x += SPEED;
                break;
            }
            case (KeyEvent.VK_LEFT):{
                playerPlane.x -= SPEED;
                break;
            }
            case (KeyEvent.VK_UP):{
                playerPlane.y -= SPEED;
                break;
            }
            case (KeyEvent.VK_DOWN):{
                playerPlane.y += SPEED;
                break;
            }
        }

        if(!checkCoordinatePlane()){
            playerPlane.x = x;
            playerPlane.y = y;
        }

    }


    private boolean checkCoordinatePlane(){
        if((playerPlane.x > FRAME_WIDTH - 45) || (playerPlane.x < 0) || (playerPlane.y > FRAME_HEIGHT - 35) || (playerPlane.y < 35)) {
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
