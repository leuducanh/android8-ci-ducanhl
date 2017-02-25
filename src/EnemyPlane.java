import java.awt.*;
import java.util.Vector;

/**
 * Created by l on 2/25/2017.
 */
public class EnemyPlane {
    public int x,y;
    public Image image;
    public int speed;
    public Vector<EnemyBullet> enemyBulletList;
    public long countdownToShoot = System.currentTimeMillis();
    public int explosionState = 0;
}
