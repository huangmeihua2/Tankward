package collision.detection;

import com.tank.Bullet;
import com.tank.GameObject;
import com.tank.Tank;

public class TankAndBulletCollider implements Collider {
    public TankAndBulletCollider() {
    }

    @Override
    public Boolean collid(GameObject gameObject1, GameObject gameObject2) {
        if(gameObject1 instanceof Tank&&gameObject2 instanceof Bullet){
            Tank tank = (Tank) gameObject1;
            Bullet bullet = (Bullet)gameObject2;
            bullet.isCollideWithTank(tank);
            return false;
        }else if(gameObject2 instanceof Tank&&gameObject1 instanceof Bullet){
            collid(gameObject2,gameObject1);
            return false;
        }else {
            return true;
        }
    }
}
