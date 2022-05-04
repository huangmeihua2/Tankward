package collision.detection;

import com.tank.Bullet;
import com.tank.GameObject;
import com.tank.Wall;

public class WallAndBullet implements Collider {
    public WallAndBullet() {
    }

    @Override
    public Boolean collid(GameObject gameObject1, GameObject gameObject2) {
        if(gameObject1 instanceof Bullet && gameObject2 instanceof Wall){
            Bullet bullet = (Bullet) gameObject1;
            Wall wall = (Wall)gameObject2;
            if(bullet.bulletRec.intersects(wall.wallRec)){
                bullet.die();
            }
            return false;
        }else if(gameObject2 instanceof Bullet && gameObject1 instanceof Wall){
            collid(gameObject2,gameObject1);
        }
        return true;
    }
}
