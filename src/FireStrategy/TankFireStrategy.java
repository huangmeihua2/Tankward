package FireStrategy;

import com.tank.Tank;

import java.io.Serializable;

public interface TankFireStrategy extends Serializable {
    void fire(Tank tank);
}
