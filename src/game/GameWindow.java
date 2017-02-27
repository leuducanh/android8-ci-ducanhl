package game;

import controllers.EnemyBulletController;
import controllers.EnemyPlaneController;
import controllers.PlayerBulletController;
import controllers.PlayerPlaneColtroller;
import models.PlayerBulletModel;
import utils.Utils;
import views.PlayerBulletView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    int move = -1;
    int shoot = -1;

    long now = System.currentTimeMillis();


    PlayerPlaneColtroller playerPlaneColtroller;
    Vector<PlayerBulletController> playerBulletControllers;
    Vector<EnemyPlaneController> enemyPlaneControllers;
    Vector<EnemyBulletController> enemyBulletControllers;


    public GameWindow(){
        setVisible(true);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        offScreenImage = new BufferedImage(FRAME_WIDTH,FRAME_HEIGHT,BufferedImage.TYPE_INT_ARGB);


        playerPlaneColtroller = new PlayerPlaneColtroller(FRAME_WIDTH/2 - 45/2,FRAME_HEIGHT - 55,45,55);
        enemyPlaneControllers = new Vector<>();

        playerBulletControllers = new Vector<>();
        enemyBulletControllers = new Vector<>();

        backgroundImage = Utils.loadImageFromRes("background.png");

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
                    checkAndAddPlayerBullet();
                    calculatePlayerBullet();
                    addEnemyPlane();
                    calculateEnemyPlaneCoordinate();
                    addEnemyBullet();
                    calculateEnemyBullet();
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
            playerPlaneColtroller.draw(offScreenGraphics);
            if(playerBulletControllers != null){
                for(int i = 0;i < playerBulletControllers.size();i++){
                    playerBulletControllers.get(i).draw(offScreenGraphics);
                }
            }

            if(enemyPlaneControllers != null){
                for(int i = 0;i < enemyPlaneControllers.size();i++){
                    enemyPlaneControllers.get(i).draw(offScreenGraphics);
                }
            }

            if(enemyBulletControllers != null){
                for(int i = 0;i < enemyBulletControllers.size();i++){
                    enemyBulletControllers.get(i).draw(offScreenGraphics);
                }
            }
            g.drawImage(offScreenImage,0,0,null);
        }
    }

    private void addPlayerBullet(){
        PlayerBulletController playerBulletController = new PlayerBulletController(playerPlaneColtroller.getModel().getX() + 45/2 - 45/2/2,playerPlaneColtroller.getModel().getY() - 35/2, 10,30);
        playerBulletControllers.add(playerBulletController);
    }


    private void checkAndAddPlayerBullet(){
        if(shoot == KeyEvent.VK_SPACE){
            if(playerBulletControllers.size() != 0){
                if(playerBulletControllers.get(playerBulletControllers.size() - 1).getModel().getX() - (playerPlaneColtroller.getModel().getX() + 45/2 - 45/2/2) > playerBulletControllers.get(0).getModel().getWidth() * 2){
                    addPlayerBullet();
                }else if((playerPlaneColtroller.getModel().getY()-playerBulletControllers.get(playerBulletControllers.size()-1).getModel().getY() > playerBulletControllers.get(0).getModel().getHeight() * 2)){
                    addPlayerBullet();
                }
            }else{
                addPlayerBullet();
            }
        }
    }


    private void calculatePlayerBullet(){
        for(int i = 0;i < playerBulletControllers.size();i++){
            playerBulletControllers.get(i).run();
            if(playerBulletControllers.get(i).getModel().getY() < 0){
                playerBulletControllers.removeElementAt(i);
            }
        }
    }

    private void addEnemyPlane(){
        Random r = new Random();
        if(System.currentTimeMillis() - now > 1000*(r.nextInt(3)+1)) {
            now = System.currentTimeMillis();
            int sl = r.nextInt(3);
            for (int i = 0; i < sl; i++) {
                EnemyPlaneController enemyPlaneController = new EnemyPlaneController(r.nextInt(600), 0, 40,40);
                enemyPlaneControllers.add(enemyPlaneController);
            }
        }
    }

    private void calculateEnemyPlaneCoordinate(){
        for(int i = 0;i < enemyPlaneControllers.size();i++){
            enemyPlaneControllers.get(i).run();
            if(enemyPlaneControllers.get(i).getModel().getY() > 800){
                enemyPlaneControllers.get(i).getModel().setInvisible(false);
            }
                for(int j = 0;j < playerBulletControllers.size();j++){
                    int xPBu = playerBulletControllers.get(j).getModel().getX();
                    int yPBu = playerBulletControllers.get(j).getModel().getY();
                    enemyPlaneControllers.get(i).checkBeingShotByPlayer(xPBu,yPBu);
                }
        }

        for(int i = 0;i < enemyPlaneControllers.size();i++){
            if(!enemyPlaneControllers.get(i).getModel().isInvisible()){
                enemyPlaneControllers.removeElementAt(i);
            }
        }

    }



    private void addEnemyBullet(){
        for(int i = 0 ;i < enemyPlaneControllers.size();i++){
            EnemyPlaneController eP = enemyPlaneControllers.get(i);
            if((System.currentTimeMillis() - eP.getModel().getLastTimeShot()) > eP.getModel().getSecondToshot()*1000){
                eP.getModel().setLastTimeShot(System.currentTimeMillis());
                EnemyBulletController eB = new EnemyBulletController(eP.getModel().getX() + eP.getModel().getWidth()/2 - 45/2,eP.getModel().getY(),45,100);
                enemyBulletControllers.add(eB);
            }
        }
    }

    private void calculateEnemyBullet(){
        for(int i = 0;i < enemyBulletControllers.size();i++){
            enemyBulletControllers.get(i).run();

            if(enemyBulletControllers.get(i).getModel().getY() >= FRAME_HEIGHT){
                enemyBulletControllers.removeElementAt(i);
            }
        }
    }


    private void calculatePlaneCoordinate(){
        switch (move){
            case (KeyEvent.VK_RIGHT):{
                playerPlaneColtroller.movePlayerPlaneToRight();
                break;
            }
            case (KeyEvent.VK_LEFT):{
                playerPlaneColtroller.movePlayerPlaneToLeft();
                break;
            }
            case (KeyEvent.VK_UP):{
                playerPlaneColtroller.movePlayerPlaneUp();
                break;
            }
            case (KeyEvent.VK_DOWN):{
                playerPlaneColtroller.movePlayerPlaneDown();
                break;
            }
        }
    }


}
