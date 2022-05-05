package collision.detection;

import com.tank.*;

public class TankAndBulletCollider implements Collider {
    public TankAndBulletCollider() {
    }

    @Override
    public Boolean collid(GameObject gameObject1, GameObject gameObject2) {
        if(gameObject1 instanceof Tank&&gameObject2 instanceof Bullet){
            Tank tank = (Tank) gameObject1;
            Bullet bullet = (Bullet)gameObject2;
            if(bullet.getGroup() == tank.getGroup()){
                return false; // 子弹不消灭己方
            }
            if(bullet.bulletRec.intersects(tank.tankRec)){
                bullet.die();
                tank.die();
                tank.getGameModel().gameObjectList.add(new Explode(tank.getX()+tank.WIDHT/2-Explode.WIDHT/2,tank.getY()+tank.HEIGHT-Explode.HIGHT,tank.getGameModel()));
                new Thread(()->new Audio("/audio/explode.wav").play()).start();
            }
            return false;
        }else if(gameObject2 instanceof Tank&&gameObject1 instanceof Bullet){
            collid(gameObject2,gameObject1);
            return false;
        }else {
            return true;
        }
    }
}
