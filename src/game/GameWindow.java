package game;

import autoload.AutoLoadImage;
import controllers.*;
import models.EnemyPlaneModel;
import models.GameModel;
import models.PlayerBulletModel;
import models.PlayerPlaneModel;
import pool.Conllision;
import utils.CustomRandom;
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

    int move = -1;
    int shoot = -1;

    long now = System.currentTimeMillis();


    PlayerPlaneColtroller playerPlaneController;
    Vector<PlayerBulletController> playerBulletControllers;
    Vector<EnemyPlaneController> enemyPlaneControllers;
    Vector<EnemyBulletController> enemyBulletControllers;
    Vector<IslandController> islandControllers;
    Vector<PowerUpController> powerUpControllers;

    BackgroundController backgroundController;

    ControllerManager controllerManager;


    long lastTime = System.currentTimeMillis();
    public GameWindow(){
        setVisible(true);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        AutoLoadImage.init();
        offScreenImage = new BufferedImage(FRAME_WIDTH,FRAME_HEIGHT,BufferedImage.TYPE_INT_ARGB);

        //================================
        Conllision.openPool();
        controllerManager = new ControllerManager();
        //==================================
        playerPlaneController = new PlayerPlaneColtroller(FRAME_WIDTH/2 - 45/2,FRAME_HEIGHT - 55,45,55);
        enemyPlaneControllers = new Vector<>();
        islandControllers = new Vector<>();
        powerUpControllers = new Vector<>();

        playerBulletControllers = new Vector<>();
        enemyBulletControllers = new Vector<>();

        backgroundController = new BackgroundController(0,0,FRAME_WIDTH,FRAME_HEIGHT);

       Conllision.gameControllersCollision.add(playerPlaneController);
        controllerManager.add(backgroundController);

        controllerManager.add(playerPlaneController);




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
//                    pool.delete();
//                    pool.checkCollision();
//                    addIsland();
                    calculatePlaneCoordinate();

                    checkAndAddPlayerBullet();

                    addEnemyPlane();
//                    addEnemyPlane();
//                    addEnemyBullet();

                    calculatePlayerBullet();
                    controllerManager.run();
                    repaint();

                    if(!playerPlaneController.getModel().isVisible()){
                        break;
                    }
                }
            }
        });

        thread.start();

    }

    @Override
    public void update(Graphics g) {
        if(offScreenImage != null){
            offScreenGraphics = offScreenImage.getGraphics();

            controllerManager.draw(offScreenGraphics);

            g.drawImage(offScreenImage,0,0,null);
        }
    }

    private void addPlayerBullet(){
        if(((PlayerPlaneModel)playerPlaneController.getModel()).isPowerUp()){
            for(int i = 0;i < 3;i++){
                PlayerBulletController playerBulletController = new PlayerBulletController(playerPlaneController.getModel().getX() + 45/2 - 45/2/2,playerPlaneController.getModel().getY() - 35/2, 10,30,i+1);
                playerBulletControllers.add(playerBulletController);
                controllerManager.add(playerBulletController);
            }
        }else{
            PlayerBulletController playerBulletController = new PlayerBulletController(playerPlaneController.getModel().getX() + 45/2 - 45/2/2,playerPlaneController.getModel().getY() - 35/2, 10,30,0);
            playerBulletControllers.add(playerBulletController);
            Conllision.gameControllersCollision.add(playerBulletController);
            controllerManager.add(playerBulletController);
        }

    }


    private void checkAndAddPlayerBullet(){
        if(shoot == KeyEvent.VK_SPACE){
            if(playerBulletControllers.size() != 0){
                if(Math.abs(playerBulletControllers.get(playerBulletControllers.size() - 1).getModel().getX() - (playerPlaneController.getModel().getX() + 45/2 - 45/2/2)) > playerBulletControllers.get(0).getModel().getWidth() * 10){
                    addPlayerBullet();
                }else if((playerPlaneController.getModel().getY()-playerBulletControllers.get(playerBulletControllers.size()-1).getModel().getY() > playerBulletControllers.get(0).getModel().getHeight() * 2)){
                    addPlayerBullet();
                }
            }else{
                addPlayerBullet();
            }
        }
    }


    private void calculatePlayerBullet(){
        for(int i = 0;i < playerBulletControllers.size();i++){
            if(playerBulletControllers.get(i).getModel().getY() < 0){
                playerBulletControllers.get(i).getModel().setVisible(false);
            }
        }
    }

    private void addEnemyPlane(){
        EnemyPlaneController.EnemyPlaneType a = EnemyPlaneController.EnemyPlaneType.ENEMYWHITE;
        if(System.currentTimeMillis() - lastTime > 1000){
            lastTime = System.currentTimeMillis();
            for(int i = 0;i < CustomRandom.customNextInt(3);i++){
                EnemyPlaneController enemyPlaneController = EnemyPlaneController.create(CustomRandom.customNextInt(600),0,30,30, EnemyPlaneController.EnemyPlaneType.ENEMYWHITE,BulletController.BulletType.ROUND,1000, EnemyPlaneController.EnemyPlaneShootType.THREE, EnemyPlaneController.EnemyPlaneMoveType.TOP_DOWN);
                ControllerManager.gameControllers.add(enemyPlaneController);
                Conllision.gameControllersCollision.add(enemyPlaneController);
            }
        }
    }

//    private void addEnemyPlane(){
//        if(System.currentTimeMillis() - EnemyPlaneController.LAST_TIME_ADD_ENEMYPLANE > EnemyPlaneController.ADD_ENEMYPLANE_AFTER) {
//            EnemyPlaneController.LAST_TIME_ADD_ENEMYPLANE = System.currentTimeMillis();
//            int sl = CustomRandom.customNextInt(3);
//
//            int planeHavePowerUp = -1;
//
//            if(System.currentTimeMillis() - PowerUpController.LAST_TIME_POWERUP > PowerUpController.ADD_POWERUP_AFTER){
//                PowerUpController.LAST_TIME_POWERUP = System.currentTimeMillis();
//                if(sl != 0){
//                    planeHavePowerUp = CustomRandom.customNextInt(sl);
//                }
//            }
//
//            int enemyType = CustomRandom.customNextInt(2);
//            int specialMove = CustomRandom.customNextInt(2);
//            int limit = CustomRandom.customNextInt(100) + 200;
//            for (int i = 0; i < sl; i++) {
//                EnemyPlaneController enemyPlaneController = new EnemyPlaneController(CustomRandom.customNextInt(600), 0, 40,40,(i == planeHavePowerUp)?true:false,enemyType,limit,specialMove);
//                enemyPlaneControllers.add(enemyPlaneController);
//                controllerManager.add(enemyPlaneController);
//                pool.addToThePool(enemyPlaneController);
//            }
//        }
//    }

//    private void calculateEnemyPlaneCoordinate(){
//        for(int i = 0;i < enemyPlaneControllers.size();i++){
//            if(enemyPlaneControllers.get(i).getModel().getY() > 800){
//                enemyPlaneControllers.get(i).getModel().setVisible(false);
//            }
//        }
//
//        for(int i = 0;i < enemyPlaneControllers.size();i++){
//            EnemyPlaneModel e = enemyPlaneControllers.get(i).getModel();
//            if(!e.isVisible()){
//                if(e.getDestroy() && e.isHavePowerUp()){
//                    PowerUpController powerUpController = new PowerUpController(e.getX(),e.getY(),30,30,playerPlaneController,e.getPowerUpType());
//                    powerUpControllers.add(powerUpController);
//                    controllerManager.add(powerUpController);
//                    pool.addToThePool(powerUpController);
//                }
////                enemyPlaneControllers.removeElementAt(i);
//            }
//        }
//    }


//
//    private void addEnemyBullet(){
//        for(int i = 0 ;i < enemyPlaneControllers.size();i++){
//            EnemyPlaneController eP = enemyPlaneControllers.get(i);
//            if((System.currentTimeMillis() - eP.getModel().getLastTimeShot()) > eP.getModel().getSecondToshot()*1000){
//                eP.getModel().setLastTimeShot(System.currentTimeMillis());
//                EnemyBulletController eB = new EnemyBulletController(eP.getModel().getX() + eP.getModel().getWidth()/2 - 30/2,eP.getModel().getY()-10,30,100);
//                enemyBulletControllers.add(eB);
//                controllerManager.add(eB);
//                pool.addToThePool(eB);
//            }
//        }
//    }

//    private void calculateEnemyBullet(){
//        for(int i = 0;i < enemyBulletControllers.size();i++){
//            if(enemyBulletControllers.get(i).getModel().getY() >= FRAME_HEIGHT){
//                enemyBulletControllers.get(i).getModel().setVisible(false);
//            }
//        }
//    }


    private void calculatePlaneCoordinate(){
        switch (move){
            case (KeyEvent.VK_RIGHT):{
                playerPlaneController.movePlayerPlaneToRight();
                break;
            }
            case (KeyEvent.VK_LEFT):{
                playerPlaneController.movePlayerPlaneToLeft();
                break;
            }
            case (KeyEvent.VK_UP):{
                playerPlaneController.movePlayerPlaneUp();
                break;
            }
            case (KeyEvent.VK_DOWN):{
                playerPlaneController.movePlayerPlaneDown();
                break;
            }
        }
    }

//    private void addIsland(){
//
//        if(System.currentTimeMillis()-IslandController.LAST_TIME_ADD_ISLAND >= IslandController.ADD_ISLAND_AFTER){
//            IslandController.LAST_TIME_ADD_ISLAND = System.currentTimeMillis();
//            Random r = new Random();
//            int count = r.nextInt(2) + 1;
//            if(count > 1){
//                IslandController.ADD_ISLAND_AFTER = (r.nextInt(5) + 1) * 1000;
//            }else{
//                IslandController.ADD_ISLAND_AFTER = (r.nextInt(4) + 1) * 1000;
//            }
//            Vector<GameModel> vGM =  new Vector<>();
//
//            for(int i = 0;i < count;i++){
//                Vector<Integer> v = CustomRandom.customGeneratePosition(vGM,FRAME_WIDTH);
//                IslandController islandController = new IslandController(v.get(0),0,v.get(1),CustomRandom.customNextInt(50) + GameModel.MIN_HEIGHT_OF_MODEL);
//                islandController.setRandomImage();
//                islandControllers.add(islandController);
//                controllerManager.add(islandController);
//                vGM.add(islandController.getModel());
//            }
//
//
//        }
//    }
//
//    private void calculateIsland(){
//        for(int i = 0;i < islandControllers.size();i++){
//            if(islandControllers.get(i).getModel().getY() > FRAME_HEIGHT){
//                islandControllers.get(i).getModel().setVisible(false);
//            }
//        }
//    }

}
