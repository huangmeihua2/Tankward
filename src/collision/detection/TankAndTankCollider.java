package collision.detection;

import com.tank.GameObject;
import com.tank.Tank;

public class TankAndTankCollider implements Collider {
    public TankAndTankCollider() {
    }

    @Override
    public Boolean collid(GameObject gameObject1, GameObject gameObject2) {
        if(gameObject1 instanceof Tank && gameObject2 instanceof Tank){
            Tank tank1 = (Tank) gameObject1;
            Tank tank2 = (Tank) gameObject2;
            if(tank1.tankRec.intersects(tank2.tankRec)){
                tank1.moveForemerStep();
                tank2.moveForemerStep();
            }
            return false;
        }else {
            return true;
        }
    }
}
