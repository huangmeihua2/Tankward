package collision.detection;

import com.tank.GameObject;
import com.tank.Tank;
import com.tank.Wall;

public class TankAndWallCollider implements Collider {
    public TankAndWallCollider() {
    }

    @Override
    public Boolean collid(GameObject gameObject1, GameObject gameObject2) {
        if(gameObject1 instanceof Tank && gameObject2 instanceof Wall){
            Tank tank = (Tank) gameObject1;
            Wall wall = (Wall) gameObject2;
            if(tank.tankRec.intersects(wall.wallRec)){
                tank.moveForemerStep();
            }
            return false;
        }else if(gameObject2 instanceof Tank && gameObject1 instanceof Wall){
            collid(gameObject2,gameObject1);
        }
        return true;
    }
}
